package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview

data class Cat(val id: String, val name: String)

@Composable
//fun Loop( @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun Loop() {


    
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


    
}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun LoopPreview( @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        Loop(
        )
    }
    }
    */