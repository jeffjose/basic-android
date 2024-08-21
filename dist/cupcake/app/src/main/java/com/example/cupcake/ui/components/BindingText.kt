package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.ui.text.TextRange
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview



@Composable
fun BindingText() {
    

var text2 by rememberSaveable(stateSaver = TextFieldValue.Saver ) { mutableStateOf(TextFieldValue("", TextRange(0, 7))) }

TextField(value=text2, onValueChange={text2=it})
Text("Entered ${text2.text}")

}

/*
@Preview
@Composable
fun BindingTextPreview() {
    CupcakeTheme {
        BindingText(
        )
    }
    }
    */