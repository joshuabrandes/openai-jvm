package net.joshuabrandes.model

data class ChatMessage(
    val role: Role,
    val content: String
) : ChatContent() {
    constructor(role: Role, content: String, maxTokens: Int) : this(role, content) {
        this.maxTokens = maxTokens
    }
}
