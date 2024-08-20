package com.example.cupcake.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text

@Composable
fun If() {
    

val user = mutableMapOf(
  "loggedIn" to false
)
println(user)

Button(onClick={ user["loggedIn" ] = true }){
  Text(text="Log in ")
}


Button(onClick={ user["loggedIn" ] = false }){
  Text(text="Log out ")
}

Text(text="$user")

}

/*
@Preview
@Composable
fun IfPreview() {
    CupcakeTheme {
        If(
        )
    }
    }
    */