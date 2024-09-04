package com.ufes.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConectionDBService {
    private static ConectionDBService INSTANCE;

    private Connection connection = null;


    private ConectionDBService() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
        } catch (SQLException e) {
            System.err.println("Erro na conexão do banco de dados");
            throw new RuntimeException(e);
        }
    }

    public static ConectionDBService getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ConectionDBService();
        }
        return INSTANCE;
    }
    public Connection getConnection(){
        return this.connection;
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
