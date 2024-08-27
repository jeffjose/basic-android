package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.utils.ScopedView



@Composable
fun ComplexButton(content: @Composable() (() -> Unit)? = null, ) {

    
println("[components/complex-button.pine]: Top")

Text(text="-COMPLEX-")
content?.invoke()


    
}