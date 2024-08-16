package com.example.cupcake

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

import com.example.cupcake.ui.RootScreen
import com.example.cupcake.ui.AboutScreen
import com.example.cupcake.ui.SettingsScreen
import com.example.cupcake.ui.SettingsGeneralScreen
import com.example.cupcake.ui.SettingsSecurityScreen
import com.example.cupcake.ui.BlogScreen
import com.example.cupcake.ui.BlogNestedScreen
import com.example.cupcake.ui.BlogRouteParamIdScreen

@Composable
fun Navigation(navController: NavHostController) {

  NavHost(
          navController = navController,
          startDestination = "/",
          modifier =
                  Modifier.fillMaxSize().verticalScroll(rememberScrollState())
  ) {

    
    composable(
            route = "/",
            ) { backStackEntry ->
      RootScreen(navController = navController, params = backStackEntry.arguments)
    }


    composable(
            route = "/about",
            ) { backStackEntry ->
      AboutScreen(navController = navController, params = backStackEntry.arguments)
    }


    composable(
            route = "/settings",
            ) { backStackEntry ->
      SettingsScreen(navController = navController, params = backStackEntry.arguments)
    }


    composable(
            route = "/settings/general",
            ) { backStackEntry ->
      SettingsGeneralScreen(navController = navController, params = backStackEntry.arguments)
    }


    composable(
            route = "/settings/security",
            ) { backStackEntry ->
      SettingsSecurityScreen(navController = navController, params = backStackEntry.arguments)
    }


    composable(
            route = "/blog",
            ) { backStackEntry ->
      BlogScreen(navController = navController, params = backStackEntry.arguments)
    }


    composable(
            route = "/blog/nested",
            ) { backStackEntry ->
      BlogNestedScreen(navController = navController, params = backStackEntry.arguments)
    }


    composable(
            route = "/blog/{id}",
            ) { backStackEntry ->
      BlogRouteParamIdScreen(navController = navController, params = backStackEntry.arguments)
    }

  }
}