package com.amangarg.samachar.data.remote.interceptor

import com.amangarg.samachar.di.qualifier.ApiKey
import com.amangarg.samachar.common.util.NetworkConstants.X_API_KEY
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.jvm.Throws

@Singleton
class ApiKeyInterceptor @Inject constructor(@param:ApiKey private val apiKey: String) :
    Interceptor {

    @Throws(IOException::class)
    @Synchronized
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header(X_API_KEY, apiKey)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}