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
//fun ComplexButton(text: String, _set_text_incoming_ : (( String) -> Unit)? = null, count : Int, _set_count_incoming_ : (( Int) -> Unit)? = null, content: @Composable() (() -> Unit)? = null,  @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun ComplexButton(text: String, _set_text_incoming_ : (( String) -> Unit)? = null, count : Int, _set_count_incoming_ : (( Int) -> Unit)? = null, content: @Composable() (() -> Unit)? = null, ) {

    
var text by rememberSaveable(inputs=arrayOf(text)) { mutableStateOf(text) }
var count by rememberSaveable(inputs=arrayOf(count)) { mutableStateOf(count) }

  Text(text=text)

Button(onClick={
  count = count + 1
}) {
content?.invoke()
}



    
LaunchedEffect(text) {
    _set_text_incoming_?.invoke(text)
}

LaunchedEffect(count ) {
    _set_count_incoming_?.invoke(count )
}

}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun ComplexButtonPreview(text: String, _set_text_incoming_ : (( String) -> Unit)? = null, count : Int, _set_count_incoming_ : (( Int) -> Unit)? = null, content: @Composable() (() -> Unit)? = null,  @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        ComplexButton(
        )
    }
    }
    */