package com.amangarg.samachar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amangarg.samachar.domain.model.Article
import com.amangarg.samachar.domain.usecase.GetTopHeadlinesBySourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsBySourceViewModel @Inject constructor(
    private val getTopHeadlinesBySourceUseCase: GetTopHeadlinesBySourceUseCase
) : ViewModel() {

    private val _sourceArticles = MutableStateFlow<List<Article>>(emptyList())
    val sourceArticles: StateFlow<List<Article>> get() = _sourceArticles

    fun fetchNewsBySource(source: String, pageNum: Int = 1, pageSize: Int = 10) {
        viewModelScope.launch {
            getTopHeadlinesBySourceUseCase(source, pageNum, pageSize).collect {
                _sourceArticles.value = it
            }
        }
    }
}
