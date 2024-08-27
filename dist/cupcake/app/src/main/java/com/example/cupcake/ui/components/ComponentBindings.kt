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
import com.example.cupcake.utils.PineRender



@Composable
fun ComponentBindings(content: @Composable() (() -> Unit)? = null, ) {

    



var first : Int by rememberSaveable { mutableStateOf(0) }
var second : Int by rememberSaveable { mutableStateOf(0) }

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

            fun _set_first(value: Int) {
                first = value
                
            }
            Keypad(value=pin, _set_first_incoming_=::_set_first, first = first, second=second)
//Keypad(value=pin, bind:v = v)


    
}