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
import com.example.cupcake.utils.ScopedView



@Composable
fun DeepNested3(first: Int, _set_first_incoming_ : (( Int) -> Unit)? = null, content: @Composable() (() -> Unit)? = null, ) {

    

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