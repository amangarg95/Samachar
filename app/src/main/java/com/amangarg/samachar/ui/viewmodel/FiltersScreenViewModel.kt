package com.amangarg.samachar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amangarg.samachar.domain.model.Source
import com.amangarg.samachar.domain.usecase.GetSourcesUseCase
import com.amangarg.samachar.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FiltersScreenViewModel @Inject constructor(
    private val getSourcesUseCase: GetSourcesUseCase
) : ViewModel() {

    private val _sourcesState = MutableStateFlow<UiState<List<Source>>>(UiState.Idle)
    val sourcesState: StateFlow<UiState<List<Source>>> get() = _sourcesState

    fun fetchSources() {
        viewModelScope.launch {
            getSourcesUseCase()
                .onStart { _sourcesState.value = UiState.Loading }
                .catch { throwable -> _sourcesState.value = UiState.Error(throwable.message ?: "Unknown error") }
                .collect { sourcesList ->
                    _sourcesState.value = UiState.Success(sourcesList)
                }
        }
    }
}

