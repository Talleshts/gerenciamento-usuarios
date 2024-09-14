package com.ufes.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDBService {
    private static ConnectionDBService INSTANCE;
    private Connection connection;

    private ConnectionDBService() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            System.out.println("Conexão com o banco de dados estabelecida.");
        } catch (SQLException e) {
            System.err.println("Erro na conexão do banco de dados");
            throw new RuntimeException(e);
        }
    }

    public static ConnectionDBService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionDBService();
        }
        return INSTANCE;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:sample.db");
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão com o banco de dados fechada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão do banco de dados");
            throw new RuntimeException(e);
        }
    }
}

