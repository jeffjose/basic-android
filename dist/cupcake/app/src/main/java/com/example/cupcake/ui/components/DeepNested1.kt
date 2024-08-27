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
import com.example.cupcake.ui.components.DeepNested2
import com.example.cupcake.utils.*



@Composable
//fun DeepNested1( @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun DeepNested1() {

    


println("1. top")

LaunchedEffect(true) {
  println("Loaded 1")
}

var first : Int by rememberSaveable { mutableStateOf(0) }

Button(onClick={
  first = first + 1
  }) {
  Text( text = "1: $first")
}


            fun _set_first(value: Int) {
                first = value
                
            }
            DeepNested2(_set_first_incoming_=::_set_first, first=first)

println("1. bottom")



    
}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun DeepNested1Preview( @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        DeepNested1(
        )
    }
    }
    */