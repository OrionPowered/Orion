use std::fs::File;
use std::path::Path;
use crate::minecraft::region::Region;
use cached::proc_macro::cached;

#[cached]
pub fn get_region(path: String) -> Region<File> {
    let file: File = std::fs::File::open(Path::new(&path).clone()).expect("Failed to unwrap region file");
    return Region::new(path, file);
}

#[cached]
pub fn get_chunk(path: String, x: i32, z: i32) -> Vec<u8> {
    return get_region(path).chunk_data(x, z).unwrap_or(Vec::with_capacity(0));
}