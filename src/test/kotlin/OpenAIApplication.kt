import net.joshuabrandes.OpenAI

fun main() {
    val openAI = OpenAI(System.getenv("OPENAI_API_KEY"))
    while (true) {
        print("You: ")
        val prompt = readlnOrNull()
        if (prompt.isNullOrEmpty() || prompt == "exit") {
            return
        }
        val completion = openAI.chat.postChatCompletion(prompt)
        println("AI: $completion")
    }
}