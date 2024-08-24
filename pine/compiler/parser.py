import re
from utils import mksetter

#
# A greedy pattern to capture everything inside paren
saver_pattern = re.compile(r"(\w+)\((.*)\)")

# import androidx.foo.bar.Baz
import_pattern = re.compile(r"import\s+.*")

# external val foo = "bar"
external_variable_w_default_value_pattern = re.compile(
    r"^external\s+(val|var)\s*(.*)\s*=\s*(.*)"
)

# external val foo
external_variable_pattern = re.compile(r"^external\s+(val|var)\s+(.*)")


# Component(bind:foo="bar")
bind_variable_pattern = re.compile(r"bind:(\w+)\s*=\s*\w+")

# var $foo = "bar"
remember_mutablestate_pattern = re.compile(r"(val|var)\s+\$(.*)\s+=\s+(.*)")

# var *foo = "bar"
remembersaveable_mutablestate_pattern = re.compile(r"(val|var)\s+\*(.*)\s+=\s+(.*)")

# Implemented in this file, but not used
# var count = $derived(count * 2)
remember_derivedstateof_pattern = re.compile(
    r"(val|var)\s+(.*)\s+=\s+\$derived\((.*)\)"
)

# Unused
# var count = $effect("bar")
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
        parcel["imports"]
        + analyze_for_imports(lines)
        + frontmatter["imports"]
        + default_imports
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

    # This is hacky. We're checking for `(` instead of having a better/more compilcated regex
    if "(" in s:
        vname, saver = saver_pattern.search(s).groups()
    else:
        vname = s
        saver = ""

    return vname, saver


def expand_component_line(line):

    # This needs to come first, and the next 2 blocks dont return but pass it along to `remember` blocks
    matched = external_variable_w_default_value_pattern.match(line)
    if matched:

        t, vname_type, value = matched.groups()
        vname = vname_type.split(":")[0]

        # Dont return, but pass along
        # The `*` exists so that external variables are rememberSaveable
        line = f"{t} *{_clean_vname(vname)}(inputs=arrayOf({_clean_vname(vname)})) = {_clean_vname(vname)}"

    matched = external_variable_pattern.match(line)
    if matched:

        t, vname_type = matched.groups()
        vname = vname_type.split(":")[0]

        # Dont return, but pass along
        # The `*` exists so that external variables are rememberSaveable
        line = f"{t} *{_clean_vname(vname)}(inputs=arrayOf({_clean_vname(vname)})) = {_clean_vname(vname)}"

    matched = remember_mutablestate_pattern.match(line)
    if matched:

        t, vname, value = matched.groups()

        vname, saver = _extract_between_paren(vname)

        stateSaverString = "" if not saver else f"({saver})"

        return (
            f"{t} {vname} by remember{stateSaverString} {{ mutableStateOf({value}) }}"
        )

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

    matched = bind_variable_pattern.search(line)
    if "bind:" in line:
        vname = matched.groups()[0]

        return line.replace("bind:", f"{mksetter(vname)}=::{mksetter(vname)}, ")

    if matched:

        import pdb

        pdb.set_trace()

        return f"{t} {vname} by remember {{ derivedStateOf {{  {value}  }} }}"

    return line


def _clean_vname(s):
    return s.strip("*$")


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
