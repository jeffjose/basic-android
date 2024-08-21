package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
    val first = MutableStateFlow("")
}

@Composable
//fun ComponentBindings( @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun ComponentBindings() {
    


val viewModel = remember { ViewModel() } // or viewModel() etc.
val (first, setFirst) = viewModel.first.collectAsMutableState()



var pin by rememberSaveable { mutableStateOf("") }

fun handleSubmit() {
  println("You entererd $pin")
}

Text("OUTSIDE: pin=$pin first=$first")
    TextField(
        value = first,
        onValueChange = setFirst
    )
Keypad(value=pin, first = first, setFirst = setFirst)
//Keypad(value=pin, bind:v = v)

}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun ComponentBindingsPreview( @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        ComponentBindings(
        )
    }
    }
    */