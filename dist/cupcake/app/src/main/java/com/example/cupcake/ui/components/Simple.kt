package com.example.cupcake.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun Simple() {
    

var coffeeCount  by remember { mutableStateOf(0) }
var count by rememberSaveable { mutableStateOf(0) }

Text(text = "S: You've had $coffeeCount cups of coffee")

Button(onClick = { coffeeCount++ }) 
        { Text("S: Add one") }

}

/*
@Preview
@Composable
fun SimplePreview() {
    CupcakeTheme {
        Simple(
        )
    }
    }
    */