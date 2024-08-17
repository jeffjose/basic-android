package com.example.cupcake.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect


import com.example.cupcake.ui.theme.CupcakeTheme

import android.util.Log
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
import com.example.cupcake.ui.components.Coffee
import kotlinx.coroutines.launch


@Composable
fun RootScreen(navController: NavHostController, params: Bundle?, http: HttpClient) {


    /*
    suspend fun getData() : HttpResponse {
        val url = "https://jsonplaceholder.typicode.com/todos"

        return http.get(url)
    }

        val scope = rememberCoroutineScope()
        val data  = remember { mutableStateOf<HttpResponse?>(null)}

        LaunchedEffect(scope) {
            data.value = getData()
    }
    */


    

//Log.d("XXX", data.value?.toString() ?: "default")

Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()
                                        .padding(dimensionResource(R.dimen.padding_medium))
        ) {
        Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {

                Coffee()

        }

        Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement =
                        Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        Button(
                                onClick = { navController.navigate("/") },
                        ) { Text(stringResource(R.string.home)) }

                        Button(
                                onClick = { navController.navigate("/blog") },
                        ) { Text(stringResource(R.string.blog)) }

                        Button(
                                onClick = { navController.navigate("/settings") },
                        ) { Text(stringResource(R.string.settings)) }

                        Button(
                                onClick = { navController.navigate("/about") },
                        ) { Text(stringResource(R.string.about)) }
                }
        }
}

}

/*
@Preview
@Composable
fun RootScreenPreview() {
    CupcakeTheme {
        RootScreen(
            navController = rememberNavController(),
            params = Bundle?
        )
    }
    }
    */