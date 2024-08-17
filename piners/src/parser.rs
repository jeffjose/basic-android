use regex::Regex;
use std::collections::HashSet;

pub fn parse_component(data: &str) -> (String, String) {
    let import_pattern = Regex::new(r"import\s+.*").unwrap();
    let lines: Vec<String> = data.lines().map(|x| expand_component_line(x)).collect();

    let mut imports = Vec::new();
    let mut contents = Vec::new();

    for line in &lines {
        if import_pattern.is_match(line) {
            imports.push(line.clone());
        } else {
            contents.push(line.clone());
        }
    }

    imports.extend(analyze_for_imports(&lines));
    let imports = cleanup_imports(&imports);

    (imports.join("\n"), contents.join("\n"))
}

pub fn expand_component_line(line: &str) -> String {
    let remember_mutablestate_pattern = Regex::new(r"(val|var)\s+\$(.*)\s+=\s+(.*)").unwrap();
    let remembersaveable_mutablestate_pattern =
        Regex::new(r"(val|var)\s+\*(.*)\s+=\s+(.*)").unwrap();

    if let Some(captures) = remember_mutablestate_pattern.captures(line) {
        let t = &captures[1];
        let vname = &captures[2];
        let value = &captures[3];
        return format!(
            "{} {} by remember {{ mutableStateOf({}) }}",
            t, vname, value
        );
    }

    if let Some(captures) = remembersaveable_mutablestate_pattern.captures(line) {
        let t = &captures[1];
        let vname = &captures[2];
        let value = &captures[3];
        return format!(
            "{} {} by rememberSaveable {{ mutableStateOf({}) }}",
            t, vname, value
        );
    }

    line.to_string()
}

pub fn analyze_for_imports(lines: &[String]) -> Vec<String> {
    let mut imports = Vec::new();

    for line in lines {
        if line.contains("rememberSaveable") {
            imports.push("import androidx.compose.runtime.saveable.rememberSaveable".to_string());
            imports.push("import androidx.compose.runtime.mutableStateOf".to_string());
            imports.push("import androidx.compose.runtime.getValue".to_string());
            imports.push("import androidx.compose.runtime.setValue".to_string());
        } else if line.contains("remember") {
            imports.push("import androidx.compose.runtime.remember".to_string());
            imports.push("import androidx.compose.runtime.mutableStateOf".to_string());
            imports.push("import androidx.compose.runtime.getValue".to_string());
            imports.push("import androidx.compose.runtime.setValue".to_string());
        }
    }

    imports
}

pub fn cleanup_imports(imports: &[String]) -> Vec<String> {
    let mut unique_imports: HashSet<String> = HashSet::new();

    for import in imports {
        let clean_import: String = import.split_whitespace().collect::<Vec<&str>>().join(" ");
        unique_imports.insert(clean_import);
    }

    let mut imports_vec: Vec<String> = unique_imports.into_iter().collect();
    imports_vec.sort();
    imports_vec
}

fn main() {
    let data = "your input string here";
    let (imports, contents) = parse_component(data);
    println!("Imports:\n{}", imports);
    println!("Contents:\n{}", contents);
}
