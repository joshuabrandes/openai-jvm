import net.joshuabrandes.OpenAI;

public class OpenAITest {
    public static void main(String[] args) {
        OpenAI openAI = new OpenAI("API_KEY");
        System.out.println(openAI.getChat().postChatCompletion("Hello, how are you?"));
    }
}
