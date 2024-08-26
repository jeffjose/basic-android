package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.hypercubetools.ktor.moshi.moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*


data class Photo (
  val id: String,
  val author: String,
  val width: Int,
  val height: Int,
  val url: String,
  val download_url: String
)



@Composable
//fun OnMount( @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun OnMount() {

    



val http =
    HttpClient(CIO) {
        install(ContentNegotiation) {
            moshi {
              add(KotlinJsonAdapterFactory())
            }
        }
    }

var photos by rememberSaveable { mutableStateOf(listOf<Photo>()) }

LaunchedEffect(true) {
  println("onMount - A")
  photos = http.get("https://picsum.photos/v2/list?limit=10").body<List<Photo>>()
  println("onMount - C $photos")
}

Text(text = "Photos - ${photos.size}")



    
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