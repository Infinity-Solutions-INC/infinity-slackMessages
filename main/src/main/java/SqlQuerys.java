import org.springframework.jdbc.core.JdbcTemplate;

public class SqlQuerys {
    ConexaoBd dbConnectionProvider = new ConexaoBd();
    JdbcTemplate connection = dbConnectionProvider.getConnection();
}
