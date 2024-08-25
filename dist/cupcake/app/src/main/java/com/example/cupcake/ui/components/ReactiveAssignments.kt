package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview



@Composable
//fun ReactiveAssignments( @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun ReactiveAssignments() {

    
var count by rememberSaveable { mutableStateOf(0) }

fun handleClick() {
  count++
}


Button(onClick={handleClick()}) {

Text(text = "Clicked $count " + if (count == 1) "time" else "times")
}


Button(onClick={count++ }) {

Text(text = "Clicked $count " + if (count == 1) "time" else "times")
}



    
}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun ReactiveAssignmentsPreview( @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        ReactiveAssignments(
        )
    }
    }
    */