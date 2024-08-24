package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.components.Keypad
import com.example.cupcake.utils.*



@Composable
//fun ComponentBindings( @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun ComponentBindings() {


    



var first by rememberSaveable { mutableStateOf(0) }
var second by rememberSaveable { mutableStateOf(0) }

fun _set_first(value: Int) {
  first = value
}

var pin by rememberSaveable { mutableStateOf("") }

fun handleSubmit() {
  println("You entererd $pin")
}

Text("OUTSIDE: pin=$pin first=$first second=$second")
    
Button(onClick={
  first = first + 1
  }) {
Text( text = "OUTSIDE (2way): $first")
}

Button(onClick={
  second = second + 1
  }) {
Text( text = "OUTSIDE (1way): $second")
}
Keypad(value=pin, _set_first=::_set_first, first = first, second=second)
//Keypad(value=pin, _set_v=::_set_v, v = v)


    
}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun ComponentBindingsPreview( @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        ComponentBindings(
        )
    }
    }
    */