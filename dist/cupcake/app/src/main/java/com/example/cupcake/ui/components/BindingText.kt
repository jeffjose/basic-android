package com.example.cupcake.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue



@Composable
fun BindingText() {
    


var text by remember { mutableStateOf(TextFieldValue()) }
//var text2 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("", TextRange(0, 7))) }
var text4 by rememberSaveable { mutableStateOf(TextFieldValue("", TextRange(0, 7))) }
var text3 by rememberSaveable(stateSaver=TextFieldValue.Saver) { mutableStateOf(TextFieldValue("", TextRange(0, 7))) }
var text2 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("", TextRange(0, 7))) }

BasicTextField(value=text, onValueChange={text=it})
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