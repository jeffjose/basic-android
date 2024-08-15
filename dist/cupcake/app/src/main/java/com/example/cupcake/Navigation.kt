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

import com.example.cupcake.ui.FirstScreen
import com.example.cupcake.ui.FourthScreen
import com.example.cupcake.ui.SecondScreen
import com.example.cupcake.ui.ThirdScreen

@Composable
fun Navigation(navController: NavHostController) {

  NavHost(
          navController = navController,
          startDestination = "/",
          modifier =
                  Modifier.fillMaxSize().verticalScroll(rememberScrollState())
  ) {

    
    composable(route = "/first") {
      FirstScreen(
              navController = navController,
              modifier = Modifier.fillMaxHeight().padding(dimensionResource(R.dimen.padding_medium))
      )
    }


    composable(route = "/fourth") {
      FourthScreen(
              navController = navController,
              modifier = Modifier.fillMaxHeight().padding(dimensionResource(R.dimen.padding_medium))
      )
    }


    composable(route = "/second") {
      SecondScreen(
              navController = navController,
              modifier = Modifier.fillMaxHeight().padding(dimensionResource(R.dimen.padding_medium))
      )
    }


    composable(route = "/third") {
      ThirdScreen(
              navController = navController,
              modifier = Modifier.fillMaxHeight().padding(dimensionResource(R.dimen.padding_medium))
      )
    }

  }
}