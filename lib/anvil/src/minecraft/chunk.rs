use byteorder::{BigEndian, ReadBytesExt};
use crate::flate::CompressionType;
use crate::error::ResErr;

pub struct ChunkCompression {
    pub length: u32,
    pub scheme: CompressionType
}

pub struct ChunkLocation {
    pub sector_offset: i32,
    pub sector_count: i32,
    pub x: i32, pub z: i32
}

impl ChunkCompression {
    pub fn new(buf: &[u8]) -> Result<Self, ResErr> {
        let mut buf = &buf[..5]; // Ensure we only read 5 bytes
        let length = buf.read_u32::<BigEndian>()?;
        let scheme: CompressionType = CompressionType::from(buf.read_u8()?);
        Ok(Self {
            length: length-1,
            scheme,
        })
    }
}