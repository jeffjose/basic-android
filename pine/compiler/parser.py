import re

import_pattern = re.compile(r'import\s+.*')
remember_mutablestate_pattern = re.compile(r'(val|var)\s+\$(.*)\s+=\s+(.*)')
remembersaveable_mutablestate_pattern = re.compile(r'(val|var)\s+\*(.*)\s+=\s+(.*)')

def parse_component(data):
    lines = [expand_component_line(x) for x in data.split("\n")]

    imports = []
    contents = []

    for line in lines:
        if import_pattern.match(line):
            imports.append(line)
        else:
            contents.append(line)

    imports = cleanup_imports(imports + analyze_for_imports(lines))

    return {"imports": "\n".join(imports), "contents": "\n".join(contents)}

def expand_component_line(line):

    matched = remember_mutablestate_pattern.match(line)
    if matched:
        
        t, vname, value = matched.groups()
        return f"{t} {vname} by remember {{ mutableStateOf({value}) }}"

    matched = remembersaveable_mutablestate_pattern.match(line)
    if matched:
        
        t, vname, value = matched.groups()
        return f"{t} {vname} by rememberSaveable {{ mutableStateOf({value}) }}"
        
    return line


def analyze_for_imports(lines):
    imports = []

    for line in lines:
        if 'rememberSaveable' in line:
            imports.append('import androidx.compose.runtime.saveable.rememberSaveable')
            imports.append('import androidx.compose.runtime.mutableStateOf')
            imports.append('import androidx.compose.runtime.getValue')
            imports.append('import androidx.compose.runtime.setValue')
        elif 'remember' in line:
            imports.append('import androidx.compose.runtime.remember')
            imports.append('import androidx.compose.runtime.mutableStateOf')
            imports.append('import androidx.compose.runtime.getValue')
            imports.append('import androidx.compose.runtime.setValue')
    
    return imports


def cleanup_imports(imports):

    # Dedupes by splitting the `import` statement into components and joining it back together
    # Sorts
    return sorted(set([" ".join(t.strip() for t in x.split()) for x in imports]))