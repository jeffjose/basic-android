package com.example.cupcake.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme

import android.os.Parcelable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.parcelize.Parcelize



@Parcelize
data class User(val loggedIn: Boolean) : Parcelable


@Composable
fun If() {
    

//println("if - before")


var foo = 0
var count by remember { mutableStateOf(0) }
val user1 by rememberSaveable { mutableStateOf(User(false)) }
val user = mutableStateMapOf<String,Boolean>("loggedIn" to false)

//println("if - after ${user["loggedIn"]}")

if (user["loggedIn"] == false) {
  Button(onClick={ 
    user["loggedIn"] = true
    println("login! ${user["loggedIn"]}")
    foo++
    }){
    Text(text="Log in ")
  }
}
else {

  Button(onClick={ 
    user["loggedIn"] = false
    println("logout! ${user["loggedIn"]}")
    foo++
    }){
    Text(text="Log out ")
  }
}

Button(onClick={ 
  if (user["loggedIn"] == true) {

user["loggedIn"] = false
  }
  else {
user["loggedIn"] = true

  }
    foo++
  }){
  Text(text="Toggle")
}

  Text(text="${user.getValue("loggedIn")}. Clicked $foo times")

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