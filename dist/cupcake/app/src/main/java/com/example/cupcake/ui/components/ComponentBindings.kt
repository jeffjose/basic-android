package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.example.cupcake.ui.components.Keypad
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow



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


class ViewModel {
    val v = MutableStateFlow("")
}


val viewModel = remember { ViewModel() } // or viewModel() etc.
val (v, setV) = viewModel.v.collectAsMutableState()



var pin by rememberSaveable { mutableStateOf("") }

fun handleSubmit() {
  println("You entererd $pin")
}

Text("OUTSIDE: pin=$pin v=$v")
Keypad(value=pin, v = v, setV = setV)

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