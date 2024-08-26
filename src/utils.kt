package com.example.cupcake.utils

import kotlin.coroutines.CoroutineContext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.runtime.State
import kotlin.coroutines.EmptyCoroutineContext
import androidx.compose.runtime.collectAsState

import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData

import com.hypercubetools.ktor.moshi.moshi
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.ktor.client.request.*
import io.ktor.client.call.body
import com.example.cupcake.utils.http


class MutableStateAdapter<T>(
    private val state: State<T>,
    private val mutate: (T) -> Unit
) : MutableState<T> {

    override var value: T
        get() = state.value
        set(value) {
            mutate(value)
        }

    override fun component1(): T = value
    override fun component2(): (T) -> Unit = { value = it }
}

@Composable
fun <T> MutableStateFlow<T>.collectAsMutableState(
    context: CoroutineContext = EmptyCoroutineContext
): MutableState<T> = MutableStateAdapter(
    state = collectAsState(context),
    mutate = { value = it }
)

public val http =
    HttpClient(CIO) {
        install(ContentNegotiation) {
            moshi {
              add(KotlinJsonAdapterFactory())
            }
        }
    }
