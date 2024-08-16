package com.example.cupcake.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
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
import retrofit2.http.GET

@Composable
fun ThirdScreen(navController: NavHostController, modifier: Modifier = Modifier) {

    val viewModel: ViewModel2 = viewModel()

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

data class Todo(var userId: Int, var id: Int, var title: String, var completed: Boolean)

// const val BASE_URL = "https://jsonplaceholder.typicode.com/"
//
// interface APIService {
//    @GET("todos") suspend fun getTodos(): Todo
//
//    companion object {
//        var apiService: APIService? = null
//        fun getInstance(): APIService {
//            if (apiService == null) {
//                apiService =
//                        Retrofit.Builder()
//                                .baseUrl(BASE_URL)
//                                .addConverterFactory(GsonConverterFactory.create())
//                                .build()
//                                .create(APIService::class.java)
//            }
//            return apiService!!
//        }
//    }
// }

interface ApiService2 {
    @GET("todos") fun getTodos(): Call<List<Todo>>
}

object RetrofitInstance {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    val apiService: ApiService2 by lazy { retrofit.create(ApiService2::class.java) }
}

// class TodoViewModel : ViewModel() {
//    private val _todoList = mutableStateListOf<Todo>()
//    var errorMessage: String by mutableStateOf("")
//    val todoList: List<Todo>
//        get() = _todoList
//
//    fun getTodoList() {
//        viewModelScope.launch {
//            val apiService = APIService.getInstance()
//            try {
//                _todoList.clear()
//                _todoList.addAll(apiService.getTodos())
//            } catch (e: Exception) {
//                errorMessage = e.message.toString()
//            }
//        }
//    }
// }

class ViewModel2 : ViewModel() {
    var loading: Boolean by mutableStateOf(false)
    private val _todos: MutableStateFlow<List<Todo>> = MutableStateFlow(listOf())
    val todos: StateFlow<List<Todo>> = _todos

    init {
        getTodosData()
    }

    private fun getTodosData() {
        viewModelScope.launch {
            loading = true
            val call: Call<List<Todo>> = RetrofitInstance.apiService.getTodos()
            call.enqueue(
                    object : Callback<List<Todo>> {
                        override fun onResponse(
                                call: Call<List<Todo>>,
                                response: Response<List<Todo>>
                        ) {
                            if (response.isSuccessful) {
                                // val responseData: List<Todo>? = response.body()?.data
                                val responseData: List<Todo>? = response.body()
                                if (responseData != null) {
                                    _todos.value = responseData
                                }

                                loading = false
                            } else {
                                loading = false
                            }
                        }

                        override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                            loading = false
                        }
                    }
            )
        }
    }
}
