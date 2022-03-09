use jni::JNIEnv;
use jni::objects::{JClass, JString};
use jni::sys::{jbyteArray, jint};

#[no_mangle]
#[allow(non_snake_case)]
pub extern "system" fn Java_pro_prysm_orion_server_world_anvilworld_ImplRegion_getChunkData(
    env: JNIEnv,
    _class: JClass,
    path: JString,
    x: jint,
    z: jint
) -> jbyteArray {
    let s: String = env.get_string(path).expect("Failed to unwrap region path").into();

    let data: Vec<u8> = super::super::cache::get_chunk(s, x, z);

    env.byte_array_from_slice(&*data).expect("Failed to create byte array")
}