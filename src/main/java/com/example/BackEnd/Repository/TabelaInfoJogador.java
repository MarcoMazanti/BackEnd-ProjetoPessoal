package com.example.BackEnd.Repository;

import com.example.BackEnd.Model.InfoJogador;
import com.example.BackEnd.Model.RankingJogador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
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
                    rs.getInt("pontuacao")
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
                    rs.getInt("pontuacao")
            );
        }

        return jogador;
    }

    public static List<RankingJogador> getRanking() throws SQLException {
        String sql = "SELECT * FROM info_jogador";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        List<RankingJogador> listaRankingJogadores = new ArrayList<>();
        while (rs.next()) {
            listaRankingJogadores.add(new RankingJogador(
                    rs.getInt("id_jogador"),
                    rs.getString("nome"),
                    rs.getInt("pontuacao"),
                    0
            ));
        }
        listaRankingJogadores.sort(Comparator.comparing(RankingJogador::getPontuacao).reversed());

        int index = 1;
        for (RankingJogador jogador : listaRankingJogadores) {
            jogador.setRanking(index);
            index++;
        }

        rs.close();
        stmt.close();

        return listaRankingJogadores;
    }

    public static RankingJogador getRankingJogador(int id) throws SQLException {
        List<RankingJogador> listaRankingJogadores = getRanking();

        RankingJogador jogador = null;

        for (RankingJogador jogadorAlvo : listaRankingJogadores) {
            if (jogadorAlvo.getId() == id) {
                jogador = jogadorAlvo;
                break;
            }
        }

        return jogador;
    }

    public static void postInfoJogador(int IDjogador, String nomeJogador) throws SQLException {
        String sqlInfoJogador = "INSERT INTO info_jogador " +
                "(id_jogador, nome, pontuacao) " +
                "VALUES (?, ?, ?)";
        PreparedStatement stmtInfoJogador = conexao.prepareStatement(sqlInfoJogador);

        stmtInfoJogador.setInt(1, IDjogador);
        stmtInfoJogador.setString(2, nomeJogador);
        stmtInfoJogador.setInt(3, 0);

        stmtInfoJogador.execute();
        stmtInfoJogador.close();
    }

    public static void putInfoJogador(int IDjogador, int pontuacao) throws SQLException {
        String sql = "UPDATE info_jogador SET pontuacao = ? WHERE id_jogador = ?";
        PreparedStatement stmtInfoJogador = conexao.prepareStatement(sql);

        stmtInfoJogador.setInt(1, pontuacao);
        stmtInfoJogador.setInt(2, IDjogador);

        stmtInfoJogador.execute();
        stmtInfoJogador.close();
    }

    public static void deleteInfoJogador(int IDjogador) throws SQLException {
        String sql = "DELETE FROM info_jogador WHERE id_jogador = ?";
        PreparedStatement stmtInfoJogador = conexao.prepareStatement(sql);

        stmtInfoJogador.setInt(1, IDjogador);

        stmtInfoJogador.execute();
        stmtInfoJogador.close();
    }
}
