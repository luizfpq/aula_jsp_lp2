package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    // Inicialização estática para registrar o driver MySQL manualmente
    static {
        try {
            // Registrar manualmente o driver do MySQL
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());  
        } catch (SQLException e) {
            throw new ExceptionInInitializerError("Erro ao registrar o driver do MySQL: " + e.getMessage());
        }
    }

    // Método para obter a conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        // Obter os detalhes do banco de dados a partir das variáveis de ambiente
        String dbUrl = System.getenv("DB_URL");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        // Verificar se as variáveis de ambiente estão configuradas corretamente
        if (dbUrl == null || dbUsername == null || dbPassword == null) {
            throw new SQLException("Configurações de banco de dados não encontradas nas variáveis de ambiente.");
        }

        // Conectar ao banco de dados usando os detalhes das variáveis de ambiente
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
}
