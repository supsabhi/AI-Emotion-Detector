package test.bin.jetpackcompose_template.presentation.homeSettings

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.bin.jetpackcompose_template.domain.repositories.LoginRepository
import test.bin.jetpackcompose_template.presentation.uiUtils.SnackBarController
import test.bin.jetpackcompose_template.presentation.uiUtils.SnackBarEvent

class HomeSettingsViewModel(private val loginRepository: LoginRepository?) : ViewModel() {
    private val _state = mutableStateOf(HomeSettingsScreenUIState())
    val state: State<HomeSettingsScreenUIState> = _state

    var isConnected = mutableStateOf<Boolean?>(null)
    /*suspend fun getDataBase(context: Context): Boolean {
            val prefs = context.dataStore.data.first()
            val ipAdd = prefs[PreferencesKeys.IPADDRESS] ?: ""
            val port = prefs[PreferencesKeys.PORTNUMBER] ?: ""
            val url = "http://$ipAdd:$port/"
            _state.value = HomeSettingsScreenUIState(uiState = MainUIState(isLoading = true))
            val result = loginRepository?.getDatabaseDetails(url)
            if (result != null) {
                if (result.status != null) {
                    if (result.status == true) {
                        _state.value = _state.value.copy(
                            uiState = MainUIState(
                                isLoading = false, message = result.message
                            ),
                        )
                        if (result.data != null) {
                            clearDatabaseList()
                            for (database in result.data!!) {
                                databases.value.add(database)
                            }

                            isConnected.value = true
                        } else {
                            _state.value = HomeSettingsScreenUIState(
                                MainUIState(
                                    isLoading = false,
                                    error = result.message

                                )
                            )
                            isConnected.value = false
                        }

                    } else {
                        _state.value = HomeSettingsScreenUIState(
                            MainUIState(
                                isLoading = false,
                                error = result.message

                            )
                        )
                        isConnected.value = false
                    }
                } else {
                    _state.value = HomeSettingsScreenUIState(
                        MainUIState(
                            isLoading = false,
                            error = result.message

                        )
                    )
                    isConnected.value = false
                }

            }
             else
             {
                 _state.value = ScanScreenUIState(
                     MainUIState(
                         isLoading = false,
                         error = "error")
                     )

                 _showDialogEvent.value = false
             }
            return isConnected.value!!
    }*/

    fun sendSnackBarEvent(
        message: String = "Moving to login"
    ) {
        viewModelScope.launch {
            SnackBarController.sendEvent(
                event = SnackBarEvent(
                    message
                )
            )
        }
    }


}