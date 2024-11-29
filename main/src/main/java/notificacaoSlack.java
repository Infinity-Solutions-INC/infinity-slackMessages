import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;

import java.io.IOException;

public abstract class notificacaoSlack {
    private Slack slack;
    private String token;
    private String mensagem;

    public abstract void enviarNotificacao() throws SlackApiException, IOException;

    public notificacaoSlack(Slack slack, String token, String mensagem) {
        this.slack = Slack.getInstance();
        this.token = token;
        this.mensagem = mensagem;
    }

    public Slack getSlack() {
        return slack;
    }

    public void setSlack(Slack slack) {
        this.slack = slack;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
