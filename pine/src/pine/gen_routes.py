#!/usr/bin/env python

import re
import glob
from pathlib import Path

from pine.utils import (
    get_screen_dir,
    read_file,
    write_file,
    get_project_namespace,
    get_app_dir,
)

from pine.compiler.parser import parse_component

ROUTE_PARAM_REGEX = re.compile(r"\[(.*?)\]")

INPUT_PATTERN = "src/routes/**/+screen.pine"


TEMPLATE_COMPOSABLE = """
    composable(
            route = "%%ROUTE%%",
            ) { backStackEntry -> 


      %%NAME%%Screen(navController = navController, params = backStackEntry.arguments, http = http)
      }
"""

DEFAULT_IMPORTS = [
    "import androidx.compose.runtime.Composable",
    "import androidx.navigation.NavHostController",
    "import androidx.compose.ui.tooling.preview.Preview",
    "import androidx.navigation.compose.rememberNavController",
    "import android.os.Bundle",
    "import io.ktor.client.*",
    "import io.ktor.client.engine.cio.*",
    "import io.ktor.client.request.*",
    "import io.ktor.client.statement.*",
    "import io.ktor.client.call.body",
    "import io.ktor.client.plugins.contentnegotiation.*",
    "import io.ktor.serialization.kotlinx.json.*",
    "import kotlinx.serialization.*",
    "import kotlinx.serialization.json.*",
    "import androidx.compose.runtime.getValue",
    "import androidx.compose.runtime.mutableStateOf",
    "import androidx.compose.runtime.setValue",
    "import androidx.compose.runtime.rememberCoroutineScope",
    "import androidx.compose.runtime.remember",
    "import androidx.compose.runtime.LaunchedEffect",
]

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
fun Navigation(navController: NavHostController) {

    val http = HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }

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

TEMPLATE_SCREEN = """%%PACKAGENAME%%

import %%NAMESPACE%%.ui.theme.CupcakeTheme

%%IMPORT%%


@Composable
fun %%NAME%%Screen(navController: NavHostController, params: Bundle?, http: HttpClient) {


    /*
    suspend fun getData() : HttpResponse {
        val url = "https://jsonplaceholder.typicode.com/todos"

        return http.get(url)
    }

        val scope = rememberCoroutineScope()
        val data  = remember { mutableStateOf<HttpResponse?>(null)}

        LaunchedEffect(scope) {
            data.value = getData()
    }
    */


    %%CONTENT%%
}

/*
@Preview
@Composable
fun %%NAME%%ScreenPreview() {
    CupcakeTheme {
        %%NAME%%Screen(
            navController = rememberNavController(),
            params = Bundle?
        )
    }
    }
    */

"""


def collect_files():

    files = sorted([Path(x) for x in glob.glob(INPUT_PATTERN, recursive=True)])

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


def mkpackage_string_screen():
    return f"package {get_project_namespace()}.ui"


def mkpackage_string():
    return f"package {get_project_namespace()}"


def get_screenfile_name(slug, include_ext=True):
    return f"{slug}Screen{'.kt' if include_ext else ''}"


def create_screen(template, file):

    parcel = parse_component(read_file(file), default_imports=DEFAULT_IMPORTS)

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

    return files
    # return [x for x in files if not x.parent.name.startswith("[")]


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

    # print("Working on param routes")

    # for file in get_param_route_files(files):
    #     create_screen(template_screen, file)


if __name__ == "__main__":
    main()
