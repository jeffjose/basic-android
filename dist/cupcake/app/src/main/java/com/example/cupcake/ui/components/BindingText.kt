package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview



@Composable
//fun BindingText( @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun BindingText() {


    

var text2 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("", TextRange(0, 7))) }

TextField(value=text2, onValueChange={text2=it})
Text("Entered ${text2.text}")


    
}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun BindingTextPreview( @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        BindingText(
        )
    }
    }
    */