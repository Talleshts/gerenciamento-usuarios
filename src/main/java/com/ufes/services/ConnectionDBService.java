package com.ufes.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDBService {
    private static ConnectionDBService INSTANCE;

    private Connection connection = null;


    private ConnectionDBService() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
        } catch (SQLException e) {
            System.err.println("Erro na conexão do banco de dados");
            throw new RuntimeException(e);
        }
    }

    public static ConnectionDBService getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ConnectionDBService();
        }
        return INSTANCE;
    }
    public Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:sqlite:sample.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Erro em fechar a conexão do banco de dados");
            throw new RuntimeException(e);
        }
    }
}
