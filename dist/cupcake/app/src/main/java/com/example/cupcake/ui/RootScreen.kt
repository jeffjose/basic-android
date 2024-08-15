package com.example.cupcake.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.cupcake.R
import com.example.cupcake.ui.theme.CupcakeTheme


@Composable
fun RootScreen(navController: NavHostController) {
    
Column(
        verticalArrangement = Arrangement.SpaceBetween
        ) {
        Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {}
        Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement =
                        Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        Button(
                                onClick = { navController.navigate("/first") },
                        ) { Text(stringResource(R.string.one)) }
                        Button(
                                onClick = { navController.navigate("/first/a") },
                        ) { Text(stringResource(R.string.foobar)) }

                        Button(
                                onClick = { navController.navigate("/second") },
                        ) { Text(stringResource(R.string.two)) }
                }
        }
}

}

@Preview
@Composable
fun RootScreenPreview() {
    CupcakeTheme {
        RootScreen(
                navController = rememberNavController(),
        )
    }
}