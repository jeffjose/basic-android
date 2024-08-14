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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.example.cupcake.R
import com.example.cupcake.ui.theme.CupcakeTheme

/**
 * Composable that displays the list of items as [RadioButton] options, [onSelectionChanged] lambda
 * that notifies the parent composable when a new value is selected, [onPrevButtonClicked] lambda
 * that cancels the order when user clicks cancel and [onNextButtonClicked] lambda that triggers the
 * navigation to next screen
 */
@Composable
fun SecondScreen(onPrevButtonClicked: () -> Unit = {}, modifier: Modifier = Modifier) {

    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
    }
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween) {
        Row(
                modifier =
                        Modifier.fillMaxWidth().padding(dimensionResource(R.dimen.padding_medium)),
                horizontalArrangement =
                        Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                verticalAlignment = Alignment.Bottom
        ) {
            Button(modifier = Modifier.weight(1f), onClick = onPrevButtonClicked) {
                Text(stringResource(R.string.prev))
            }
        }
    }
}

@Preview
@Composable
fun SelectOptionPreview() {
    CupcakeTheme { SecondScreen(modifier = Modifier.fillMaxHeight()) }
}
