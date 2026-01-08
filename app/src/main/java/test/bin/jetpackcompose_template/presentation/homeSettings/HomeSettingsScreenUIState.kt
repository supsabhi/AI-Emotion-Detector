package test.bin.jetpackcompose_template.presentation.homeSettings

import test.bin.jetpackcompose_template.domain.model.MainUIState


data class HomeSettingsScreenUIState(
    val uiState: MainUIState = MainUIState()
)

enum class HomeSettingsScreenEvent {
    Continue
}