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
import com.example.cupcake.ui.components.DeepNested3
import com.example.cupcake.utils.*
import com.example.cupcake.utils.PineRender



@Composable
fun DeepNested2(first: Int, _set_first_incoming_ : (( Int) -> Unit)? = null, content: @Composable() (() -> Unit)? = null, ) {

    



println(" 2. top")
var first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }

PineRender {
  Button(onClick={
    first = first + 1
    }) {
    Text( text = "2: $first")
  }


            fun _set_first(value: Int) {
                first = value
                _set_first_incoming_?.invoke(first)
            }
              DeepNested3(_set_first_incoming_=::_set_first, first=first)
}

fun _pine_disposable_fun() {
}

    
LaunchedEffect(first) {
    _set_first_incoming_?.invoke(first)
}


    // onDetroy
    val _pine_disposable_state by remember {mutableStateOf(true)}

    DisposableEffect(_pine_disposable_state) {
    onDispose {
        _pine_disposable_fun()
        }
    }

}