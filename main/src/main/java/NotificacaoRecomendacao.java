import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

import java.io.IOException;

public class NotificacaoRecomendacao extends notificacaoSlack {

    public NotificacaoRecomendacao(Slack slack, String token, String mensagem) {
        super(slack, token, mensagem);
    }

    public NotificacaoRecomendacao() {
        super(Slack.getInstance(), System.getenv("TOKEN_RECOMENDACAO"), "");
    }

    public void gerarMensagem(Double variacaoPercentual, Integer qtdTurmasInsRecente) throws SlackApiException, IOException {
        String fraseVariacao = "";
        String mensagemRecomendacao = "";

        if (variacaoPercentual < 0 || variacaoPercentual > 0) {
            if (variacaoPercentual < 0) {
                fraseVariacao = "DIMINUIU em " + Math.abs(variacaoPercentual) + "%";
            } else {
                fraseVariacao = "AUMENTOU em " + variacaoPercentual + "%";
            }

        } else {
            fraseVariacao = "PERMANECEU IGUAL";
        }

        mensagemRecomendacao = String.format("Na atualização dos dados da sua instituição, você teve a INSERÇÃO DE %d TURMAS, e o número de evasões %s, confira a atualização em nossa Dashboard.",
                qtdTurmasInsRecente, fraseVariacao);

        setMensagem(mensagemRecomendacao);
        enviarNotificacao();
    }

    @Override
    public void enviarNotificacao() throws SlackApiException, IOException {
        ChatPostMessageResponse resposta = getSlack().methods(getToken()).chatPostMessage(req -> req
                .channel("Infinity-Solutions")
                .text(getMensagem()));
        if (!resposta.isOk()) {
            String errorCode = resposta.getError();
            System.out.println("ERRO:" + errorCode);
        }
    }


}
