package com.amangarg.samachar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amangarg.samachar.domain.model.Article
import com.amangarg.samachar.domain.usecase.GetTopHeadlinesByLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsByLanguageViewModel @Inject constructor(
    private val getTopHeadlinesByLanguageUseCase: GetTopHeadlinesByLanguageUseCase
): ViewModel() {

    private val _languageArticles = MutableStateFlow<List<Article>>(emptyList())
    val languageArticles: StateFlow<List<Article>> get() = _languageArticles

    fun fetchNewsByLanguage(language: String, pageNum: Int = 1, pageSize: Int = 10) {
        viewModelScope.launch {
            getTopHeadlinesByLanguageUseCase(language, pageNum, pageSize).collect {
                _languageArticles.value = it
            }
        }
    }
}
