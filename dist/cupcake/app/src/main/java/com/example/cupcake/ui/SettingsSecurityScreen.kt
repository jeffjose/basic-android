package com.example.cupcake.ui

import com.example.cupcake.ui.theme.CupcakeTheme

import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.R
import com.example.cupcake.utils.PineRender
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*


@Composable
fun SettingsSecurityScreen(navController: NavHostController, params: Bundle?, http: HttpClient) {


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


    
Column(
    verticalArrangement = Arrangement.SpaceBetween,
    modifier =
        Modifier
            .fillMaxHeight()
            .padding(dimensionResource(R.dimen.padding_medium)),
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
    ) {}

    Button(
        onClick = { navController.navigate("/settings/security") },
    ) { Text(stringResource(R.string.security)) }
}

fun _pine_disposable_fun() {
}

    // onDetroy
    val _pine_disposable_state by remember {mutableStateOf(true)}

    DisposableEffect(_pine_disposable_state) {
    onDispose {
        _pine_disposable_fun()
        }
    }
}

/*
@Preview
@Composable
fun SettingsSecurityScreenPreview() {
    CupcakeTheme {
        SettingsSecurityScreen(
            navController = rememberNavController(),
            params = Bundle?
        )
    }
    }
    */