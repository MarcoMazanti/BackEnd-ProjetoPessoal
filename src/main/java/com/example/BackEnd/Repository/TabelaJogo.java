package com.example.BackEnd.Repository;

import com.example.BackEnd.Model.Jogo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.BackEnd.Model.Jogo.arrayToString;
import static com.example.BackEnd.Repository.BancoDeDados.conexao;

public class TabelaJogo {
    public static List<Jogo> getJogosJogador(int id_jogador) throws SQLException, JsonProcessingException {
        String sql = "SELECT * FROM jogo WHERE id_jogador = ?";
        PreparedStatement stmtInfoJogador = conexao.prepareStatement(sql);

        stmtInfoJogador.setInt(1, id_jogador);

        ResultSet rs = stmtInfoJogador.executeQuery();
        List<Jogo> jogosJogador = new ArrayList<>();

        while (rs.next()) {
            String mapaJSON = rs.getString("mapa");

            ObjectMapper mapper = new ObjectMapper();
            int[][] mapaArray = mapper.readValue(mapaJSON, int[][].class);

            jogosJogador.add(new Jogo(
                    rs.getInt("id"),
                    rs.getInt("id_jogador"),
                    mapaArray,
                    rs.getTimestamp("data"),
                    rs.getInt("pontuacao"),
                    rs.getInt("fim")
            ));
        }

        stmtInfoJogador.close();
        rs.close();

        return jogosJogador;
    }

    public static Jogo getJogoAndamento(int id_jogador) throws SQLException, JsonProcessingException {
        List<Jogo> listaJogos = getJogosJogador(id_jogador);

        for (Jogo jogo : listaJogos) {
            if (!jogo.isEhFim()) {
                return jogo;
            }
        }

        return null;
    }

    public static String postNovoJogo(Jogo jogo) throws SQLException {
        String sql = "INSERT INTO  jogo (id_jogador, mapa, data, pontuacao, fim) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, jogo.getId_jogador());
        stmt.setString(2, String.format("%s", arrayToString(jogo.getArray())));
        stmt.setTimestamp(3, jogo.getData());
        stmt.setInt(4, jogo.getPontuacao());
        stmt.setBoolean(5, jogo.isEhFim());

        stmt.execute();
        stmt.close();

        return "Novo jogo criado com sucesso!";
    }

    public static String updateJogo(int id_jogador, int pontuacao) throws SQLException, JsonProcessingException {
        Jogo jogo = getJogoAndamento(id_jogador);

        if (jogo != null) {
            String sql = "UPDATE jogo WHERE id = ? SET pontuacao = ? AND fim = 1";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, jogo.getId());
            stmt.setInt(2, pontuacao);

            stmt.execute();
            stmt.close();

            return "Jogo finalizado com sucesso!";
        } else {
            return "Não foi possível finalizar o jogo!";
        }
    }
}
