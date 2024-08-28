package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
fun ReactiveDeclarations(content: @Composable() (() -> Unit)? = null, ) {

    




var count by rememberSaveable { mutableStateOf(1) }
var x = mutableStateOf(0)

//val doubled by remember {derivedStateOf { count * 2}}
//val quadrupled by remember {derivedStateOf { doubled * 2}}

val doubled  by remember { derivedStateOf {  count * 2  } }
val quadrupled  by remember { derivedStateOf {  doubled * 2  } }

// val x = $derived({ doubled * 2 })
// val $x = 0
// val $x = $derived(doubled * 2) 
// val $x = {doubled * 2}


fun handleClick() {
  count++
}


Button(onClick={handleClick()}) {
  Text(text = "Count: $count")
}

  Text(text = "$count * 2 = $doubled")
  Text(text = "$count * 2 = $quadrupled")

fun _pine_disposable_fun() {
}

    

    // on_destroy
    val _pine_disposable_state by remember {mutableStateOf(true)}

    DisposableEffect(_pine_disposable_state) {
    onDispose {
        _pine_disposable_fun()
        }
    }

}