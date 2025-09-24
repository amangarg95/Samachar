package com.amangarg.samachar.ui.activity

import androidx.lifecycle.ViewModel
import com.amangarg.samachar.domain.usecase.GetFormattedCurrentDateUseCase
import com.amangarg.samachar.util.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getFormattedCurrentDateUseCase: GetFormattedCurrentDateUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        MainUiState(
            currentRegion = AppConstants.DEFAULT_COUNTRY_NAME,
            currentLanguage = AppConstants.DEFAULT_LANGUAGE_NAME,
            date = getFormattedCurrentDateUseCase()
        )
    )
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun updateRegion(region: String) {
        _uiState.value = _uiState.value.copy(currentRegion = region)
    }

    fun updateLanguage(language: String) {
        _uiState.value = _uiState.value.copy(currentLanguage = language)
    }
}