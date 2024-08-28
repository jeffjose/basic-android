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
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.utils.PineRender

data class Cat(val id: String, val name: String)

@Composable
fun Loop(content: @Composable() (() -> Unit)? = null, ) {

    
val cats = listOf(
  Cat(id="123", name="Keyboard Cat"),
  Cat(id="234", name="Smelly Cat"),
  Cat(id="489", name="Alley Cat")
)


Text(text="Famous Cats")

for (cat in cats) {
  Button(onClick={}) {
    Text(text="${cat.name}")
  }
}


for ((i, cat) in cats.withIndex()) {
  Button(onClick={}) {
    Text(text="$i: ${cat.name}")
  }
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