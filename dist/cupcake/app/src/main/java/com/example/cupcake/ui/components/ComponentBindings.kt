package com.example.cupcake.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.cupcake.ui.components.Keypad



@Composable
fun ComponentBindings() {
    



var pin by rememberSaveable { mutableStateOf(0) }

fun handleSubmit() {
  println("You entererd $pin")
}

Text("$pin")
Keypad(value=pin)

}

/*
@Preview
@Composable
fun ComponentBindingsPreview() {
    CupcakeTheme {
        ComponentBindings(
        )
    }
    }
    */