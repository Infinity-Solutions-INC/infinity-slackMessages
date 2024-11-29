import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class ConexaoBd {
    private final DataSource dataSource;
//        private String url = "jdbc:mysql://mysql-app:3306/?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
   private String url = "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC";


//        private String username = "root";
//    private String passwd = "rootpassword";
    private String username = "sptech";
    private String passwd = "123";


    public ConexaoBd() {
        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl("jdbc:mysql://mysql-app:3306/infinity_solutions?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/infinity_solutions?useSSL=false&serverTimezone=UTC");
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(passwd);
        this.dataSource = basicDataSource;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carregar o driver explicitamente
            try (Connection con = DriverManager.getConnection(url, username, passwd);
                 Statement statement = con.createStatement()) {
                String sql = "CREATE DATABASE IF NOT EXISTS infinity_solutions;";
                statement.executeUpdate(sql);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.getMessage();
        }
    }

    public JdbcTemplate getConnection() {
        return new JdbcTemplate(dataSource);
    }
}
