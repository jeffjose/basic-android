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
import com.example.cupcake.utils.ScopedView



@Composable
fun Nested(answer: Int=0, _set_answer_incoming_ : (( Int) -> Unit)? = null, content: @Composable() (() -> Unit)? = null, ) {

    
var answer by rememberSaveable(inputs=arrayOf(answer)) { mutableStateOf(answer) }

Text(text = "The answer is $answer")


    
LaunchedEffect(answer) {
    _set_answer_incoming_?.invoke(answer)
}

}