use glob::glob;
use regex::Regex;
use std::collections::HashMap;
use std::fs;
use std::path::{Path, PathBuf};

mod utils;
use compiler::parser::parse_component;
use utils::{get_app_dir, get_components_dir, get_project_namespace, mkdir, read_file, write_file};

const INPUT_PATTERN: &str = "src/**/**.pine";
const TEMPLATE_COMPONENT: &str = r#"%%PACKAGENAME%%

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import %%NAMESPACE%%.ui.theme.CupcakeTheme

%%IMPORT%%

@Composable
fn %%NAME%%() {
    %%CONTENT%%
}

/*
@Preview
@Composable
fn %%NAME%%Preview() {
    CupcakeTheme {
        %%NAME%%(
        )
    }
}
*/"#;

pub fn collect_files() -> Vec<PathBuf> {
    let files: Vec<PathBuf> = glob(INPUT_PATTERN)
        .expect("Failed to read glob pattern")
        .filter_map(Result::ok)
        .filter(|path| path.file_name() != Some("+screen.pine".as_ref()))
        .collect();

    println!("Collected components -");
    for file in &files {
        println!("- {:?}", file);
    }

    files
}

pub fn get_template_component() -> &'static str {
    TEMPLATE_COMPONENT
}

pub fn get_slug(file: &Path) -> String {
    get_name(file).to_uppercase()
}

pub fn mkpackage_string_component(output_dir_base: &str) -> String {
    format!(
        "package {}.ui.components{}",
        get_project_namespace(),
        if output_dir_base.is_empty() {
            String::new()
        } else {
            format!(".{}", output_dir_base.replace('/', "."))
        }
    )
}

pub fn get_component_name(slug: &str, include_ext: bool) -> String {
    format!("{}{}", slug, if include_ext { ".kt" } else { "" })
}

pub fn get_output_dir_base(file: &Path) -> String {
    file.parent()
        .unwrap_or_else(|| Path::new(""))
        .to_str()
        .unwrap_or("")
        .replace("src/components", "")
        .trim_matches('/')
        .to_string()
}

pub fn create_component(template: &str, file: &Path) {
    let output_dir_base = get_output_dir_base(file);
    let parcel = parse_component(&read_file(file).expect("Failed to read file"));
    let slug = get_slug(file);

    println!("{}", mkpackage_string_component(&output_dir_base));

    let final_component = template
        .replace(
            "%%PACKAGENAME%%",
            &mkpackage_string_component(&output_dir_base),
        )
        .replace("%%NAMESPACE%%", &get_project_namespace())
        .replace("%%IMPORT%%", &parcel["imports"])
        .replace("%%CONTENT%%", &parcel["contents"])
        .replace("%%NAME%%", &slug)
        .trim()
        .to_string();

    mkdir(&get_components_dir().join(&output_dir_base));
    write_file(
        &get_components_dir()
            .join(&output_dir_base)
            .join(&get_component_name(&slug, true)),
        &final_component,
    )
    .expect("Failed to write file");
}

pub fn get_name(file: &Path) -> String {
    file.file_stem()
        .unwrap_or_default()
        .to_str()
        .unwrap()
        .to_string()
}

pub fn get_route(file: &Path, routable: bool) -> String {
    let route = file
        .parent()
        .unwrap_or_else(|| Path::new(""))
        .to_str()
        .unwrap()
        .replace("src/routes", "");

    let mut route = if route.is_empty() { "/" } else { &route };
    if routable {
        route = &route.replace("[", "{").replace("]", "}");
    }

    route.to_string()
}

pub fn get_composable(template_composable: &str, file: &Path) -> String {
    template_composable
        .replace("%%ROUTE%%", &get_route(file, true))
        .replace("%%NAME%%", &get_slug(file))
}

pub fn get_static_route_files(files: &[PathBuf]) -> Vec<PathBuf> {
    files.to_vec()
}

pub fn get_param_route_files(files: &[PathBuf]) -> Vec<PathBuf> {
    files
        .iter()
        .filter(|file| {
            file.parent()
                .unwrap_or_else(|| Path::new(""))
                .file_name()
                .unwrap_or_default()
                .to_str()
                .unwrap()
                .starts_with('[')
        })
        .cloned()
        .collect()
}

fn main() {
    let files = collect_files();
    let template_screen = get_template_component();

    println!("Working on components");

    for file in get_static_route_files(&files) {
        create_component(template_screen, &file);
    }

    // Uncomment and implement if needed
    // let template_composable = get_template_composable();
    // let template_navigation = get_template_navigation();
    // create_navigation(template_navigation, template_composable, files);
}
