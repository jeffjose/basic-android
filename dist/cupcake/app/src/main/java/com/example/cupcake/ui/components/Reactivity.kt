package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.components.ComplexButton
import com.example.cupcake.ui.components.Counter
import com.example.cupcake.utils.PineRender
import kotlinx.coroutines.flow.MutableStateFlow


 class ViewModel {
    val v = MutableStateFlow("")
    val first = MutableStateFlow(0)
    val c = MutableStateFlow(0)
    var signal = MutableStateFlow(0)
 }

@Composable
fun Reactivity(content: @Composable() (() -> Unit)? = null, ) {

    
println("[components/reactivity.pine]: Top")

LaunchedEffect(true) {
  println("[components/reactivity.pine]: onCreate")
}

PineRender {

  Counter()
  Counter()

}

fun _pine_disposable_fun() {
}

    

    // onDetroy
    val _pine_disposable_state by remember {mutableStateOf(true)}

    DisposableEffect(_pine_disposable_state) {
    onDispose {
        _pine_disposable_fun()
        }
    }

}