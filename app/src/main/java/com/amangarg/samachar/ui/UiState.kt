package com.amangarg.samachar.ui

sealed interface UiState<out T> {
    object Idle : UiState<Nothing>
    object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error<T>(val message: String) : UiState<T>
}