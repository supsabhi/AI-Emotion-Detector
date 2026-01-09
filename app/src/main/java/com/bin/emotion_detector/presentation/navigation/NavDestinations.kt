package com.bin.emotion_detector.presentation.navigation

sealed class Screen(val route: String) {

    object HomeScreen : Screen(HOMESCREEN)
}