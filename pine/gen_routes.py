#!/usr/bin/env python

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

INPUT_PATTERN = "src/routes/**/*.pine"
TEMPLATE_SCREEN = """%%NAMESPACE%%

%%IMPORT%%

@Composable
fun %%NAME%%Screen(navController: NavHostController, modifier: Modifier = Modifier) {
    %%CONTENT%%
}

@Preview
@Composable
fun %%NAME%%ScreenPreview() {
    CupcakeTheme {
        %%NAME%%Screen(
                navController = rememberNavController(),
                modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}

"""

TEMPLATE_NAVIGATION = """%%NAMESPACE%%

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
              modifier = Modifier.fillMaxHeight().padding(dimensionResource(R.dimen.padding_medium))
      )
    }
"""


def collect_files():

    files = [Path(x) for x in glob.glob(INPUT_PATTERN)]
    print(files)

    return files


def get_template_screen():

    return TEMPLATE_SCREEN


def get_template_navigation():

    return TEMPLATE_NAVIGATION


def get_template_composable():

    return TEMPLATE_COMPOSABLE


def get_slug(file):

    return file.parent.name.capitalize()


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
        template.replace("%%NAMESPACE%%", mkpackage_string_screen())
        .replace("%%IMPORT%%", parcel["imports"])
        .replace("%%CONTENT%%", parcel["contents"])
        .replace("%%NAME%%", slug)
        .strip()
    )

    write_file(get_screen_dir() / Path(get_screenfile_name(slug)), final)


def get_composable(template_composable, file):

    route = str(file.parent).replace("src/routes", "")

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
        template_navigation.replace("%%NAMESPACE%%", mkpackage_string())
        .replace("%%IMPORTS%%", "\n".join(imports))
        .replace("%%COMPOSABLES%%", "\n".join(composables))
        .strip()
    )

    print(final)
    write_file(get_app_dir() / "Navigation.kt", final)


def main():
    files = collect_files()

    template_screen = get_template_screen()

    for file in files:
        create_screen(template_screen, file)

    template_composable = get_template_composable()
    template_navigation = get_template_navigation()

    create_navigation(template_navigation, template_composable, files)


if __name__ == "__main__":
    main()
