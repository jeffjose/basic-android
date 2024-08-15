package com.example.cupcake.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.ui.theme.CupcakeTheme




@Composable
fun BlogRouteParamIdRouteParamModeScreen(navController: NavHostController) {
    
}

@Preview
@Composable
fun BlogRouteParamIdRouteParamModeScreenPreview() {
    CupcakeTheme {
        BlogRouteParamIdRouteParamModeScreen(
                navController = rememberNavController(),
        )
    }
}