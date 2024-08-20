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
import kotlinx.coroutines.launch



@Composable
fun Simple(good:String="bye") {
    

var foo = "foo-value"
var bar by rememberSaveable { mutableStateOf("bar-value") }
var baz by remember { mutableStateOf("baz-value") }
var x = 1
var y = 2
println("------------------")
println("This is a test")

val scope = rememberCoroutineScope()
LaunchedEffect(true) {
        println("LaunchedEffect: $x $y")

        x = 10
        y = 20

        println("LaunchedEffect: $x $y")
}

scope.launch {

        println("scope.launch: $x $y")
        x = 100
        y = 200
        println("scope.launch: $x $y")

}

DisposableEffect(scope) {
        println("DisposableEffect: $x $y")
        onDispose {
        println("DisposableEffect:onDispose : $x $y")

        }
}


Text("Simple $foo $bar $baz")
Text("x=$x y=$y")

}

/*
@Preview
@Composable
fun SimplePreview(good:String="bye") {
    CupcakeTheme {
        Simple(
        )
    }
    }
    */