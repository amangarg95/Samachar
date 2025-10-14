package com.amangarg.samachar.ui.activity

import androidx.lifecycle.ViewModel
import com.amangarg.samachar.domain.usecase.GetFormattedCurrentDateUseCase
import com.amangarg.samachar.common.util.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getFormattedCurrentDateUseCase: GetFormattedCurrentDateUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        MainUiState(
            currentRegion = AppConstants.DEFAULT_COUNTRY.uppercase(),
            currentLanguage = AppConstants.DEFAULT_LANGUAGE.uppercase(),
            date = getFormattedCurrentDateUseCase()
        )
    )
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun setRegion(region: String) {
        val normalized = region.trim().uppercase()
        if (normalized == _uiState.value.currentRegion) return
        _uiState.update { it.copy(currentRegion = normalized) }
    }

    fun setLanguage(language: String) {
        val normalized = language.trim().uppercase()
        if (normalized == _uiState.value.currentLanguage) return
        _uiState.update { it.copy(currentLanguage = normalized) }
    }
}