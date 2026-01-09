package com.bin.emotion_detector.presentation.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bin.emotion_detector.presentation.home.HomeScreen
import com.bin.emotion_detector.presentation.home.HomeViewModel
import com.bin.emotion_detector.theme.JetPackCompose_templateTheme

import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    navHostController: NavHostController,
    innerPadding: PaddingValues,
    onRouteUpdate: (route: String) -> Unit
) {
    val currentBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute by remember {
        derivedStateOf {
            currentBackStackEntry?.destination?.route ?: "Welcome"
        }
    }
    var startDestination by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        startDestination = Screen.HomeScreen.route
    }
    LaunchedEffect(currentRoute) {
        onRouteUpdate.invoke(currentRoute)
    }

    JetPackCompose_templateTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (startDestination != null) {
                NavHost(
                    navController = navHostController,
                    startDestination = startDestination!!,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(route = Screen.HomeScreen.route) {
                        val viewModel = koinViewModel<HomeViewModel>()
                        val state by viewModel.uiState.collectAsStateWithLifecycle()

                        HomeScreen(
                            state,
                            viewModel
                        )
                    }

                }
            } else {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}