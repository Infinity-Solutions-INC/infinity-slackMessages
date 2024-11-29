import com.slack.api.methods.SlackApiException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SqlQuerys {
    private static ConexaoBd dbConnectionProvider = new ConexaoBd();
    private static JdbcTemplate connection = dbConnectionProvider.getConnection();
    LocalDate dataHoje = LocalDate.now();

    public static void consultarLogsError() throws SlackApiException, IOException {
//        LocalDate dataHoje = LocalDate.now();
        String dataHoje = "%2024-11-29%";

        Integer qtdLinhas = connection.queryForObject(
                """
                        select count(id) from error_logs where dt_hr_captacao_error like ?""",
                Integer.class,
                dataHoje
        );

        if (qtdLinhas > 0) {
            Integer idInicialPeriodo = connection.queryForObject(
                    """
                            select id from error_logs where dt_hr_captacao_error like ? limit 1""",
                    Integer.class,
                    dataHoje
            );


            Integer id = idInicialPeriodo;

            for (int i = 1; i <= qtdLinhas; i++) {
                String errorLog = connection.queryForObject(
                        """
                                select mensagem_error from error_logs where id = ?""",
                        String.class,
                        id
                );

                String dtHrCaptacao = connection.queryForObject(
                        """
                                select dt_hr_captacao_error from error_logs where id = ?""",
                        String.class,
                        id
                );

                enviarMensagemError(errorLog, dtHrCaptacao);
                id++;
            }
        }

    }

    public static void enviarMensagemError(String errorLog, String dtHrCaptacao) throws SlackApiException, IOException {
        DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime horaCaptacao = LocalDateTime.parse(dtHrCaptacao, formatoEntrada);
        DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = horaCaptacao.format(formatoSaida);

        String slackMessage = horaFormatada + "\n" + errorLog + "\n";

        NotificacaoErrors msgError = new NotificacaoErrors();
        msgError.setMensagem(slackMessage);
        msgError.enviarNotificacao();
    }
}
