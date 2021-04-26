/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetnews.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.jetnews.data.AppContainer
import com.example.jetnews.ui.theme.JetnewsTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun JetnewsApp(
    appContainer: AppContainer
) {
    JetnewsTheme {
        ProvideWindowInsets {
            // Make system bar transparent. Always use light icons, as the icons will show on either
            // the red primary color (in light mode) or black (in dark mode).
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = false)
            }

            val navController = rememberNavController()
            val coroutineScope = rememberCoroutineScope()
            val scaffoldState = rememberScaffoldState()
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = {
                    AppDrawer(
                        closeDrawer = { coroutineScope.launch { scaffoldState.drawerState.close() } },
                        navController = navController
                    )
                }
            ) {
                NavGraph(
                    appContainer = appContainer,
                    navController = navController,
                    scaffoldState = scaffoldState
                )
            }
        }
    }
}
