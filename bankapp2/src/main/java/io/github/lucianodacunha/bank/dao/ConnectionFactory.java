package io.github.lucianodacunha.bank.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private Connection con = null;

    public Connection recuperarConexao() throws RuntimeException{
        try {
            con = createDataSource().getConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

    public void fecharConexao(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private HikariDataSource createDataSource() {
        InputStream prop_file = ConnectionFactory.class.getClassLoader().getResourceAsStream(
                "application.properties");
        Properties prop = new Properties();

        try {
            prop.load(prop_file);
        }  catch (IOException e){
            System.out.println("Erro ao carregar o arquivo de properties");
        }

        String host = prop.getProperty("host");
        String db = prop.getProperty("database");
        String port = prop.getProperty("port");
        String user = prop.getProperty("user");
        String passwd = prop.getProperty("passwd");


        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:mysql://%s:%s/%s".formatted(host, port, db));
//        config.setJdbcUrl("jdbc:mysql://%s:%s/%s".formatted("192.168.0.110", "3306", "BYTE_BANK"));
        config.setJdbcUrl("jdbc:mysql://192.168.0.110:3306/BYTE_BANK");
        config.setUsername(user);
        config.setPassword("senha");
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }
}
