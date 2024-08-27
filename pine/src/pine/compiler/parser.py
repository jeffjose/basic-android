import re
from pine.utils import mksetter, mksetter_incoming


# content()
content_pattern = re.compile(r"^content\(\)$")

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
remember_mutablestate_pattern = re.compile(r"(val|var)\s+\$(.*)\s*=\s*(.*)")

# var *foo = "bar"
remembersaveable_mutablestate_pattern = re.compile(r"(val|var)\s+\*(.*)\s*=\s*(.*)")

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

    # This needs to come before `expand_component_lines`
    # since one of the things expand_compomnent_lines
    # does is modify `bind:var` lines
    vars = get_var_declarations(lines)

    lines = [expand_component_line(x, vars, exports) for x in lines]

    parcel = get_imports_and_contents(lines)

    imports = cleanup_imports(
        parcel["imports"]
        + add_imports_for_remember_var_declarations(lines)
        + frontmatter["imports"]
        + default_imports
    )

    return {
        "imports": "\n".join(imports),
        "exports": exports,
        "contents": "\n".join(parcel["contents"]),
        "frontmatter": "\n".join(frontmatter["frontmatter"]),
        "vars": vars,
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


def get_var_declarations(lines):

    vars = []

    for line in lines:

        # external val foo = "bar"
        matched = external_variable_w_default_value_pattern.match(line)
        if matched:

            t, vname_type, value = matched.groups()
            try:
                vname, type = [x.strip("*$ ") for x in vname_type.split(":")]
            except:
                vname = vname_type
                type = None

            var = {"vname": vname, "type": type, "line": line}
            vars.append(var)
            continue

        # external val foo
        matched = external_variable_pattern.match(line)
        if matched:

            t, vname_type = matched.groups()
            try:
                vname, type = [x.strip("*$ ") for x in vname_type.split(":")]
            except:
                vname = vname_type
                type = None

            var = {"vname": vname, "type": type, "line": line}
            vars.append(var)
            continue

        # var $foo = "bar"
        matched = remember_mutablestate_pattern.match(line)
        if matched:

            t, vname, value = matched.groups()

            # This section is updated (newer) than expand_component_line
            # because we need `type` info
            vname_type, saver = _extract_between_paren(vname)
            try:
                vname, type = [x.strip("*$ ") for x in vname_type.split(":")]
            except:
                vname = vname_type
                type = None

            var = {"vname": vname, "type": type, "line": line}
            vars.append(var)
            continue

        # var *foo = "bar"
        matched = remembersaveable_mutablestate_pattern.match(line)
        if matched:

            t, vname, value = matched.groups()

            # This section is updated (newer) than expand_component_line
            # because we need `type` info
            vname_type, saver = _extract_between_paren(vname)
            try:
                vname, type = [x.strip("*$ ") for x in vname_type.split(":")]
            except:
                vname = vname_type
                type = None

            var = {"vname": vname, "type": type, "line": line}
            vars.append(var)
            continue

    return vars


def get_exports(lines):

    exports = []

    for line in lines:

        matched = external_variable_w_default_value_pattern.match(line)

        if matched:

            t, vname_type, value = matched.groups()

            try:
                vname, type = vname_type.split(":")
            except:
                vname = vname_type
                type = None

            export = {
                "name": vname_type.strip(" *$"),
                "value": value,
                "vname": vname.strip("$* "),
                # FIXME: remove `type` being None. And raise an error
                "type": type.strip() if type else None,
            }
            exports.append(export)

            continue

        matched = external_variable_pattern.match(line)
        if matched:

            t, vname_type = matched.groups()

            try:
                vname, type = vname_type.split(":")
            except:
                vname = vname_type
                type = None

            export = {
                "name": vname_type.strip(" *$"),
                "value": None,
                "vname": vname.strip("$* "),
                # FIXME: remove `type` being None. And raise an error
                "type": type.strip() if type else None,
            }

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


def expand_component_line(line, vars, exports):

    # This needs to come first, and the next 2 blocks dont return but pass it along to `remember` blocks

    # external val foo = "bar"
    matched = external_variable_w_default_value_pattern.match(line)
    if matched:

        t, vname_type, value = matched.groups()
        vname = vname_type.split(":")[0]

        # Dont return, but pass along
        # The `*` exists so that external variables are rememberSaveable
        line = f"{t} *{_clean_vname(vname)}(inputs=arrayOf({_clean_vname(vname)})) = {_clean_vname(vname)}"

    # external val foo
    matched = external_variable_pattern.match(line)
    if matched:

        t, vname_type = matched.groups()
        vname = vname_type.split(":")[0]

        # Dont return, but pass along
        # The `*` exists so that external variables are rememberSaveable
        line = f"{t} *{_clean_vname(vname)}(inputs=arrayOf({_clean_vname(vname)})) = {_clean_vname(vname)}"

    # var $foo = "bar"
    matched = remember_mutablestate_pattern.match(line)
    if matched:

        t, vname, value = matched.groups()

        vname_type, saver = _extract_between_paren(vname)
        try:
            vname, type = [x.strip() for x in vname_type.split(":")]
        except:
            vname = vname_type
            type = None

        stateSaverString = "" if not saver else f"({saver.strip()})"

        return f"{t} {_clean_vname(vname)}{' : ' + type if type else ''} by remember{stateSaverString} {{ mutableStateOf({value}) }}"

    # var *foo = "bar"
    matched = remembersaveable_mutablestate_pattern.match(line)
    if matched:

        t, vname, value = matched.groups()

        vname_type, saver = _extract_between_paren(vname)
        try:
            vname, type = [x.strip() for x in vname_type.split(":")]
        except:
            vname = vname_type
            type = None

        stateSaverString = "" if not saver else f"({saver.strip()})"

        # return f"{t} {vname_type} by rememberSaveable{stateSaverString} {{ mutableStateOf({value}) }}"
        return f"{t} {_clean_vname(vname)}{' : ' + type if type else ''} by rememberSaveable{stateSaverString} {{ mutableStateOf({value}) }}"

    # var count = $derived(count * 2)
    matched = remember_derivedstateof_pattern.match(line)
    if matched:

        t, vname, value = matched.groups()
        return f"{t} {vname} by remember {{ derivedStateOf {{  {value}  }} }}"

    # Component(bind:foo="bar")
    matched = bind_variable_pattern.search(line)
    if "bind:" in line and not line.strip().startswith("//"):

        finalsetter = ""
        final = line

        for vname in bind_variable_pattern.findall(line):

            # vname = matched.groups()[0]

            var_declaration = [v for v in vars if v["vname"] == vname][0]
            try:
                [e for e in exports if e["vname"] == vname][0]
            except:
                is_export = False
            else:
                is_export = True

            bindingsetter = f"""
            fun {mksetter(vname)}(value: {var_declaration['type']}) {{
                {vname} = value
                %%INCOMINGSETTER%%
            }}
            """

            finalsetter = finalsetter + bindingsetter.replace(
                "%%INCOMINGSETTER%%",
                f"{mksetter_incoming(vname)}?.invoke({vname})" if is_export else "",
            )

            final = final.replace(
                f"bind:{vname}",
                f"{mksetter_incoming(vname)}=::{mksetter(vname)}, {vname}",
            )

        return finalsetter + final

    # content()
    matched = content_pattern.search(line.strip())
    if matched:

        return "content?.invoke()"

    # if matched:
    #     return f"{t} {vname} by remember {{ derivedStateOf {{  {value}  }} }}"

    return line


def _clean_vname(s):
    return s.strip("*$").strip()


def add_imports_for_remember_var_declarations(lines):
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
