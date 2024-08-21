package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview



@Composable
fun Nested(answer: Int=0) {
    
var answer = answer

Text(text = "The answer is $answer")

}

/*
@Preview
@Composable
fun NestedPreview(answer: Int=0) {
    CupcakeTheme {
        Nested(
        )
    }
    }
    */