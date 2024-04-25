package net.joshuabrandes.interactions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import net.joshuabrandes.config.Model
import net.joshuabrandes.model.ChatCompletionRequest
import net.joshuabrandes.model.ChatMessage
import retrofit2.Retrofit

class Chat @JvmOverloads
constructor(
    client: Retrofit,
    private val model: Model = Model.GPT3,
    private val historySize: Int = 3,
    private val systemMessage: String =
        "You are a helpful chat assistant and try" +
                " to execute the user's commands to the " +
                "best of your ability."
) {

    private val chatClient = client.create(ChatRoutes::class.java)
    private val history = mutableListOf<ChatMessage>()

    @JvmOverloads
    fun postChatCompletion(
        prompt: String,
        role: ChatMessage.Role = ChatMessage.Role.USER,
        model: Model = this.model
    ): String {
        val message = ChatMessage(role, prompt)
        history.addMessage(message)

        val completion = runBlocking(Dispatchers.IO) {
            chatClient.postChatCompletion(
                ChatCompletionRequest(
                    model = model,
                    messages = getMessagesFromHistory()
                )
            ).execute()
        }

        if (completion.isSuccessful) {
            val response = completion.body()!!
            val completionMessage = response.choices[0].message
            history.addMessage(completionMessage)
            return completionMessage.content
        } else {
            throw Exception("Failed to get completion: ${completion.errorBody()}")
        }
    }

    private fun getMessagesFromHistory(): List<ChatMessage> {
        val messages = mutableListOf(ChatMessage(ChatMessage.Role.SYSTEM, systemMessage))
        messages.addAll(history)

        return messages
    }


    private fun MutableList<ChatMessage>.addMessage(message: ChatMessage) {
        if (message.role == ChatMessage.Role.SYSTEM) {
            return
        }
        if (size >= historySize) {
            removeAt(0)
        }
        add(message)
    }
}
