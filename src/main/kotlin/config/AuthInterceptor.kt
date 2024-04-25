package net.joshuabrandes.config

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor
@JvmOverloads constructor(private val token: String? = null) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            if (token != null) {
                chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .addHeader("Content-Type", "application/json")
                    .build()
            } else {
                chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
            }
        return chain.proceed(request)
    }
}