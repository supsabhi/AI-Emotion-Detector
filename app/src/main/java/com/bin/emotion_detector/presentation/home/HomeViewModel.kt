package com.bin.emotion_detector.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.bin.emotion_detector.com.bin.emotion_detector.domain.model.EmotionResult
import com.bin.emotion_detector.core.ResourcesProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import test.bin.jetpackcompose_template.R

class HomeViewModel(
    private val resourcesProvider: ResourcesProvider,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUIState())
    val uiState: StateFlow<HomeScreenUIState> = _uiState.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun onAction(action: HomeScreenAction) {

        when (action) {

            HomeScreenAction.AlertDismissed -> clearAlert()
            is HomeScreenAction.InternetStatusChanged -> handleInternetStatus(action)
            is HomeScreenAction.OnPermissionResult -> handlePermission(action)

        }
    }

    fun onEmotionDetected(result: EmotionResult) {
        _uiState.update {
            it.copy(emotionResult = result)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun handlePermission(action: HomeScreenAction.OnPermissionResult) {
        if (!action.granted) {
            _uiState.update {
                it.copy(
                    isPermissionPermanentlyDenied = action.permanentlyDenied,
                    startCamera = false,
                    alert = AlertState(
                        title = resourcesProvider.getString(R.string.permission_required_title),
                        message = if (action.permanentlyDenied)
                            resourcesProvider.getString(R.string.permission_permanently_denied_msg)
                        else
                            resourcesProvider.getString(R.string.permission_required_msg),
                        action = AlertAction.OPEN_APP_SETTINGS
                    )
                )
            }
            return
        } else {
            _uiState.update {
                it.copy(
                    isPermissionPermanentlyDenied = false,
                    alert = null,
                    startCamera = true
                )

            }

        }
    }



    private fun clearAlert() {
        _uiState.update { it.copy(alert = null) }
    }


    private fun handleInternetStatus(action: HomeScreenAction.InternetStatusChanged) {
        _uiState.update {
            it.copy(
                internetOff = action.isInternetOff,
                alert = if (action.isInternetOff) {
                    AlertState(
                        title = resourcesProvider.getString(R.string.net_off_title),
                        message = resourcesProvider.getString(R.string.net_on_msg),
                        action = AlertAction.OPEN_WIFI_SETTINGS
                    )
                } else {
                    null
                }
            )
        }
    }

}