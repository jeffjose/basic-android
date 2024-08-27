package com.example.cupcake.ui.components

import com.example.cupcake.ui.theme.CupcakeTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.utils.PineRender



@Composable
fun Spacer(content: @Composable() (() -> Unit)? = null, ) {

    
Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
) {
        Text(
                text = "First item",
                modifier = Modifier.padding(16.dp)
        )
        Text(
                text = "Second item",
                modifier = Modifier.padding(16.dp)
        )
}


    
}