package net.joshuabrandes.config

enum class Model(private val model: String) {
    GPT4("gpt-4"),
    GPT4_TURBO("gpt-4-turbo-preview"),
    GPT3("gpt-3.5-turbo");


    fun getModel(): String = model

    companion object {
        private val modelsMap = entries.associateBy { it.model }

        fun fromString(name: String): Model? = modelsMap[name]
    }
}