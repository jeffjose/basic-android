#!/usr/bin/env python

import re
import glob
from pathlib import Path

from utils import (
    get_components_dir,
    read_file,
    write_file,
    get_project_namespace,
    get_app_dir,
    mkdir
)
from compiler.parser import parse_component

INPUT_PATTERN = "src/**/**.pine"
TEMPLATE_COMPONENT = """%%PACKAGENAME%%

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import %%NAMESPACE%%.ui.theme.CupcakeTheme

%%IMPORT%%

@Composable
fun %%NAME%%(%%PARAMS%%) {
    %%CONTENT%%
}

/*
@Preview
@Composable
fun %%NAME%%Preview(%%PARAMS%%) {
    CupcakeTheme {
        %%NAME%%(
        )
    }
    }
    */

"""

def collect_files():

    files = [Path(x) for x in glob.glob(INPUT_PATTERN, recursive=True) if Path(x).name != '+screen.pine']

    print("Collected components -")
    print("\n".join([f"- {x}" for x in files]))

    return files


def get_template_component():

    return TEMPLATE_COMPONENT


def get_slug(file):

    name = get_name(file)

    return "".join([n.capitalize() for  n in name.split('-')])



def mkpackage_string_component(output_dir_base):
    return f"package {get_project_namespace()}.ui.components{"." + output_dir_base.replace('/', '.') if output_dir_base != '' else ''}"

def mkexport_string_component(exports):

    s = []

    for e in exports:
        if e['value']:
            s.append(f'{e["name"]}={e["value"]}')
        else:
            s.append(f'{e["name"]}')

    return ", ".join(s)


def get_component_name(slug, include_ext=True):
    return f"{slug}{'.kt' if include_ext else ''}"


def get_output_dir_base(file):

    return str(file.parent).replace('src/components', '').strip('/')

def create_component(template, file):

    output_dir_base = get_output_dir_base(file)

    parcel = parse_component(read_file(file))

    slug = get_slug(file)

    print(mkpackage_string_component(output_dir_base))

    final = (
        template.replace("%%PACKAGENAME%%", mkpackage_string_component(output_dir_base))
        .replace("%%NAMESPACE%%", get_project_namespace())
        .replace("%%IMPORT%%", parcel["imports"])
        .replace("%%CONTENT%%", parcel["contents"])
        .replace("%%NAME%%", slug)
        .replace("%%PARAMS%%", mkexport_string_component(parcel["exports"]))
        .strip()
    )

    mkdir(get_components_dir() / output_dir_base)
    write_file(get_components_dir() / output_dir_base / get_component_name(slug), final)


def get_name(file):
    
    return file.stem

def get_route(file, routable=False):
    route = str(file.parent).replace("src/routes", "")

    # Handle top-level
    if route == "":
        route = "/"

    if routable:
        route = route.replace("[", "{").replace("]", "}")

    return route


def get_composable(template_composable, file):

    route = get_route(file, routable=True)

    return template_composable.replace("%%ROUTE%%", route).replace(
        "%%NAME%%", get_slug(file)
    )


def get_static_route_files(files):

    return files
    # return [x for x in files if not x.parent.name.startswith("[")]


def get_param_route_files(files):

    return [x for x in files if x.parent.name.startswith("[")]


def main():
    files = collect_files()

    template_screen = get_template_component()

    print("Working on components")

    for file in get_static_route_files(files):
        create_component(template_screen, file)

    #template_composable = get_template_composable()
    #template_navigation = get_template_navigation()

    #create_navigation(template_navigation, template_composable, files)

if __name__ == "__main__":
    main()
