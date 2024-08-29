package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.utils.PineRender



@Composable
fun ComputedBad(content: @Composable() (() -> Unit)? = null, ) {

    


println("[components/computed-bad.pine]: Top")

var count by rememberSaveable { mutableStateOf(0) }
val doubled  = count * 2

LaunchedEffect(true) {
  println("[components/computed-bad.pine]: onCreate")
}


PineRender {
    
   
  Text("Computed Bad")

  Text("count=$count, doubled=$doubled")

  Button(onClick={count = count + 1}) {
    Text("count++")
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