package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.components.Keypad
import com.example.cupcake.utils.*
import kotlinx.coroutines.flow.MutableStateFlow


class ViewModel {
    var first = MutableStateFlow(0)
    var second = MutableStateFlow(0)
}

@Composable
//fun ComponentBindings( @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun ComponentBindings() {
    


val viewModel = remember { ViewModel() } // or viewModel() etc.
var (first, setFirst) = viewModel.first.collectAsMutableState()
//var (second, setSecond) = viewModel.second.collectAsMutableState()

var second by rememberSaveable { mutableStateOf(0) }



var pin by rememberSaveable { mutableStateOf("") }

fun handleSubmit() {
  println("You entererd $pin")
}

Text("OUTSIDE: pin=$pin first=$first second=$second")
    
Button(onClick={
  first = first + 1
  setFirst(first)
  }) {
Text( text = "OUTSIDE (2way): $first")
}

Button(onClick={
  second = second + 1
  }) {
Text( text = "OUTSIDE (1way): $second")
}
Keypad(value=pin, first = first, second=second, _params = arrayOf("first" to setFirst))
//Keypad(value=pin, bind:v = v)

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
