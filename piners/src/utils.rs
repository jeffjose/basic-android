use std::fs;
use std::fs::File;
use std::io::{self, Read, Write};
use std::path::{Path, PathBuf};
use serde_json::{self, Value};

const NAMESPACE_BASE: &str = "com.example";

const OUTPUT_BASE: &str = "dist";

pub fn get_project_name() -> &'static str {
    "cupcake"
}

pub fn get_project_namespace() -> String {
    format!("{}.{}", NAMESPACE_BASE, get_project_name())
}

pub fn get_output_dir() -> PathBuf {
    Path::new(OUTPUT_BASE).join(get_project_name())
}

pub fn get_top_app_dir() -> PathBuf {
    get_output_dir().join("app")
}

pub fn get_src_dir() -> PathBuf {
    get_top_app_dir().join("src")
}

pub fn get_main_dir() -> PathBuf {
    get_src_dir().join("main")
}

pub fn get_app_dir() -> PathBuf {
    get_main_dir().join(format!("java/com/example/{}", get_project_name()))
}

pub fn get_screen_dir() -> PathBuf {
    let ui_dir = get_app_dir().join("ui");
    mkdir(&ui_dir);
    ui_dir
}

pub fn get_components_dir() -> PathBuf {
    let components_dir = get_app_dir().join("ui/components");
    mkdir(&components_dir);
    components_dir
}

pub fn get_project_name_capitalized() -> String {
    let mut project_name = get_project_name().to_string();
    project_name[..1].make_ascii_uppercase();
    project_name
}

pub fn mkdir(path: &Path) {
    println!("mkdir {:?}", path);
    fs::create_dir_all(path).unwrap_or_else(|err| {
        eprintln!("Failed to create directory {:?}: {}
