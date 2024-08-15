#!/usr/bin/env python

import re
import glob
from pathlib import Path

from patterns import import_pattern

from utils import (
    get_screen_dir,
    read_file,
    write_file,
    get_project_namespace,
    get_app_dir,
)

ROUTE_PARAM_REGEX = re.compile(r"\[(.*?)\]")

INPUT_PATTERN = "src/routes/**/*.pine"
TEMPLATE_SCREEN = """%%PACKAGENAME%%

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import %%NAMESPACE%%.ui.theme.CupcakeTheme

%%IMPORT%%


@Composable
fun %%NAME%%Screen(navController: NavHostController) {
    %%CONTENT%%
}

@Preview
@Composable
fun %%NAME%%ScreenPreview() {
    CupcakeTheme {
        %%NAME%%Screen(
                navController = rememberNavController(),
        )
    }
}

"""

TEMPLATE_NAVIGATION = """%%PACKAGENAME%%

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

%%IMPORTS%%

@Composable
fun Navigation(navController: NavHostController) {

  NavHost(
          navController = navController,
          startDestination = "/",
          modifier =
                  Modifier.fillMaxSize().verticalScroll(rememberScrollState())
  ) {

    %%COMPOSABLES%%
  }
}

"""

TEMPLATE_COMPOSABLE = """
    composable(route = "%%ROUTE%%") {
      %%NAME%%Screen(
              navController = navController,
      )
    }
"""


def collect_files():

    files = [Path(x) for x in glob.glob(INPUT_PATTERN, recursive=True)]

    print("Collected routes -")
    print("\n".join([f"- {x}" for x in files]))

    return files


def get_template_screen():

    return TEMPLATE_SCREEN


def get_template_navigation():

    return TEMPLATE_NAVIGATION


def get_template_composable():

    return TEMPLATE_COMPOSABLE


def get_slug(file):

    route = get_route(file)

    if route == "/":
        route = "root"

    route = (
        "".join([x.capitalize() for x in route.strip("/").split("/")])
        #  .replace("[", "RouteParam")
        #  .replace("]", "")
    )
    route = ROUTE_PARAM_REGEX.sub(
        lambda x: f"RouteParam{x.group(1).capitalize()}", route
    )

    return route


def parse(data):
    lines = data.split("\n")

    imports = []
    contents = []

    for line in lines:
        if import_pattern.match(line):
            imports.append(line)
        else:
            contents.append(line)

    return {"imports": "\n".join(imports), "contents": "\n".join(contents)}


def mkpackage_string_screen():
    return f"package {get_project_namespace()}.ui"


def mkpackage_string():
    return f"package {get_project_namespace()}"


def get_screenfile_name(slug, include_ext=True):
    return f"{slug}Screen{'.kt' if include_ext else ''}"


def create_screen(template, file):

    parcel = parse(read_file(file))

    slug = get_slug(file)

    final = (
        template.replace("%%PACKAGENAME%%", mkpackage_string_screen())
        .replace("%%NAMESPACE%%", get_project_namespace())
        .replace("%%IMPORT%%", parcel["imports"])
        .replace("%%CONTENT%%", parcel["contents"])
        .replace("%%NAME%%", slug)
        .strip()
    )

    write_file(get_screen_dir() / Path(get_screenfile_name(slug)), final)


def get_route(file):
    route = str(file.parent).replace("src/routes", "")

    # Handle top-level
    if route == "":
        route = "/"

    return route


def get_composable(template_composable, file):

    route = get_route(file)

    return template_composable.replace("%%ROUTE%%", route).replace(
        "%%NAME%%", get_slug(file)
    )


def create_navigation(template_navigation, template_composable, files):

    slugs = [get_slug(file) for file in files]
    filenames = [get_screenfile_name(slug, include_ext=False) for slug in slugs]

    imports = [
        f"import {get_project_namespace()}.ui.{filename}" for filename in filenames
    ]

    composables = [get_composable(template_composable, file) for file in files]

    final = (
        template_navigation.replace("%%PACKAGENAME%%", mkpackage_string())
        .replace("%%IMPORTS%%", "\n".join(imports))
        .replace("%%COMPOSABLES%%", "\n".join(composables))
        .strip()
    )

    write_file(get_app_dir() / "Navigation.kt", final)


def get_static_route_files(files):

    return [x for x in files if not x.parent.name.startswith("[")]


def get_param_route_files(files):

    return [x for x in files if x.parent.name.startswith("[")]


def main():
    files = collect_files()

    template_screen = get_template_screen()

    ### Static routes
    print("Working on static routes")

    for file in get_static_route_files(files):
        create_screen(template_screen, file)

    template_composable = get_template_composable()
    template_navigation = get_template_navigation()

    create_navigation(template_navigation, template_composable, files)

    print("Working on param routes")

    for file in get_param_route_files(files):
        create_screen(template_screen, file)


if __name__ == "__main__":
    main()
