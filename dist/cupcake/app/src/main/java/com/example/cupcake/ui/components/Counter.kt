package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

// Regular. Changes dont trigger update
var a = 0

// proposed: remember
var &b = 0

// old: remember
// proposed: mutableState
var c by remember { mutableStateOf(0) }

// rememberSaveable
var d by rememberSaveable { mutableStateOf(0) }

var count by mutableStateOf(0)

val scope = rememberCoroutineScope()

var ticks by rememberSaveable { mutableStateOf(0) }

suspend fun start() {
  println("[components/counter.pine]: LE $ticks")
  ticks  = 0
  while(true) {
  println("[components/counter.pine]: while $ticks")
      delay(1.seconds)
      ticks++
  }
}

scope.launch {
  start()
}


//Text(text="ticks: $ticks")
PineRender {
Text(text="Count: $count ticks: ${ticks}")

Button(onClick={
println("[components/counter.pine]: click $count")
    count = count + 1
  }) {
    Text(text="Click Me")
}

}


    
}