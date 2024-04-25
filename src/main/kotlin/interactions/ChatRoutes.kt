package net.joshuabrandes.interactions

import net.joshuabrandes.model.ChatCompletionRequest
import net.joshuabrandes.model.ChatCompletionResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

fun interface ChatRoutes {
    @POST("/chat/completions")
    suspend fun postChatCompletion(
        @Body completionRequest: ChatCompletionRequest,
    ): Call<ChatCompletionResponse>
}