package com.example.BackEnd.Repository;

import com.example.BackEnd.Model.InfoJogador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.BackEnd.Repository.BancoDeDados.conexao;

public class TabelaInfoJogador {
    public static List<InfoJogador> getInfoJogador() throws SQLException {
        String sql = "SELECT * FROM info_jogador";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<InfoJogador> listaInfoJogadores = new ArrayList<>();

        while (rs.next()) {
            InfoJogador jogador = new InfoJogador(
                    rs.getInt("id_jogador"),
                    rs.getString("nome"),
                    rs.getInt("pontuacao"),
                    rs.getInt("ranking"),
                    rs.getInt("jogos_participados"),
                    rs.getInt("vitorias"),
                    rs.getInt("empates"),
                    rs.getInt("derrotas")
            );

            listaInfoJogadores.add(jogador);
        }

        rs.close();
        stmt.close();

        return listaInfoJogadores;
    }

    public static InfoJogador getInfoJogadorUnico(int id) throws SQLException {
        String sql = "SELECT * FROM info_jogador WHERE id_jogador = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        InfoJogador jogador = null;

        if (rs.next()) {
            jogador = new InfoJogador(
                    rs.getInt("id_jogador"),
                    rs.getString("nome"),
                    rs.getInt("pontuacao"),
                    rs.getInt("ranking"),
                    rs.getInt("jogos_participados"),
                    rs.getInt("vitorias"),
                    rs.getInt("empates"),
                    rs.getInt("derrotas")
            );
        }

        return jogador;
    }

    public static void postInfoJogador(int IDjogador, String nomeJogador) throws SQLException {
        String sqlInfoJogador = "INSERT INTO info_jogador " +
                "(id_jogador, nome, pontuacao, ranking, jogos_participados, vitorias, empates, derrotas) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmtInfoJogador = conexao.prepareStatement(sqlInfoJogador);

        stmtInfoJogador.setInt(1, IDjogador);
        stmtInfoJogador.setString(2, nomeJogador);
        stmtInfoJogador.setInt(3, 0);
        stmtInfoJogador.setInt(4, 0);
        stmtInfoJogador.setInt(5, 0);
        stmtInfoJogador.setInt(6, 0);
        stmtInfoJogador.setInt(7, 0);
        stmtInfoJogador.setInt(8, 0);

        stmtInfoJogador.execute();
        stmtInfoJogador.close();
    }
}
