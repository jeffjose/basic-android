package com.example.cupcake.ui

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import io.ktor.client.request.*
import androidx.compose.runtime.setValue
import androidx.compose.material3.Text
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.call.body
import androidx.compose.foundation.layout.fillMaxWidth
import com.example.cupcake.R
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import kotlinx.serialization.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import io.ktor.client.plugins.contentnegotiation.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxHeight
import io.ktor.client.statement.*
import kotlinx.serialization.json.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.res.stringResource


@Composable
fun BlogNestedScreen(navController: NavHostController, params: Bundle?, http: HttpClient) {


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