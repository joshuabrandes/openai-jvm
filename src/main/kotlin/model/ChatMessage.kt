package net.joshuabrandes.model

data class ChatMessage(
    val role: Role,
    val content: String
) {
    enum class Role {
        SYSTEM,
        USER,
        ASSISTANT
    }
}
