package net.joshuabrandes.model

import net.joshuabrandes.config.Model

data class ChatCompletionRequest(
    val model: Model,
    var messages: List<ChatMessage>
)
