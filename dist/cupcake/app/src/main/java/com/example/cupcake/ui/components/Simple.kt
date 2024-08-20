package com.example.cupcake.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun Simple() {
    
var foo = "foo-value"
var bar by rememberSaveable { mutableStateOf("bar-value") }
var baz by remember { mutableStateOf("baz-value") }
var x = 1
var y = 2
println("------------------")
println("This is a test")

val scope = rememberCoroutineScope()
LaunchedEffect(scope) {
        println("LaunchedEffect")
        println(x)
        println(y)

        x = 10
        y = 20

        println(x)
        println(y)
}

DisposableEffect(scope) {
        println("DisposableEffect")
        println(x)
        println(y)
        onDispose {
        println("DisposableEffect: onDispose")

        }
}


Text("Simple $foo $bar $baz")
Text("x=$x y=$y")

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