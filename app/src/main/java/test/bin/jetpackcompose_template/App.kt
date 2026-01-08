package test.bin.jetpackcompose_template

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import test.bin.jetpackcompose_template.presentation.navigation.Navigation
import test.bin.jetpackcompose_template.presentation.uiUtils.ObserveAsEvents
import test.bin.jetpackcompose_template.presentation.uiUtils.SnackBarController
import kotlinx.coroutines.launch


@Composable
fun App() {
    val snackBarHostState = remember { SnackbarHostState() }
    val navBarNavController: NavHostController = rememberNavController()
    var currentRoute by remember { mutableStateOf("LogInScreen") }
    val scope = rememberCoroutineScope()



    ObserveAsEvents(
        flow = SnackBarController.events,
        snackBarHostState
    ) { event ->
        scope.launch {
            snackBarHostState.currentSnackbarData?.dismiss()
            val result = snackBarHostState.showSnackbar(
                message = event.message,
                actionLabel = event.action?.name,
                duration = SnackbarDuration.Short
            )
            if (result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier
                    .wrapContentSize()
            )
        },
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize(),

        ) { innerPadding ->
        Navigation(navBarNavController, innerPadding) {
            currentRoute = it
        }
    }
}