package test.bin.jetpackcompose_template.presentation.splash

import test.bin.jetpackcompose_template.domain.model.MainUIState

data class SplashScreenUIState(
    val uiState: MainUIState = MainUIState()
)

enum class SplashScreenEvent {
    Continue
}