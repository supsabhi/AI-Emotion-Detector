package test.bin.jetpackcompose_template.presentation.navigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.flow.first
import org.koin.androidx.compose.koinViewModel
import test.bin.jetpackcompose_template.presentation.homeSettings.HomeSettingsScreen
import test.bin.jetpackcompose_template.presentation.homeSettings.HomeSettingsScreenEvent
import test.bin.jetpackcompose_template.presentation.homeSettings.HomeSettingsViewModel

import test.bin.jetpackcompose_template.presentation.splash.SplashScreen
import test.bin.jetpackcompose_template.presentation.splash.SplashScreenEvent
import test.bin.jetpackcompose_template.theme.JetPackCompose_templateTheme
import test.bin.jetpackcompose_template.util.PreferencesKeys
import test.bin.jetpackcompose_template.util.dataStore

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
    val context = LocalContext.current
    LaunchedEffect(Unit) {

        val prefs = context.dataStore.data.first()
       // val isLoggedIn = prefs[PreferencesKeys.ISLOGGEDIN] ?: false


        startDestination = /*if (isLoggedIn) {
            Screen.Scanner.route
        } else {*/
            Screen.Splash.route
        //}
    }
    //val sharedViewModel: ShareViewModel = koinViewModel()
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
                    composable(route = Screen.Splash.route) {
                        SplashScreen(onNavigateAction = { event ->
                            when (event) {
                                SplashScreenEvent.Continue -> navHostController.navigate(Screen.HomeSetting.route)
                            }
                        })
                    }
                    composable(route = Screen.HomeSetting.route) {
                        val viewModel = koinViewModel<HomeSettingsViewModel>()
                        HomeSettingsScreen(
                            viewModel.state.value,
                            viewModel,
                            onNavigateAction = { event ->
                            when (event) {
                                HomeSettingsScreenEvent.Continue -> {}
                            }
                        })
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