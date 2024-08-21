package com.example.cupcake.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text



@Composable
fun Keypad(value) {
    


Text(text=value)

Row() {
  Button(onClick={}){Text(text="1")}
  Button(onClick={}){Text(text="2")}
  Button(onClick={}){Text(text="3")}

}


Row() {
  Button(onClick={}){Text(text="4")}
  Button(onClick={}){Text(text="5")}
  Button(onClick={}){Text(text="6")}

}


Row() {
  Button(onClick={}){Text(text="7")}
  Button(onClick={}){Text(text="8")}
  Button(onClick={}){Text(text="9")}
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
fun KeypadPreview(value) {
    CupcakeTheme {
        Keypad(
        )
    }
    }
    */