import re


import_pattern = re.compile(r"import\s+.*")

external_variable_w_default_value_pattern = re.compile(
    r"^external\s+(val|var)\s+(.*)\s+=\s+(.*)"
)
external_variable_pattern = re.compile(r"^external\s+(val|var)\s+(.*)")

remember_mutablestate_pattern = re.compile(r"(val|var)\s+\$(.*)\s+=\s+(.*)")
remembersaveable_mutablestate_pattern = re.compile(r"(val|var)\s+\*(.*)\s+=\s+(.*)")

remember_derivedstateof_pattern = re.compile(
    r"(val|var)\s+(.*)\s+=\s+\$derived\((.*)\)"
)
remember_sideeffect_pattern = re.compile(r"(val|var)\s+(.*)\s+=\s+\$effect\((.*)\)")


def get_frontmatter(lines):

    first_line = lines[0]
    if first_line == "---":

        end_of_frontmatter_index = (
            lines[1:].index("---") + 2
        )  # Adding 1 since first item is ignored, and go beyond the final `---`

        frontmatter = lines[0:end_of_frontmatter_index]
    else:
        frontmatter = []
        end_of_frontmatter_index = 0

    parcel = get_imports_and_contents(frontmatter)

    return {
        "frontmatter": parcel["contents"],
        "imports": parcel["imports"],
        "rest": lines[end_of_frontmatter_index:],
    }


def parse_component(data, default_imports):

    lines = data.split("\n")

    frontmatter = get_frontmatter(lines)

    # `get_frontmatter` removes the frontmatter, so use the `rest` of the lines
    lines = frontmatter["rest"]

    # This needs to come before `expand_component_lines`
    # since one of the things expand_compomnent_lines
    # does is remove `external var foo..` lines
    exports = get_exports(lines)

    lines = [expand_component_line(x) for x in lines]

    parcel = get_imports_and_contents(lines)


    imports = cleanup_imports(
        parcel["imports"] + analyze_for_imports(lines) + frontmatter["imports"] + 
        default_imports
    )

    return {
        "imports": "\n".join(imports),
        "exports": exports,
        "contents": "\n".join(parcel["contents"]),
        "frontmatter": "\n".join(frontmatter["frontmatter"]),
    }


def get_imports_and_contents(lines):

    imports = []
    contents = []

    for line in lines:
        if line == "---":
            pass
        elif import_pattern.match(line):
            imports.append(line)
        else:
            contents.append(line)

    return {"imports": imports, "contents": contents}


def get_exports(lines):

    exports = []

    for line in lines:
        matched = external_variable_w_default_value_pattern.match(line)
        if matched:

            t, vname, value = matched.groups()

            export = {"name": vname.strip("*$"), "value": value}
            exports.append(export)

            continue

        matched = external_variable_pattern.match(line)
        if matched:

            t, vname = matched.groups()

            export = {"name": vname.strip("*$"), "value": None}

            exports.append(export)
            continue

    return exports


def _extract_between_paren(s):

    if "(" in s:
        vname, saver = s.split("(")
    else:
        vname = s
        saver = ""

    saver = saver.replace(")", "")
    return vname, saver


def expand_component_line(line):

    matched = remember_mutablestate_pattern.match(line)
    if matched:

        t, vname, value = matched.groups()

        vname, saver = _extract_between_paren(vname)

        stateSaverString = "" if not saver else f"({saver})"

        return (
            f"{t} {vname} by remember{stateSaverString} {{ mutableStateOf({value}) }}"
        )

    # This needs to come first, and the next 2 blocks dont return but pass it along to `remember` blocks
    matched = external_variable_w_default_value_pattern.match(line)
    if matched:


        t, vname_type, value = matched.groups()
        vname = vname_type.split(":")[0]

        # Dont return, but pass along
        line = f"{t} {vname} = {vname.strip("*$")}"

    matched = external_variable_pattern.match(line)
    if matched:

        t, vname_type = matched.groups()
        vname = vname_type.split(":")[0]

        # Dont return, but pass along
        line =  f"{t} {vname} = {vname.strip("*$")}"


    matched = remembersaveable_mutablestate_pattern.match(line)
    if matched:

        t, vname, value = matched.groups()

        vname, saver = _extract_between_paren(vname)

        stateSaverString = "" if not saver else f"({saver})"
        return f"{t} {vname} by rememberSaveable{stateSaverString} {{ mutableStateOf({value}) }}"

    matched = remember_derivedstateof_pattern.match(line)
    if matched:

        t, vname, value = matched.groups()
        return f"{t} {vname} by remember {{ derivedStateOf {{  {value}  }} }}"

    # matched = remember_derivedstateof_pattern.match(line)
    # if matched:

    #    t, vname, value = matched.groups()
    #    return f"{t} {vname} by remember {{ derivedStateOf {{  {value}  }} }}"


    return line


def analyze_for_imports(lines):
    imports = []

    for line in lines:
        if "rememberSaveable" in line:
            imports.append("import androidx.compose.runtime.saveable.rememberSaveable")
            imports.append("import androidx.compose.runtime.mutableStateOf")
            imports.append("import androidx.compose.runtime.getValue")
            imports.append("import androidx.compose.runtime.setValue")
        elif "remember" in line:
            imports.append("import androidx.compose.runtime.remember")
            imports.append("import androidx.compose.runtime.mutableStateOf")
            imports.append("import androidx.compose.runtime.getValue")
            imports.append("import androidx.compose.runtime.setValue")

    return imports


def cleanup_imports(imports):

    # Dedupes by splitting the `import` statement into components and joining it back together
    # Sorts
    return sorted(set([" ".join(t.strip() for t in x.split()) for x in imports]))
