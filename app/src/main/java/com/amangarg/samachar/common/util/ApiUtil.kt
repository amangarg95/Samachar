package com.amangarg.samachar.common.util

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T> {
    return try {
        val response = apiCall()
        ResultWrapper.Success(response)
    } catch (e: IOException) {
        ResultWrapper.NetworkError    // No internet or timeout
    } catch (e: HttpException) {
        val code = e.code()
        val errorBody = e.response()?.errorBody()?.string()
        ResultWrapper.GenericError(code, errorBody)
    } catch (e: Exception) {
        ResultWrapper.GenericError(null, e.localizedMessage)
    }
}
