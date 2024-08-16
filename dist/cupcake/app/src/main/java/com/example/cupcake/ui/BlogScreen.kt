package com.example.cupcake.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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


@Composable
fun BlogScreen(navController: NavHostController, params: Bundle?) {
    
Column(verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()
                                        .padding(dimensionResource(R.dimen.padding_medium))
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
                                onClick = { navController.navigate("/blog/nested") },
                        ) { Text(stringResource(R.string.nested)) }

                        Button(
                                onClick = { navController.navigate("/blog/123") },
                        ) { Text(stringResource(R.string.blog123)) }
                        Button(
                                onClick = { navController.navigate("/blog/345") },
                        ) { Text(stringResource(R.string.blog345)) }
                }
                }
        }

}

/*
@Preview
@Composable
fun BlogScreenPreview() {
    CupcakeTheme {
        BlogScreen(
            navController = rememberNavController(),
            params = Bundle?
        )
    }
    }
    */