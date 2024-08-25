package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview



@Composable
//fun Nested(answer: Int=0, _set_answer_incoming_ : (( Int) -> Unit)? = null,  @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun Nested(answer: Int=0, _set_answer_incoming_ : (( Int) -> Unit)? = null, ) {

    
var answer by rememberSaveable(inputs=arrayOf(answer)) { mutableStateOf(answer) }
var _set_answer_incoming_ = _set_answer_incoming_ ?: { it }

Text(text = "The answer is $answer")



    
LaunchedEffect(answer) {
    _set_answer_incoming_?.invoke(answer)
}

}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun NestedPreview(answer: Int=0, _set_answer_incoming_ : (( Int) -> Unit)? = null,  @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        Nested(
        )
    }
    }
    */