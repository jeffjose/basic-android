package com.example.cupcake.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun If() {
    

var count by remember { mutableStateOf(0) }
val user by remember { mutableStateOf(mutableStateMapOf("loggedIn" to false)) }
println(user)

if (user["loggedIn"] == false) {
Button(onClick={ 
  user["loggedIn"] = true
  }){
  Text(text="Log in ")
}
}
else {

Button(onClick={ 
  user["loggedIn"] = false
  }){
  Text(text="Log out ")
}
}

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