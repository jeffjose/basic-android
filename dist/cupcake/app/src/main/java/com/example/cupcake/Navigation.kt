package com.example.cupcake

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cupcake.ui.AboutScreen
import com.example.cupcake.ui.BlogNestedScreen
import com.example.cupcake.ui.BlogRouteParamIdScreen
import com.example.cupcake.ui.BlogScreen
import com.example.cupcake.ui.RootScreen
import com.example.cupcake.ui.SettingsGeneralScreen
import com.example.cupcake.ui.SettingsScreen
import com.example.cupcake.ui.SettingsSecurityScreen

@Composable
fun Navigation(navController: NavHostController) {

  NavHost(
          navController = navController,
          startDestination = "/",
          modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
  ) {
    composable(route = "/") {
      RootScreen(
              navController = navController,
      )
    }

    composable(route = "/settings") {
      SettingsScreen(
              navController = navController,
      )
    }

    composable(route = "/settings/security") {
      SettingsSecurityScreen(
              navController = navController,
      )
    }

    composable(route = "/settings/general") {
      SettingsGeneralScreen(
              navController = navController,
      )
    }

    composable(route = "/about") {
      AboutScreen(
              navController = navController,
      )
    }

    composable(route = "/blog") {
      BlogScreen(
              navController = navController,
      )
    }

    composable(
            route = "/blog/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) { backStackEntry ->
      backStackEntry.arguments?.getString("id")?.let { id ->
        BlogRouteParamIdScreen(navController = navController, id = id)
      }
    }

    composable(route = "/blog/nested") { BlogNestedScreen(navController = navController) }
  }
}
