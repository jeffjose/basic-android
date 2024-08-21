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
