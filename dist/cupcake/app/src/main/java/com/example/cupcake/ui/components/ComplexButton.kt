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
//fun ComplexButton(content: @Composable() (() -> Unit)? = null,  @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun ComplexButton(content: @Composable() (() -> Unit)? = null, ) {

    
//external var count : Int
var count by rememberSaveable { mutableStateOf(1) }

println("[components/complex-button.pine]: Top")

Button(onClick={
  count = count + 1
}) {
content?.invoke()
}



    
}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun ComplexButtonPreview(content: @Composable() (() -> Unit)? = null,  @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        ComplexButton(
        )
    }
    }
    */