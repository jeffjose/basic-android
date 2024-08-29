package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.utils.PineRender



@Composable
fun ReactiveStatements(content: @Composable() (() -> Unit)? = null, ) {

    
var count by rememberSaveable { mutableStateOf(1) }

fun check() {
  println("The $count is ridiculously high")
}

//LaunchedEffect(true) {
//  println("LE: hi there $count")
//  }
//
SideEffect {println("SE: hi there $count")}

fun handleClick() {
  count++
}




Button(onClick={handleClick()}) {
  Text(text = "RS: Count: $count")
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