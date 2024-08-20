package com.example.cupcake.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun ReactiveStatements() {
    
var count by rememberSaveable { mutableStateOf(1) }

fun check() {
  println("The $count is ridiculously high")
}

SideEffect {println("hi there $count")}

fun handleClick() {
  count++
}




Button(onClick={handleClick()}) {
  Text(text = "RS: Count: $count")
}

}

/*
@Preview
@Composable
fun ReactiveStatementsPreview() {
    CupcakeTheme {
        ReactiveStatements(
        )
    }
    }
    */