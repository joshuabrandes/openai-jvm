package net.joshuabrandes.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

data class ChatVisionMessage @JvmOverloads
constructor(
    val role: Role = Role.USER,
    val content: List<VisionMessageContent>
) : ChatContent() {

    constructor(role: Role, content: List<VisionMessageContent>, maxTokens: Int) : this(role, content) {
        this.maxTokens = maxTokens
    }

    data class VisionMessageContent(
        val type: MessageType,
        @JsonIgnore val value: String
    ) {

        var text: String? = null

        @JsonProperty("image_url")
        var imageUrl: ImageUrl? = null

        data class ImageUrl(
            val url: String
        )

        init {
            when (type) {
                MessageType.TEXT -> text = value
                MessageType.IMAGE -> imageUrl = ImageUrl(value)
            }
        }
    }

    enum class MessageType(private val type: String) {
        TEXT("text"),
        IMAGE("image_url");

        companion object {
            private val typesMap = values().associateBy(MessageType::type)

            fun fromString(type: String) = typesMap[type]
        }
    }
}
