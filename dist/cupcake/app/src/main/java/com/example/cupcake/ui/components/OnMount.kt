package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*



@Composable
//fun OnMount( @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun OnMount() {

    
val http =
    HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

var photos = arrayOf<String>()

LaunchedEffect(true) {
    val response : HttpResponse = http.get("https://picsum.photos/v2/list?limit=10")
}



    
}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun OnMountPreview( @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        OnMount(
        )
    }
    }
    */