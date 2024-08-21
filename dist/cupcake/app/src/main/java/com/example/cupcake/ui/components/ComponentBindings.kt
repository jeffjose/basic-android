package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.runtime.MutableState
import com.example.cupcake.ui.components.Keypad
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.MutableLiveData
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext
import androidx.compose.material3.Text
import androidx.compose.runtime.setValue
import kotlin.coroutines.EmptyCoroutineContext
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview



@Composable
fun ComponentBindings() {
    


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




var pin by rememberSaveable { mutableStateOf("") }

fun handleSubmit() {
  println("You entererd $pin")
}

Text("pin=$pin")
Keypad(value=pin)

}

/*
@Preview
@Composable
fun ComponentBindingsPreview() {
    CupcakeTheme {
        ComponentBindings(
        )
    }
    }
    */