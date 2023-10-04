package com.example.application.data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class FabricaDeConexoes {

    private final static String URL = "jdbc:sqlite:src/main/java/com/example/application/banco/lembrou.db";

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
