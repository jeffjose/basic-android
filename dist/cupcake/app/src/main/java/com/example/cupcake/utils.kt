package com.example.cupcake.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import com.hypercubetools.ktor.moshi.moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class MutableStateAdapter<T>(
    private val state: State<T>,
    private val mutate: (T) -> Unit,
) : MutableState<T> {
    override var value: T
        get() {
            println("get $state")
            return state.value
        }
        set(value) {
            println("set $value")
            mutate(value)
        }

    override fun component1(): T = value

    override fun component2(): (T) -> Unit = { value = it }
}

@Composable
fun <T> MutableStateFlow<T>.collectAsMutableState(context: CoroutineContext = EmptyCoroutineContext): MutableState<T> =
    MutableStateAdapter(
        state = collectAsState(context),
        mutate = { value = it },
    )

public val http =
    HttpClient(CIO) {
        install(ContentNegotiation) {
            moshi {
                add(KotlinJsonAdapterFactory())
            }
        }
    }

@Composable
fun <T> State<T>.onValueChanged(content: @Composable (T) -> Unit) = content(value)


@Composable
fun PineRender(content: @Composable () -> Unit) {
    content()
}
