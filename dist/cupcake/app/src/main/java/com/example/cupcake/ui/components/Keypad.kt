package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview



@Composable
//fun Keypad(first: Int=0, second: Int=0, value: String="", setFirst: (Int) -> Unit,  @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun Keypad(first: Int=0, second: Int=0, value: String="", setFirst: (Int) -> Unit, ) {
    
var first = first
var second by rememberSaveable(inputs=arrayOf(second)) { mutableStateOf(second) }
var third: Int = 0
var fourth: Int by rememberSaveable { mutableStateOf(0) }
println("first=$first, second=$second, third=$third, fourth=$fourth")

var value by rememberSaveable { mutableStateOf(value) }

var setFirst = setFirst

fun select(num: Int) {
  value = Regex("\\w").replace(value, "x") + num.toString()
  println("Here $num $value ")
}

//println("INSIDE - params - $params")

Text(text="INSIDE: value=$value first=$first second=$second")

Button(onClick={
  first = first + 1
  setFirst(first)
  }) {
Text( text = "INSIDE (2way): $first")
}

Button(onClick={
  println(second)
  second = second + 1
  println(second)
  }) {

Text( text = "INSIDE (1way): $second")
}


Button(onClick={
  println(third)
  third = third + 1
  println(third)
  }) {

Text( text = "INSIDE var third $third")
}


Button(onClick={
  println(fourth)
  fourth = fourth + 1
  println(fourth)
  }) {

Text( text = "INSIDE var *fourth: $fourth")
}


Row() {
  Button(onClick={select(1)}){Text(text="1")}
  Button(onClick={select(2)}){Text(text="2")}
  Button(onClick={select(3)}){Text(text="3")}

}


Row() {
  Button(onClick={select(4)}){Text(text="4")}
  Button(onClick={select(5)}){Text(text="5")}
  Button(onClick={select(6)}){Text(text="6")}

}


Row() {
  Button(onClick={select(7)}){Text(text="7")}
  Button(onClick={select(8)}){Text(text="8")}
  Button(onClick={select(9)}){Text(text="9")}
}


Row() {
  Button(onClick={}){Text(text="Clear")}
  Button(onClick={}){Text(text="0")}
  Button(onClick={}){Text(text="Submit")}
}

}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun KeypadPreview(first: Int=0, second: Int=0, value: String="", setFirst: (Int) -> Unit,  @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        Keypad(
        )
    }
    }
    */
