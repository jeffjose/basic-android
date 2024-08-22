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
import androidx.compose.runtime.LaunchedEffect



@Composable
fun Keypad(first: Int=0, second: Int=0, value: String="",  setFirst : ((Int) -> Unit)? = null, setSecond: ((Int) -> Unit)? = null) {
//fun Keypad(first: Int=0, second: Int=0, value: String="", setFirst: (Int) -> Unit, ) {
var first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }
var second by rememberSaveable(inputs=arrayOf(second)) { mutableStateOf(second) }

println("first=$first, second=$second")

var value by rememberSaveable(inputs=arrayOf(value)) { mutableStateOf(value) }

//var setFirst  = setFirst
LaunchedEffect(first) {
  println("launchedeffect: setFirst($first)")
  //params.get("first")?.invoke(first)
  setFirst?.invoke(first)
  
}

LaunchedEffect(second) {
  println("2. launchedeffect: setSecond($second)")
  //params.get("second")?.invoke(first)
  setSecond?.invoke(second)
  
}


fun select(num: Int) {
  value = Regex("\\w").replace(value, "x") + num.toString()
  println("Here $num $value ")
}

//println("INSIDE - params - $params")

Text(text="INSIDE: value=$value first=$first second=$second")

Button(onClick={
  first = first + 1
  //setFirst(first)
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
