package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.utils.*
import com.example.cupcake.utils.ScopedView
import kotlinx.coroutines.flow.MutableStateFlow


 class ViewModel {
    val v = MutableStateFlow("")
    val first = MutableStateFlow("")
    val c = MutableStateFlow(0)
 }
 


@Composable
fun Reactivity(content: @Composable() (() -> Unit)? = null, ) {

    
val viewModel = remember { ViewModel() } 
val (first, setFirst) = viewModel.first.collectAsMutableState()

var count by remember { mutableStateOf(0) }

println("[components/reactivity.pine]: Top")
Text(text="Simple count: $count first=$first")

Button(onClick={
  count = count + 1
}) {
  Text(text="Simple: Click me")
}


    
}