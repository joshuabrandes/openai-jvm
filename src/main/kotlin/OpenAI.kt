package net.joshuabrandes

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import net.joshuabrandes.config.AuthInterceptor
import net.joshuabrandes.interactions.Chat
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class OpenAI @JvmOverloads constructor(
    apiKey: String,
    private val url: String = "https://api.openai.com/v1",
    chatSystemMessage: String?
) {

    @JvmName("getUrl")
    fun url() = url

    private val mapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
    private val interceptor = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(apiKey))
        .build()
    private val client = Retrofit.Builder()
        .client(interceptor)
        .baseUrl(url)
        .addConverterFactory(JacksonConverterFactory.create(mapper))
        .build()

    val chat = if (chatSystemMessage != null) {
        Chat(client, systemMessage = chatSystemMessage)
    } else {
        Chat(client)
    }
}