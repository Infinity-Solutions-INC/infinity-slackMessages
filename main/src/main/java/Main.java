import com.slack.api.methods.SlackApiException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws SlackApiException, IOException {
        SqlQuerys.consultarLogsError();
        SqlQuerys.calcularPorcentagemEvasao();
    }
}
