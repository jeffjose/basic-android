package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
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
    val first = MutableStateFlow(0)
    val c = MutableStateFlow(0)
    var signal = MutableStateFlow(0)
 }
 


@Composable
fun Reactivity(content: @Composable() (() -> Unit)? = null, ) {

    

println("[components/reactivity.pine]: Top")

val viewModel = ViewModel() 

//var (first, setFirst) = viewModel.first.collectAsMutableState()
//var signal = viewModel.signal.collectAsState()

var signal by remember { mutableStateOf(0)}

Button(onClick={
  signal = signal + 1
}) {

  if (signal > 5) {

  Text(text="Greater than 5 - ${signal}")
  }
  else {
  Text(text="Less than 5 - ${signal}")

  }
}


    
}