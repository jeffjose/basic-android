use glob::glob;
use regex::Regex;
use std::fs;
use std::path::{Path, PathBuf};

mod utils;
use compiler::parser::parse_component;
use utils::{get_app_dir, get_project_namespace, get_screen_dir, read_file, write_file};

const ROUTE_PARAM_REGEX: &str = r"\[(.*?)\]";
const INPUT_PATTERN: &str = "src/routes/**/+screen.pine";

const TEMPLATE_COMPOSABLE: &str = r#"
    composable(
            route = "%%ROUTE%%",
            ) { backStackEntry -> 

      %%NAME%%Screen(navController = navController, params = backStackEntry.arguments, http = http)
      }
"#;

const TEMPLATE_NAVIGATION: &str = r#"%%PACKAGENAME%%

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.res.dimensionResource
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.fillMaxSize
import com.example.cupcake.R
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.launch
import androidx.compose.runtime.LaunchedEffect

%%IMPORTS%%

@Composable
fn Navigation(navController: NavHostController) {

    let http = HttpClient::new(CIO) {
            install(ContentNegotiation) {
                json()
            }
        };

    NavHost(
          navController = navController,
          startDestination = "/",
          modifier =
                  Modifier.fillMaxSize().verticalScroll(rememberScrollState())
  ) {

    %%COMPOSABLES%%
  }
}

"#;

const TEMPLATE_SCREEN: &str = r#"%%PACKAGENAME%%

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect

import %%NAMESPACE%%.ui.theme.CupcakeTheme

%%IMPORT%%

@Composable
fn %%NAME%%Screen(navController: NavHostController, params: Option<Bundle>, http: HttpClient) {

    %%CONTENT%%
}

/*
@Preview
@Composable
fn %%NAME%%ScreenPreview() {
    CupcakeTheme {
        %%NAME%%Screen(
            navController = rememberNavController(),
            params = None
        )
    }
    }
    */
"#;

pub fn collect_files() -> Vec<PathBuf> {
    let mut files: Vec<PathBuf> = glob(INPUT_PATTERN)
        .expect("Failed to read glob pattern")
        .filter_map(Result::ok)
        .collect();
    files.sort();

    println!("Collected routes -");
    for file in &files {
        println!("- {:?}", file);
    }

    files
}

pub fn get_template_screen() -> &'static str {
    TEMPLATE_SCREEN
}

pub fn get_template_navigation() -> &'static str {
    TEMPLATE_NAVIGATION
}

pub fn get_template_composable() -> &'static str {
    TEMPLATE_COMPOSABLE
}

pub fn get_slug(file: &Path) -> String {
    let route = get_route(file, false);

    let mut route = if route == "/" {
        "root".to_string()
    } else {
        route
            .trim_matches('/')
            .split('/')
            .map(|x| x.to_lowercase().capitalize())
            .collect::<String>()
    };

    let re = Regex::new(ROUTE_PARAM_REGEX).expect("Invalid regex pattern");
    route = re
        .replace_all(&route, |caps: &regex::Captures| {
            format!("RouteParam{}", caps[1].capitalize())
        })
        .to_string();

    route
}

pub fn mkpackage_string_screen() -> String {
    format!("package {}.ui", get_project_namespace())
}

pub fn mkpackage_string() -> String {
    format!("package {}", get_project_namespace())
}

pub fn get_screenfile_name(slug: &str, include_ext: bool) -> String {
    format!("{}Screen{}", slug, if include_ext { ".kt" } else { "" })
}

pub fn create_screen(template: &str, file: &Path) {
    let parcel = parse_component(&read_file(file).expect("Failed to read file"));
    let slug = get_slug(file);

    let final_screen = template
        .replace("%%PACKAGENAME%%", &mkpackage_string_screen())
        .replace("%%NAMESPACE%%", &get_project_namespace())
        .replace("%%IMPORT%%", &parcel["imports"])
        .replace("%%CONTENT%%", &parcel["contents"])
        .replace("%%NAME%%", &slug)
        .trim()
        .to_string();

    write_file(
        &get_screen_dir().join(get_screenfile_name(&slug, true)),
        &final_screen,
    )
    .expect("Failed to write file");
}

pub fn get_route(file: &Path, routable: bool) -> String {
    let mut route = file
        .parent()
        .unwrap_or_else(|| Path::new(""))
        .to_str()
        .unwrap_or("")
        .replace("src/routes", "");

    if route.is_empty() {
        route = "/".to_string();
    }

    if routable {
        route = route.replace("[", "{").replace("]", "}");
    }

    route
}

pub fn get_composable(template_composable: &str, file: &Path) -> String {
    template_composable
        .replace("%%ROUTE%%", &get_route(file, true))
        .replace("%%NAME%%", &get_slug(file))
}

pub fn create_navigation(template_navigation: &str, template_composable: &str, files: &[PathBuf]) {
    let slugs: Vec<String> = files.iter().map(get_slug).collect();
    let filenames: Vec<String> = slugs
        .iter()
        .map(|slug| get_screenfile_name(slug, false))
        .collect();

    let imports: Vec<String> = filenames
        .iter()
        .map(|filename| format!("import {}.ui.{}", get_project_namespace(), filename))
        .collect();

    let composables: Vec<String> = files
        .iter()
        .map(|file| get_composable(template_composable, file))
        .collect();

    let final_navigation = template_navigation
        .replace("%%PACKAGENAME%%", &mkpackage_string())
        .replace("%%IMPORTS%%", &imports.join("\n"))
        .replace("%%COMPOSABLES%%", &composables.join("\n"))
        .trim()
        .to_string();

    write_file(&get_app_dir().join("Navigation.kt"), &final_navigation)
        .expect("Failed to write file");
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
                .unwrap_or("")
                .starts_with('[')
        })
        .cloned()
        .collect()
}

fn main() {
    let files = collect_files();
    let template_screen = get_template_screen();

    // Static routes
    println!("Working on static routes");

    for file in get_static_route_files(&files) {
        create_screen(template_screen, &file);
    }

    let template_composable = get_template_composable();
    let template_navigation = get_template_navigation();

    create_navigation(template_navigation, template_composable, &files);

    // Uncomment if needed
    // println!("Working on param routes");
    // for file in get_param_route_files(&files) {
    //     create_screen(template_screen, &file);
    // }
}
