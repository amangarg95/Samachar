package com.amangarg.samachar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amangarg.samachar.domain.model.Source
import com.amangarg.samachar.domain.usecase.GetSourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourcesScreenViewModel @Inject constructor(
    private val getSourcesUseCase: GetSourcesUseCase
) : ViewModel() {

    private val _sources = MutableStateFlow<List<Source>>(emptyList())
    val sources: StateFlow<List<Source>> get() = _sources

    fun fetchSources() {
        viewModelScope.launch {
            getSourcesUseCase().collect { _sources.value = it }
        }
    }
}
