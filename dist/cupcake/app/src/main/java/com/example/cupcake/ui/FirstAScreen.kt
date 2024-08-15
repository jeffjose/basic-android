package com.example.cupcake.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.ui.res.stringResource
import com.example.cupcake.R
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text


@Composable
fun FirstAScreen(navController: NavHostController) {
    
Column() {

  Button(onClick={}) {
    Text(stringResource(R.string.foobar))

  }

}

}

@Preview
@Composable
fun FirstAScreenPreview() {
    CupcakeTheme {
        FirstAScreen(
                navController = rememberNavController(),
        )
    }
}