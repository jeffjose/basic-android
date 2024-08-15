package com.example.cupcake.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.ui.res.stringResource
import com.example.cupcake.R
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.res.dimensionResource
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement


@Composable
fun BlogNestedScreen(navController: NavHostController, params: Bundle?) {
    

Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()
                                        .padding(dimensionResource(R.dimen.padding_medium))

) {

        Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {}

    Text(stringResource(R.string.blogpost))


}

}

/*
@Preview
@Composable
fun BlogNestedScreenPreview() {
    CupcakeTheme {
        BlogNestedScreen(
            navController = rememberNavController(),
            params = Bundle?
        )
    }
    }
    */