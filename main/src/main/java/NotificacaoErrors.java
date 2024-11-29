import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

import java.io.IOException;

public class NotificacaoErrors extends notificacaoSlack {

    public NotificacaoErrors(Slack slack, String token, String mensagem) {
        super(slack, token, mensagem);
    }

    public NotificacaoErrors() {
        super(Slack.getInstance(), System.getenv("TOKEN_ERROR"), "");
    }

    @Override
    public void enviarNotificacao() throws SlackApiException, IOException {

        ChatPostMessageResponse resposta = getSlack().methods(getToken()).chatPostMessage(req -> req
                .channel("logs-erro-infinitysolutions")
                .text(getMensagem()));
        if (!resposta.isOk()) {
            String errorCode = resposta.getError();
            System.out.println("ERRO:" + errorCode);
        }
    }
}
