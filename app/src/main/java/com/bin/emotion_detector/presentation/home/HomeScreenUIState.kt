package com.bin.emotion_detector.presentation.home

import com.bin.emotion_detector.com.bin.emotion_detector.domain.model.EmotionResult
import com.bin.emotion_detector.domain.model.MainUIState

data class HomeScreenUIState(
    val uiState: MainUIState = MainUIState(),
    val internetOff: Boolean = false,
    val alert: AlertState? = null,
    val hasCameraPermission: Boolean = false,
    val startCamera: Boolean = false,
    val emotionResult: EmotionResult? = null,

    val isPermissionPermanentlyDenied: Boolean = false,


    val error: String? = null
)

data class AlertState(
    val title: String,
    val message: String,
    val action: AlertAction = AlertAction.NONE
)

enum class AlertAction {
    NONE,
    OPEN_WIFI_SETTINGS,
    EXIT_APP,
    OPEN_APP_SETTINGS,


}