package com.example.cupcake.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.cupcake.R
import com.example.cupcake.ui.theme.*

@Composable
fun ThirdScreen(navController: NavHostController, modifier: Modifier = Modifier) {

    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) {
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
                        onClick = { navController.navigate("/second") },
                ) { Text(stringResource(R.string.two)) }

                Button(
                        onClick = { navController.navigate("/third") },
                ) { Text(stringResource(R.string.three)) }
            }
        }
    }
}

@Preview
@Composable
fun ThirdScreenPreview() {
    CupcakeTheme {
        ThirdScreen(
                navController = rememberNavController(),
                modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}
