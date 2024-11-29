import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

import java.io.IOException;

public class NotificacaoRecomendacao extends notificacaoSlack {

    public NotificacaoRecomendacao(Slack slack, String token, String mensagem) {
        super(slack, token, mensagem);
    }

    public NotificacaoRecomendacao() {
        super(Slack.getInstance(), System.getenv("TOKEN_ERROR"), "RECOMENDACAO RECEBIDA");
    }

    @Override
    public void enviarNotificacao() throws SlackApiException, IOException {

        System.out.println("TOKEN DE RECOMENDACAO CAPTADO:  " + getToken());

        ChatPostMessageResponse resposta = getSlack().methods(getToken()).chatPostMessage(req -> req
                .channel("Infinity-Solutions")
                .text("RECOMENDACAO RECEBIDA"));
        if (!resposta.isOk()) {
            String errorCode = resposta.getError();
            System.out.println("ERRO:" + errorCode);
        }
    }
}
