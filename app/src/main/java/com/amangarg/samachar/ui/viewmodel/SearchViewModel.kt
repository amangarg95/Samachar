package com.amangarg.samachar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amangarg.samachar.domain.model.Article
import com.amangarg.samachar.domain.usecase.SearchNewsUseCase
import com.amangarg.samachar.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase
) : ViewModel() {

    private val _searchState = MutableStateFlow<UiState<List<Article>>>(UiState.Idle)
    val searchState: StateFlow<UiState<List<Article>>> = _searchState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _hasSearched = MutableStateFlow(false)
    val hasSearched: StateFlow<Boolean> = _hasSearched.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchNews(query: String) {
        if (query.isBlank()) return

        _hasSearched.value = true
        _searchState.value = UiState.Loading

        viewModelScope.launch {
            try {
                searchNewsUseCase(query).collect { articles ->
                    if (articles.isEmpty()) {
                        _searchState.value = UiState.Error("No results found. Try searching something else.")
                    } else {
                        _searchState.value = UiState.Success(articles)
                    }
                }
            } catch (e: Exception) {
                _searchState.value = UiState.Error(e.message ?: "Something went wrong")
            }
        }
    }

    fun clearSearch() {
        _searchQuery.value = ""
        _searchState.value = UiState.Idle
        _hasSearched.value = false
    }
}
