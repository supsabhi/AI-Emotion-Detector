package com.bin.emotion_detector.presentation.home


sealed interface HomeScreenAction {
    object AlertDismissed : HomeScreenAction
    data class OnPermissionResult(
        val granted: Boolean,
        val permanentlyDenied: Boolean
    ) : HomeScreenAction
    data class InternetStatusChanged(
        val isInternetOff: Boolean
    ) : HomeScreenAction


}