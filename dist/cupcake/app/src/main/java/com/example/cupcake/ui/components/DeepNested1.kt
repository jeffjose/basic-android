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
import com.example.cupcake.ui.components.DeepNested2
import com.example.cupcake.utils.*
import com.example.cupcake.utils.PineRender



@Composable
fun DeepNested1(content: @Composable() (() -> Unit)? = null, ) {

    


println("1. Render")

var first : Int by rememberSaveable { mutableStateOf(0) }

LaunchedEffect(true) {
  println("1. onCreate")
}

PineRender {
  Button(onClick={

    println("----")

    first = first + 1
    }) {
    Text( text = "1: $first")
  }


            fun _set_first(value: Int) {
                first = value
                
            }
              DeepNested2(_set_first_incoming_=::_set_first, first=first)
}


// 
// Open app
//   [routes/+screen.pine]: Top
//   1. Render
//    2. Render
//     3. Render
//     3. bottom
//   [routes/+screen.pine]: onCreate
//   1. onCreate
//    2. onCreate
//     3. onCreate

// 
// Click DeepNested1 Button
//    2. Render
//     3. Render
//     3. bottom


// 
// Click DeepNested2 Button
//    2. Render
//     3. Render
//     3. bottom
//    2. Render
//     3. Render
//     3. bottom


// 
// Click DeepNested3 Button
//     3. Render
//     3. bottom
//    2. Render
//     3. Render
//     3. bottom

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