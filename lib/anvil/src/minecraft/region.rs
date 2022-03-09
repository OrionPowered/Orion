use std::borrow::BorrowMut;
use std::fs::File;
use std::io::{Read, Seek, SeekFrom, Write};
use crate::error::ResErr;
use crate::flate::decompress;
use crate::minecraft::chunk::{ChunkCompression, ChunkLocation};

pub const SECTOR_SIZE: i32 = 4096; // 4KiB

pub struct Region<SR: Seek + Read + Write> {
    path: String,
    raw: SR,
}

impl<SR: Seek + Read + Write> Region<SR> {
    pub fn new(path: String, data: SR) -> Self {
        Self {
            path,
            raw: data
        }
    }

    pub fn get_chunk_location(&mut self, chunk_x: i32, chunk_z: i32) -> Result<ChunkLocation, ResErr> {
        if chunk_x >= 32 || chunk_z >= 32 { return Err(ResErr::InvalidCoordinates(chunk_x,chunk_z))}
        else {
            let loc: i32 = super::super::util::get_chunk_header_location(chunk_x, chunk_z);

            // Seek to location in header
            self.raw.borrow_mut().seek(SeekFrom::Start(loc as u64))?;
            let mut buf: [u8;4] = [0u8; 4];
            self.raw.borrow_mut().read_exact(&mut buf)?;

            let mut offset: i32 = 0;
            offset |= (buf[0] as i32) << 16;
            offset |= (buf[1] as i32) << 8;
            offset |= buf[2] as i32;

            Ok(ChunkLocation {
                sector_offset: offset,
                sector_count: buf[3] as i32,
                x: chunk_x,
                z: chunk_z
            })
        }
    }

    pub fn chunk_data(&mut self, x: i32, z: i32) -> Result<Vec<u8>, ResErr> {
        let loc: ChunkLocation = self.get_chunk_location(x, z).unwrap();
        return if loc.sector_count == loc.sector_offset && loc.sector_offset == 0 {
            Err(ResErr::NoChunk)
        } else {
            let mut buf: Vec<u8> = Vec::new();

            // Seek to the chunk offset
            self.raw.borrow_mut().seek(SeekFrom::Start(loc.sector_offset as u64 * SECTOR_SIZE as u64))?;

            // Resize the buffer to read compression details
            buf.resize(5, 0);
            self.raw.borrow_mut().read_exact(&mut buf[0..5])?;

            let chunk_compression = ChunkCompression::new(&mut buf)?;

            // Resize the buffer to take in the rest of the chunk data
            buf.resize(buf.len() + chunk_compression.length as usize, 0u8);

            // Read the rest of the compressed chunk
            self.raw.borrow_mut().read_exact(&mut buf[5..])?;

            return decompress(chunk_compression, &buf);
        }
    }
}

impl Clone for Region<File> {
    fn clone(&self) -> Self {
        Region {
            path: self.path.clone(),
            raw: self.raw.try_clone().unwrap()
        }
    }

    fn clone_from(&mut self, _source: &Self) {
        todo!()
    }
}