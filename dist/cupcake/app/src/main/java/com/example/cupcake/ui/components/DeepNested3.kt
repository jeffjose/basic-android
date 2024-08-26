package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.utils.*



@Composable
//fun DeepNested3(first: Int, _set_first_incoming_ : (( Int) -> Unit)? = null,  @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun DeepNested3(first: Int, _set_first_incoming_ : (( Int) -> Unit)? = null, ) {

    

println("  3. top")
var first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }

LaunchedEffect(true) {
  println("Loaded 3")
}

Button(onClick={
  first = first + 1
  }) {
  Text( text = "3: $first")
}

println("  3. bottom")



    
LaunchedEffect(first) {
    _set_first_incoming_?.invoke(first)
}

}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun DeepNested3Preview(first: Int, _set_first_incoming_ : (( Int) -> Unit)? = null,  @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        DeepNested3(
        )
    }
    }
    */