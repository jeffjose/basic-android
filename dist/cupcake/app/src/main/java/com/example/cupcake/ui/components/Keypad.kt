package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview



@Composable
fun Keypad(value: String="") {
    
var value by rememberSaveable { mutableStateOf(value) }

fun select(num: Int) {
  value = Regex("\\w").replace(value, "x") + num.toString()
  println("Here $num $value")
}

Text(text="value=$value")

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
@Preview
@Composable
fun KeypadPreview(value: String="") {
    CupcakeTheme {
        Keypad(
        )
    }
    }
    */