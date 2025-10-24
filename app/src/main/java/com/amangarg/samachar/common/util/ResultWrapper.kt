package com.amangarg.samachar.common.util

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError<out T>(val code: Int?, val message: String?) : ResultWrapper<T>()
    object NetworkError : ResultWrapper<Nothing>()
}