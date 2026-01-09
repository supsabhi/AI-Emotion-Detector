package com.bin.emotion_detector.presentation.home

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.compose.LocalLifecycleOwner


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    state: HomeScreenUIState,
    viewModel: HomeViewModel
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val activity = context as? Activity
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        viewModel.onAction(
            HomeScreenAction.OnPermissionResult(
                granted = isGranted,
                permanentlyDenied = !ActivityCompat.shouldShowRequestPermissionRationale(
                    context as Activity, Manifest.permission.CAMERA
                )
            )
        )
    }

    // Request permission on first launch
    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.CAMERA)
    }

    Scaffold(
    )
    { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.LightGray)
        ) {
            if (state.startCamera) {
                CameraPreview(
                    lifecycleOwner = lifecycleOwner,
                    context = context,
                    onEmotionDetected = viewModel::onEmotionDetected
                )
            }
            if (state.uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp),
                        color = Color.Black,
                        trackColor = Color.White
                    )
                }
            }
            state.emotionResult?.let { emotion ->
                EmotionOverlay(emotion)
            }
            state.alert?.let { alert ->
                ComposeAlertDialog(
                    title = alert.title,
                    msg = alert.message,
                    onButtonClick = {
                        viewModel.onAction(HomeScreenAction.AlertDismissed)

                        when (alert.action) {
                            AlertAction.NONE -> {}
                            AlertAction.EXIT_APP -> {
                                activity?.finishAffinity()
                            }

                            AlertAction.OPEN_WIFI_SETTINGS -> {
                                context.startActivity(
                                    Intent(Settings.ACTION_WIFI_SETTINGS)
                                )
                                activity?.finish()
                            }
                            AlertAction.OPEN_APP_SETTINGS -> {
                                context.startActivity(
                                    Intent(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.fromParts("package", context.packageName, null)
                                    )
                                )
                                activity?.finish()
                            }

                        }
                    },
                    onDismissClick = {}
                )
            }
            /*if (state.countryList.isNotEmpty()) {
                CountryScreen(state.countryList)
            }*/
        }
    }

}