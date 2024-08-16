/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.ui.FirstScreen
import com.example.cupcake.ui.SecondScreen
import com.example.cupcake.ui.ThirdScreen
import io.ktor.client.*

/** Composable that displays the topBar and displays back button if back navigation is possible. */
@Composable
fun CupcakeAppBar(
        currentScreen: String,
        canNavigateBack: Boolean,
        navigateUp: () -> Unit,
        modifier: Modifier = Modifier
) {
    TopAppBar(
            title = { Text(currentScreen) },
            colors =
                    TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
            modifier = modifier,
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            }
    )
}

@Composable
fun CupcakeApp(navController: NavHostController = rememberNavController()) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = backStackEntry?.destination?.route ?: "/first"

    Scaffold(
            topBar = {
                CupcakeAppBar(
                        currentScreen = currentScreen,
                        canNavigateBack = navController.previousBackStackEntry != null,
                        navigateUp = { navController.navigateUp() }
                )
            }
    ) { innerPadding ->
        NavHost(
                navController = navController,
                startDestination = "/first",
                modifier =
                        Modifier.fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(innerPadding)
        ) {
            composable(route = "/first") {
                FirstScreen(
                        // onNextButtonClicked = {
                        //     // viewModel.setQuantity(it)
                        //     navController.navigate("/second")
                        // },
                        navController = navController,
                        modifier =
                                Modifier.fillMaxHeight()
                                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route = "/second") {
                // val context = LocalContext.current
                SecondScreen(
                        navController = navController,
                        modifier =
                                Modifier.fillMaxHeight()
                                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route = "/third") {
                // val context = LocalContext.current
                ThirdScreen(
                        navController = navController,
                        // onPrevButtonClicked = {
                        //     navController.popBackStack("/first", inclusive = false)
                        // },
                        modifier =
                                Modifier.fillMaxHeight()
                                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
        }
    }
}
