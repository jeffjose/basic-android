package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.utils.*
import com.example.cupcake.utils.PineRender



@Composable
fun DeepNested3(first: Int, _set_first_incoming_ : (( Int) -> Unit)? = null, content: @Composable() (() -> Unit)? = null, ) {

    

println("  3. Render")
var first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }

LaunchedEffect(true) {
  println("  3. onCreate")
}


PineRender {
    
LaunchedEffect(first) {
    _set_first_incoming_?.invoke(first)
}

   
  Button(onClick={

    println("----")

    first = first + 1
    }) {
    Text( text = "3: $first")
  }
}

fun _pine_disposable_fun() {
}

    // onDestroy
    val _pine_disposable_state by remember {mutableStateOf(true)}

    DisposableEffect(_pine_disposable_state) {
    onDispose {
        _pine_disposable_fun()
        }
    }

}