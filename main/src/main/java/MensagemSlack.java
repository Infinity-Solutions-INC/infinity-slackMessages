import com.slack.api.Slack;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

public class MensagemSlack {
    public static void main(String[] args) throws Exception {
        Slack slack = Slack.getInstance();

        String token = System.getenv("TOKEN");

        ChatPostMessageResponse resposta = slack.methods(token).chatPostMessage(req -> req
                .channel("Infinity-Solutions")
                .text("Conexão com o Slack feita com sucesso!"));
        if (!resposta.isOk()) {
            String errorCode = resposta.getError();
            System.out.println("ERRO:" + errorCode);
        }
    }
}
