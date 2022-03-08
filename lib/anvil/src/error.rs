use std::error::Error;
use std::fmt::{Debug, Display, Formatter};

pub enum ResErr {
    InvalidCoordinates(i32, i32),
    NoChunk,
    IOErr(std::io::Error)
}

impl Debug for ResErr {
    fn fmt(&self, _f: &mut Formatter<'_>) -> std::fmt::Result {
        unimplemented!()
    }
}

impl Display for ResErr {
    fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
        match self {
            ResErr::InvalidCoordinates(x,z) => f.write_fmt(format_args!("Invalid coordinates: {}, {}", x, z)),
            ResErr::NoChunk => f.write_str("Chunk not found in region."),
            _ => f.write_str("An unknown error occurred.")
        }
    }
}

impl From<std::io::Error> for ResErr {
    fn from(err: std::io::Error) -> ResErr {
        return ResErr::IOErr(err);
    }
}

impl Error for ResErr {

}