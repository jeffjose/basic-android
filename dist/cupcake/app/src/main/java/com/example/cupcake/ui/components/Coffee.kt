package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
//fun Coffee( @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun Coffee() {

    

var coffeeCount  by remember { mutableStateOf(0) }
var _set_coffeeCount _incoming_ = _set_coffeeCount _incoming_ ?:  { it }
var count by rememberSaveable { mutableStateOf(0) }
var _set_count_incoming_ = _set_count_incoming_ ?: { it }

val xcoffeeCount  by remember { mutableStateOf(0) }
var _set_xcoffeeCount _incoming_ = _set_xcoffeeCount _incoming_ ?:  { it }
val xcount by rememberSaveable { mutableStateOf(0) }
var _set_xcount_incoming_ = _set_xcount_incoming_ ?: { it }


Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
) {
        Text(
                text = "First item",
                modifier = Modifier.padding(16.dp)
        )
        Text(
                text = "Second item",
                modifier = Modifier.padding(16.dp)
        )
        Text(
                text = "Third item",
                modifier = Modifier.padding(16.dp)
        )
      Text("You've had $coffeeCount cups of coffee.",
        modifier = Modifier.padding(16.dp)
      )
        Button(onClick = { coffeeCount++ }, Modifier.padding(top = 8.dp)) {
            Text("Add one")
        }
      Text("Count is $count.",
        modifier = Modifier.padding(16.dp)
      )

        Button(onClick = { count++ }, Modifier.padding(top = 8.dp)) {
            Text("Add one")
        }
}



    
}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun CoffeePreview( @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        Coffee(
        )
    }
    }
    */