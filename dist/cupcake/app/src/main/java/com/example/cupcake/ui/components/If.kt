package com.example.cupcake.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun If() {
    

var count by remember { mutableStateOf(0) }
val user by remember { mutableStateOf(mutableMapOf( "loggedIn" to false)) }
println(user)

Button(onClick={ count-- }){
  Text(text="Log in ")
}


Button(onClick={ 
  println("hi $user") 
  user["loggedIn"] = true
  println("bye $user") 
  }){
  Text(text="Log out ")
}

Text(text="$count")
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