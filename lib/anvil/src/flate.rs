use std::io::Read;
use flate2::read::{GzDecoder, ZlibDecoder};
use crate::error::ResErr;
use crate::minecraft::chunk::ChunkCompression;

pub enum CompressionType {
    Gzip = 1,
    Zlib = 2,
    None = 3,
}

impl CompressionType {
    pub fn from(n: u8) -> Self {
        match n {
            0x01 => Self::Gzip,
            0x02 => Self::Zlib,
            _ => Self::None
        }
    }
}

pub fn decompress(compression: ChunkCompression, data: &[u8]) -> Result<Vec<u8>, ResErr> {
    let compressed = &data[5..];

    match compression.scheme {
        CompressionType::Zlib => zlib(compressed),
        CompressionType::Gzip => gzip(compressed),
        _ => Ok(Vec::from(compressed))
    }
}

fn zlib(data: &[u8]) -> Result<Vec<u8>, ResErr> {
    let mut decoder = ZlibDecoder::new(data);
    let mut uncompressed = Vec::new();
    decoder.read_to_end(&mut uncompressed)?;
    Ok(uncompressed)
}

fn gzip(data: &[u8]) -> Result<Vec<u8>, ResErr> {
    let mut decoder = GzDecoder::new(data);
    let mut uncompressed = Vec::new();
    decoder.read_to_end(&mut uncompressed)?;
    Ok(uncompressed)
}