package com.example.BackEnd.Repository;

import com.example.BackEnd.Model.RankingJogador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import static com.example.BackEnd.Repository.BancoDeDados.conexao;

public class TabelaRanking {
    public static List<RankingJogador> getRanking() throws SQLException {
        String sqlRanking = "SELECT * FROM ranking";
        PreparedStatement stmtRanking = conexao.prepareStatement(sqlRanking);
        ResultSet rs = stmtRanking.executeQuery();

        List<RankingJogador> listaRanking = new java.util.ArrayList<>();

        while (rs.next()) {
            RankingJogador ranking = new RankingJogador(
                    rs.getInt("id_jogador"),
                    rs.getString("nome"),
                    rs.getInt("vitorias"),
                    rs.getInt("pontuacao"),
                    rs.getInt("ranking"));

            listaRanking.add(ranking);
        }

        listaRanking.sort(Comparator
                .comparingInt(RankingJogador::getVitorias)
                .thenComparing(RankingJogador::getPontuacao));

        for (RankingJogador rankingJogador : listaRanking) {
            rankingJogador.setRanking(listaRanking.indexOf(rankingJogador) + 1);

            putRanking(rankingJogador);
        }

        return listaRanking;
    }

    public static RankingJogador getRankingUnico(int id) throws SQLException {
        getRanking();

        String sqlRanking = "SELECT * FROM ranking WHERE id_jogador = ?";
        PreparedStatement stmtRanking = conexao.prepareStatement(sqlRanking);

        stmtRanking.setInt(1, id);

        ResultSet rs = stmtRanking.executeQuery();

        RankingJogador ranking = null;

        if (rs.next()) {
            ranking = new RankingJogador(
                    rs.getInt("id_jogador"),
                    rs.getString("nome"),
                    rs.getInt("vitorias"),
                    rs.getInt("pontuacao"),
                    rs.getInt("ranking"));
        }

        return ranking;
    }

    public static void postRanking(int IDjogador, String nomeJogador) throws SQLException {
        String sqlRanking = "INSERT INTO ranking " +
                "(id_jogador, nome, vitorias, pontuacao, ranking) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmtRankingGET_ONE = conexao.prepareStatement(sqlRanking);

        stmtRankingGET_ONE.setInt(1, IDjogador);
        stmtRankingGET_ONE.setString(2, nomeJogador);
        stmtRankingGET_ONE.setInt(3, 0);
        stmtRankingGET_ONE.setInt(4, 0);
        stmtRankingGET_ONE.setInt(5, 0);

        stmtRankingGET_ONE.execute();
        stmtRankingGET_ONE.close();
    }

    public static void putRanking(RankingJogador rankingJogador) throws SQLException {
        String sqlRanking = "UPDATE ranking SET nome = ?, vitorias = ?, pontuacao = ?,ranking = ? WHERE id_jogador = ?";
        PreparedStatement stmtRankingPUT = conexao.prepareStatement(sqlRanking);

        stmtRankingPUT.setString(1, rankingJogador.getNome());
        stmtRankingPUT.setInt(2, rankingJogador.getVitorias());
        stmtRankingPUT.setInt(3, rankingJogador.getPontuacao());
        stmtRankingPUT.setInt(4, rankingJogador.getRanking());
        stmtRankingPUT.setInt(5, rankingJogador.getId());

        stmtRankingPUT.execute();
        stmtRankingPUT.close();
    }
}
