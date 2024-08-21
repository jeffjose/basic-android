package com.example.cupcake

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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.Navigation

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
                                                contentDescription =
                                                        stringResource(R.string.back_button)
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
        ) { Navigation(navController) }
}
