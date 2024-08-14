/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.R
import com.example.cupcake.ui.theme.CupcakeTheme

/**
 * Composable that displays the list of items as [RadioButton] options, [onSelectionChanged] lambda
 * that notifies the parent composable when a new value is selected, [onPrevButtonClicked] lambda
 * that cancels the order when user clicks cancel and [onNextButtonClicked] lambda that triggers the
 * navigation to next screen
 */
@Composable
fun SecondScreen(navController: NavHostController, modifier: Modifier = Modifier) {

    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) {
        Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {}
        Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement =
                        Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { navController.navigate("/first") }) {
                    Text(stringResource(R.string.one))
                }
                Button(onClick = { navController.navigate("/second") }) {
                    Text(stringResource(R.string.two))
                }

                Button(onClick = { navController.navigate("/third") }) {
                    Text(stringResource(R.string.three))
                }
            }
        }
    }
}

@Preview
@Composable
fun SecondScreenPreview() {
    CupcakeTheme {
        SecondScreen(
                navController = rememberNavController(),
                modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}
