use jni::objects::{JClass, JString};
use jni::JNIEnv;
use fastanvil::{JavaChunk, RegionBuffer};
use fastnbt::de::from_bytes;


#[no_mangle]
#[allow(non_snake_case)]
pub extern "system" fn Java_pro_prysm_orion_server_world_ChunkManager_loadRegion(
    env: JNIEnv,
    _class: JClass,
    path: JString
) {

    let s: String = env.get_string(path).expect("").into();

    let file = std::fs::File::open(s.clone()).unwrap();
    let region = RegionBuffer::new(file);
    let data = region.load_chunk(0, 0).unwrap();

    let chunk: JavaChunk = from_bytes(data.as_slice()).unwrap();

    println!("{:?}", chunk);
}