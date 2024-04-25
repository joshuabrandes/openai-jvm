package net.joshuabrandes.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.awt.Choice


data class ChatCompletionResponse(
    val choices: List<Choice>,
    val usage: Usage
) {
    data class Choice(
        val message: ChatMessage,
        val index: Int,
        @JsonProperty("finish_reason") val finishReason: String
    )

    data class Usage(
        @JsonProperty("completion_tokens") val completionTokens: Int,
        @JsonProperty("prompt_tokens") val promptTokens: Int,
        @JsonProperty("total_tokens") val totalTokens: Int
    )
}
