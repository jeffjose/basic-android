package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.components.Keypad
import com.example.cupcake.utils.*
import kotlinx.coroutines.flow.MutableStateFlow


class ViewModel {
    val v = MutableStateFlow("")
}

@Composable
fun ComponentBindings() {
    


val viewModel = remember { ViewModel() } // or viewModel() etc.
val (v, setV) = viewModel.v.collectAsMutableState()



var pin by rememberSaveable { mutableStateOf("") }

fun handleSubmit() {
  println("You entererd $pin")
}

Text("OUTSIDE: pin=$pin v=$v")
Keypad(value=pin, v = v, setV = setV)
//Keypad(value=pin, bind:v = v, setV = setV)

}

/*
@Preview
@Composable
fun ComponentBindingsPreview() {
    CupcakeTheme {
        ComponentBindings(
        )
    }
    }
    */