package com.example.cupcake.ui.components.dropdown

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.utils.PineRender



@Composable
fun Dropdown(content: @Composable() (() -> Unit)? = null, ) {

    
fun _pine_disposable_fun() {
}

    

    // on_destroy
    val _pine_disposable_state by remember {mutableStateOf(true)}

    DisposableEffect(_pine_disposable_state) {
    onDispose {
        _pine_disposable_fun()
        }
    }

}