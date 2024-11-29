import com.slack.api.methods.SlackApiException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws SlackApiException, IOException {
        ConexaoBd conexao = new ConexaoBd();

//        MensagemError msgErro = new MensagemError();
//        MensagemRecomendacao msgReco = new MensagemRecomendacao();
//
//        msgReco.enviarMensagem();
//        msgErro.enviarMensagem();

        SqlQuerys.consultarLogsError();

    }
}
