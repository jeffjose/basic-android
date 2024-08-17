use serde_json::Value;
use std::collections::HashMap;
use std::fs;
use std::path::{Path, PathBuf};

mod utils;
use utils::{
    get_main_dir, get_project_name_capitalized, get_screen_dir, read_file, read_json, write_file,
    write_json,
};

const INPUT_MANIFEST: &str = "src/manifest.json";
const TEMPLATE: &str = r#"<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    %%PERMISSIONS%%

    <application
        android:icon="{icon}"
        android:label="{label}"
        android:roundIcon="{roundIcon}"
        android:supportsRtl="{supportsRtl}"
        android:theme="{theme}"
        tools:targetApi="{targetApi}">
        <activity
            android:name=".{activity}"
            android:exported="{exported}"
            android:theme="{theme}">
            <intent-filter>
                <action android:name="{intentAction}" />
                <category android:name="{intentCategory}" />
            </intent-filter>
        </activity>
    </application>

</manifest>
"#;

const TEMPLATE_PERMISSION: &str = r#"<uses-permission android:name="{permission}" />"#;

pub fn get_output_manifest() -> PathBuf {
    get_main_dir().join("AndroidManifest.xml")
}

pub fn get_template() -> &'static str {
    TEMPLATE
}

pub fn handle_label(text: &str) -> Result<&str, String> {
    if text.starts_with("@string/") {
        Ok(text)
    } else {
        Err("label is not of the format: @string/foobar".to_string())
    }
}

pub fn handle_icon(text: &str) -> Result<&str, String> {
    if text.starts_with("@mipmap/") {
        Ok(text)
    } else {
        Err("icon is not of the format: @mipmap/foobar".to_string())
    }
}

pub fn handle_round_icon(text: &str) -> Result<&str, String> {
    if text.starts_with("@mipmap/") {
        Ok(text)
    } else {
        Err("roundIcon is not of the format: @mipmap/foobar".to_string())
    }
}

pub fn handle_supports_rtl(text: &str) -> &str {
    text
}

pub fn handle_theme(text: &str) -> Result<&str, String> {
    if text.starts_with("@style/") {
        Ok(text)
    } else {
        Err("theme is not of the format: @style/foobar".to_string())
    }
}

pub fn handle_target_api(text: &str) -> &str {
    text
}

pub fn handle_activity(text: &str) -> &str {
    text
}

pub fn handle_exported(text: &str) -> &str {
    text
}

pub fn handle_intent_action(text: &str) -> &str {
    text
}

pub fn handle_intent_category(text: &str) -> &str {
    text
}

pub fn handle_permissions(permissions: &Value) -> Vec<String> {
    permissions
        .as_array()
        .map(|arr| {
            arr.iter()
                .filter_map(|v| v.as_str().map(String::from))
                .collect()
        })
        .unwrap_or_else(Vec::new)
}

pub fn parse(data: &Value) -> Result<HashMap<String, String>, String> {
    let mut result = HashMap::new();

    result.insert(
        "label".to_string(),
        handle_label(
            data.get("label")
                .unwrap_or(&Value::String("@string/app_name".to_string()))
                .as_str()
                .unwrap(),
        )?
        .to_string(),
    );
    result.insert(
        "icon".to_string(),
        handle_icon(
            data.get("icon")
                .unwrap_or(&Value::String("@mipmap/ic_launcher".to_string()))
                .as_str()
                .unwrap(),
        )?
        .to_string(),
    );
    result.insert(
        "roundIcon".to_string(),
        handle_round_icon(
            data.get("roundIcon")
                .unwrap_or(&Value::String("@mipmap/ic_launcher_round".to_string()))
                .as_str()
                .unwrap(),
        )?
        .to_string(),
    );
    result.insert(
        "supportsRtl".to_string(),
        handle_supports_rtl(
            data.get("supportsRtl")
                .unwrap_or(&Value::String("true".to_string()))
                .as_str()
                .unwrap(),
        )
        .to_string(),
    );
    result.insert(
        "theme".to_string(),
        handle_theme(
            data.get("theme")
                .unwrap_or(&Value::String(format!(
                    "@style/Theme.{}",
                    get_project_name_capitalized()
                )))
                .as_str()
                .unwrap(),
        )?
        .to_string(),
    );
    result.insert(
        "targetApi".to_string(),
        handle_target_api(
            data.get("targetApi")
                .unwrap_or(&Value::String("".to_string()))
                .as_str()
                .unwrap(),
        )
        .to_string(),
    );
    result.insert(
        "activity".to_string(),
        handle_activity(
            data.get("activity")
                .unwrap_or(&Value::String("MainActivity".to_string()))
                .as_str()
                .unwrap(),
        )
        .to_string(),
    );
    result.insert(
        "exported".to_string(),
        handle_exported(
            data.get("exported")
                .unwrap_or(&Value::String("true".to_string()))
                .as_str()
                .unwrap(),
        )
        .to_string(),
    );
    result.insert(
        "intentAction".to_string(),
        handle_intent_action(
            data.get("intent")
                .and_then(|v| v.get("action"))
                .unwrap_or(&Value::String("".to_string()))
                .as_str()
                .unwrap(),
        )
        .to_string(),
    );
    result.insert(
        "intentCategory".to_string(),
        handle_intent_category(
            data.get("intent")
                .and_then(|v| v.get("category"))
                .unwrap_or(&Value::String("".to_string()))
                .as_str()
                .unwrap(),
        )
        .to_string(),
    );
    result.insert(
        "permissions".to_string(),
        handle_permissions(data.get("permissions").unwrap_or(&Value::Null)).join("\n"),
    );

    Ok(result)
}

pub fn create_manifest(template: &str, manifest: &Value) -> Result<(), String> {
    let parcel = parse(manifest)?;

    let mut final_content = template.to_string();
    for (key, value) in &parcel {
        final_content = final_content.replace(&format!("{{{}}}", key), value);
    }

    let permissions = parcel.get("permissions").unwrap_or(&"".to_string());
    final_content = final_content.replace("%%PERMISSIONS%%", permissions);

    write_file(&get_output_manifest(), &final_content).map_err(|e| e.to_string())
}

pub fn get_manifest() -> Result<Value, String> {
    read_json(Path::new(INPUT_MANIFEST)).map_err(|e| e.to_string())
}

fn main() -> Result<(), String> {
    let manifest = get_manifest()?;
    let template = get_template();
    create_manifest(template, &manifest)
}
