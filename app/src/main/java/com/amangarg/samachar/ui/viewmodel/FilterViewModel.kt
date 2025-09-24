package com.amangarg.samachar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amangarg.samachar.domain.model.Article
import com.amangarg.samachar.domain.usecase.GetTopHeadlinesByLanguageUseCase
import com.amangarg.samachar.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getTopHeadlinesByLanguageUseCase: GetTopHeadlinesByLanguageUseCase
) : ViewModel() {

    private val _filterState = MutableStateFlow<UiState<List<Article>>>(UiState.Idle)
    val filterState: StateFlow<UiState<List<Article>>> = _filterState

    fun fetchNewsByCountry(countryCode: String) {
        _filterState.value = UiState.Loading

        viewModelScope.launch {
            try {
                getTopHeadlinesByLanguageUseCase(countryCode).collect { articles ->
                    if (articles.isEmpty()) {
                        _filterState.value = UiState.Error("No news available for this country")
                    } else {
                        _filterState.value = UiState.Success(articles)
                    }
                }
            } catch (e: Exception) {
                _filterState.value = UiState.Error(e.message ?: "Something went wrong")
            }
        }
    }

    fun fetchNewsByLanguage(languageCode: String) {
        _filterState.value = UiState.Loading

        viewModelScope.launch {
            try {
                getTopHeadlinesByLanguageUseCase(languageCode, 1, 20).collect { articles ->
                    if (articles.isEmpty()) {
                        _filterState.value = UiState.Error("No news available in this language")
                    } else {
                        _filterState.value = UiState.Success(articles)
                    }
                }
            } catch (e: Exception) {
                _filterState.value = UiState.Error(e.message ?: "Something went wrong")
            }
        }
    }
}