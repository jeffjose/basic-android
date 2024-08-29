package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.utils.PineRender
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun Counter(content: @Composable() (() -> Unit)? = null, ) {

    



println("[components/counter.pine]: Top")

val scope = rememberCoroutineScope()

var count : Int by rememberSaveable { mutableStateOf(0) }

var ticks by rememberSaveable { mutableStateOf(0) }

suspend fun start() {
  ticks  = 0
  while(true) {
  println("[components/counter.pine]: while $ticks")
      delay(1.seconds)
      ticks++
  }
}

LaunchedEffect(true) {
  println("[components/counter.pine]: onCreate")
}

fun _pine_disposable_fun() {
  println("[components/counter.pine]: onDetroy")
}



PineRender {
    
   
  Text(text="ticks: $ticks")
  Text(text="Count: $count ticks: ${ticks}")

  Button(onClick={
  println("[components/counter.pine]: click $count")
      ticks = ticks + 1
    }) {
      Text(text="Click Me")
  }
}


    // onDestroy
    val _pine_disposable_state by remember {mutableStateOf(true)}

    DisposableEffect(_pine_disposable_state) {
    onDispose {
        _pine_disposable_fun()
        }
    }

}