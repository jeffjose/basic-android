package com.example.cupcake.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text



@Composable
fun Keypad(value) {
    


Row() {
  Button(){Text(text="1")}
  Button(){Text(text="2")}
  Button(){Text(text="3")}

}


Row() {
  Button(){Text(text="4")}
  Button(){Text(text="5")}
  Button(){Text(text="6")}

}


Row() {
  Button(){Text(text="7")}
  Button(){Text(text="8")}
  Button(){Text(text="9")}
}


Row() {
  Button(){Text(text="Clear")}
  Button(){Text(text="0")}
  Button(){Text(text="Submit")}
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