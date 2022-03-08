pub fn get_chunk_header_location(chunk_x: i32, chunk_z: i32) -> i32 {
    let mut loc_x: i32 = chunk_x%32;
    let mut loc_z: i32 = chunk_z%32;

    // If either loc_x or loc_y are negative, we & instead of %
    if loc_x < 0 { loc_x = chunk_x+32; }
    if loc_z < 0 { (loc_z = chunk_z+32); }

    return 4 * (loc_x + loc_z * 32);
}