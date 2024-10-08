package com.example.cupcake.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.cupcake.R
import com.example.cupcake.ui.theme.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import retrofit2.http.GET
import com.google.gson.annotations.SerializedName
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.*
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.saveable.rememberSaveable
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*



@Composable
fun ThirdScreen(navController: NavHostController, modifier: Modifier = Modifier, http: HttpClient) {

    suspend fun getData(): List<Todo> {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
        val url = "https://jsonplaceholder.typicode.com/todos"
        val response: HttpResponse = client.get(url)
        client.close()

        return response.body()
    }

        val scope = rememberCoroutineScope()
        val data = remember { mutableStateOf<List<Todo>>(emptyList<Todo>())}
        LaunchedEffect(scope) {
        data.value = getData()
    }


    //Log.d("XXX: data", data.value.toString())
    //val viewModel: ViewModel2 = viewModel()

    Log.d("XXX", "before")
    Log.d("XXX", data.value.size.toString())
    //Text(data.value.toString())

    //Log.d("XXX", "Loading ${viewModel.loading} ${todos.value.size}")

           LazyColumn(
                      modifier = Modifier.height(100.dp)
                  ) {
                      items(data.value) {

                          Box(
                              modifier = Modifier.fillMaxWidth(),
                          ) {
                              Text(
                                  text = it.title
                              )
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

@Serializable
data class Todo(
    val userId: Int, 

    val id: Int, 

    val title: String, 

    val completed: Boolean
    )
