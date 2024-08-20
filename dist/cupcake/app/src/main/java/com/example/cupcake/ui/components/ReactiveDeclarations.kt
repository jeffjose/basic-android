package com.example.cupcake.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun ReactiveDeclarations() {
    




var count by rememberSaveable { mutableStateOf(1) }

val doubled by remember {derivedStateOf { count * 2}}
val quadrupled by remember {derivedStateOf { doubled * 2}}

fun handleClick() {
  count++
}


Button(onClick={handleClick()}) {
  Text(text = "Count: $count")
}

  Text(text = "$count * 2 = $doubled")
  Text(text = "$count * 2 = $quadrupled")

}

/*
@Preview
@Composable
fun ReactiveDeclarationsPreview() {
    CupcakeTheme {
        ReactiveDeclarations(
        )
    }
    }
    */