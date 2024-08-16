package com.example.cupcake.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun Coffee() {
    
var coffeeCount by remember { mutableStateOf(0) }


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
}

}

/*
@Preview
@Composable
fun CoffeePreview() {
    CupcakeTheme {
        Coffee(
        )
    }
    }
    */