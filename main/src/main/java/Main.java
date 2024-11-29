import com.slack.api.methods.SlackApiException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws SlackApiException, IOException {
        ConexaoBd conexao = new ConexaoBd();

        NotificacaoErrors msgErro = new NotificacaoErrors();
        NotificacaoRecomendacao msgReco = new NotificacaoRecomendacao();

        msgReco.enviarNotificacao();
        msgErro.enviarNotificacao();

        SqlQuerys.consultarLogsError();

    }
}
