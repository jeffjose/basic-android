package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.components.ComplexButton
import com.example.cupcake.utils.*
import kotlinx.coroutines.flow.MutableStateFlow


 class ViewModel {
    val v = MutableStateFlow("")
    val first = MutableStateFlow("")
    val c = MutableStateFlow(0)
 }
 

@Composable
//fun Reactivity(content: @Composable() (() -> Unit)? = null,  @Suppress("UNUSED_PARAMETER") vararg params: (String) -> Unit) {
fun Reactivity(content: @Composable() (() -> Unit)? = null, ) {

    


val viewModel = remember { ViewModel() } 
val (first, setFirst) = viewModel.first.collectAsMutableState()

val c by remember { mutableStateOf(0) }

if(c > 5) {
  println("greater than 5")
}
else {
  println("less than 5")

}

var count by rememberSaveable { mutableStateOf(0) }

println("[components/reactivity.pine]: Top")
Text(text="Simple count: $count")

Button(onClick={
  count = count + 1
}) {
  Text(text="Simple: Click me")
}



//Text(text=text)
//
//Button(onClick={
//  count = count + 1
//}) {
//  Text(text="Complex: Click me")
//}


Text(text="Complex count: $count")
Text(text="Simple count: $count")
  Button(onClick={
    count = count + 1
  }) {
    Text(text="Simple: Click me")
  }



    
}

/*
@Suppress("unused")
@Suppress("unused_parameter")
@Preview
@Composable
fun ReactivityPreview(content: @Composable() (() -> Unit)? = null,  @Suppress("UNUSED_PARAMETER")) {
    CupcakeTheme {
        Reactivity(
        )
    }
    }
    */