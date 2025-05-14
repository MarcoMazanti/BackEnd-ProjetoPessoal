package com.example.BackEnd.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDeDados {
    private static final String URL = "jdbc:mysql://localhost:3306/projeto_fullstack";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    protected static Connection conexao = null;

    public static void Conectar() {
        try {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão bem-sucedida!");

        } catch (SQLException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
        }
    }

    public static void Desconectar() {
        try {
            if (conexao != null) {
                conexao.close();
                System.out.println("Conexão encerrada!");
            } else {
                System.err.println("Não há conexão para ser encerrada!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao encerrar conexão: " + e.getMessage());
        }
    }
}