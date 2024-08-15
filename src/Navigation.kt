package com.example.cupcake

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.ui.FirstScreen
import com.example.cupcake.ui.SecondScreen
import com.example.cupcake.ui.ThirdScreen
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.res.dimensionResource
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.fillMaxSize

@Composable
fun Navigation(navController: NavHostController) {

  NavHost(
          navController = navController,
          startDestination = "/first",
          modifier =
                  Modifier.fillMaxSize().verticalScroll(rememberScrollState())
  ) {
    composable(route = "/first") {
      FirstScreen(
              // onNextButtonClicked = {
              //     // viewModel.setQuantity(it)
              //     navController.navigate("/second")
              // },
              navController = navController,
              modifier = Modifier.fillMaxHeight().padding(dimensionResource(R.dimen.padding_medium))
      )
    }
    composable(route = "/second") {
      // val context = LocalContext.current
      SecondScreen(
              navController = navController,
              modifier = Modifier.fillMaxHeight().padding(dimensionResource(R.dimen.padding_medium))
      )
    }
    composable(route = "/third") {
      // val context = LocalContext.current
      ThirdScreen(
              navController = navController,
              // onPrevButtonClicked = {
              //     navController.popBackStack("/first", inclusive = false)
              // },
              modifier = Modifier.fillMaxHeight().padding(dimensionResource(R.dimen.padding_medium))
      )
    }
  }
}
