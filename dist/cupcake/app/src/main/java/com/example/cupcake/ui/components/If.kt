package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import kotlinx.parcelize.Parcelize
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import android.os.Parcelable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.material3.Text
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Parcelize
data class User(var loggedIn: Boolean) : Parcelable

@Composable
fun If() {
    

//println("if - before")


var foo = 0
var count by remember { mutableStateOf(0) }
var user by rememberSaveable { mutableStateOf(User(false)) }

//println("if - after ${user["loggedIn"]}")

if (user.loggedIn == false) {
  Button(onClick={ 
    user  = user.copy(loggedIn = true)
    println("login! ${user.loggedIn}")
    foo++
    }){
    Text(text="Log in ")
  }
}
else {

  Button(onClick={ 
    user  = user.copy(loggedIn = false)
    println("logout! ${user.loggedIn}")
    foo++
    }){
    Text(text="Log out ")
  }
}

Button(onClick={ 
  if (user.loggedIn == true) {

    user  = user.copy(loggedIn = false)
  }
  else {
    user  = user.copy(loggedIn = true)

  }
    foo++
  }){
  Text(text="Toggle")
}

  Text(text="${user.loggedIn}. Clicked foo=$foo times")

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