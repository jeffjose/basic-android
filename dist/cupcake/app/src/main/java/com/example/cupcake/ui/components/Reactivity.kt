package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview



@Composable
//fun Reactivity( @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun Reactivity() {

    
println("[reactivity]: hi")

var count by rememberSaveable { mutableStateOf(0) }

Text(text="Count: $count")

Button(onClick={
  count = count + 1
}) {
  Text(text="Click me")
}



    
}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun ReactivityPreview( @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        Reactivity(
        )
    }
    }
    */