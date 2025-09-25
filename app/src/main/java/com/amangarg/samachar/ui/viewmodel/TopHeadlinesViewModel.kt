package com.amangarg.samachar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amangarg.samachar.domain.model.Article
import com.amangarg.samachar.domain.usecase.GetTopHeadlinesByCountryUseCase
import com.amangarg.samachar.domain.usecase.GetTopHeadlinesByLanguageUseCase
import com.amangarg.samachar.ui.UiState
import com.amangarg.samachar.common.util.AppConstants.DEFAULT_COUNTRY
import com.amangarg.samacharam.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesViewModel @Inject constructor(
    private val getTopHeadlinesByCountryUseCase: GetTopHeadlinesByCountryUseCase,
    private val getTopHeadlinesByLanguageUseCase: GetTopHeadlinesByLanguageUseCase,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Article>>>(UiState.Idle)
    val state: StateFlow<UiState<List<Article>>> = _state

    private var currentPage = 1
    private val pageSize = 20

    // simple request guard — still used, but flow operators handle lifecycle
    private var isRequestInProgress = false

    private val loadedArticles = mutableListOf<Article>()

    init {
        getTopHeadlinesByCountry(DEFAULT_COUNTRY)
    }

    fun getTopHeadlinesByCountry(country: String) {
        currentPage = 1
        fetchByCountry(country = country, page = currentPage, reset = true)
    }

    fun loadNextPage(country: String = DEFAULT_COUNTRY) {
        if (isRequestInProgress) return
        currentPage += 1
        fetchByCountry(country = country, page = currentPage, reset = false)
    }

    private fun fetchByCountry(country: String, page: Int, reset: Boolean) {
        if (isRequestInProgress) return

        viewModelScope.launch {
            getTopHeadlinesByCountryUseCase(
                country = country,
                pageNum = page,
                pageSize = pageSize
            )
                .flowOn(dispatcherProvider.io)
                .onStart {
                    isRequestInProgress = true
                    if (reset) {
                        loadedArticles.clear()
                        _state.value = UiState.Loading
                    } else {
                        // optionally: _state.value = UiState.LoadingMore(loadedArticles.toList())
                    }
                }
                .catch { throwable ->
                    _state.value = UiState.Error(throwable.message ?: "Something went wrong")
                }
                .onCompletion {
                    isRequestInProgress = false
                }
                .collectLatest { list ->
                    loadedArticles.addAll(list)
                    _state.value = UiState.Success(loadedArticles.toList())
                }
        }
    }

    fun getTopHeadlinesByLanguage(language: String) {
        currentPage = 1
        loadedArticles.clear()
        fetchByLanguage(language = language, page = currentPage, reset = true)
    }

    private fun fetchByLanguage(language: String, page: Int, reset: Boolean) {
        if (isRequestInProgress) return

        viewModelScope.launch {
            getTopHeadlinesByLanguageUseCase(
                language = language,
                pageNum = page,
                pageSize = pageSize
            )
                .flowOn(dispatcherProvider.io)
                .onStart {
                    isRequestInProgress = true
                    if (reset) {
                        loadedArticles.clear()
                        _state.value = UiState.Loading
                    }
                }
                .catch { throwable ->
                    _state.value = UiState.Error(throwable.message ?: "Something went wrong")
                }
                .onCompletion {
                    isRequestInProgress = false
                }
                .collectLatest { list ->
                    loadedArticles.addAll(list)
                    _state.value = UiState.Success(loadedArticles.toList())
                }
        }
    }

    fun bookMarkArticle(article: Article) {

    }

}
