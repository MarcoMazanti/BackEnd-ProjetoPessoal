package com.example.BackEnd.Repository;

import com.example.BackEnd.Model.Amizade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.BackEnd.Repository.BancoDeDados.conexao;

public class TabelaAmizade {
    public static List<Amizade> getTodasAmizades() throws SQLException {
        String sql = "select * from Amizade";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Amizade> lista = new ArrayList<>();

        while (rs.next()) {
            lista.add(new Amizade(
                    rs.getInt("id_jogador_1"),
                    rs.getInt("id_jogador_2"),
                    rs.getInt("amizade_pendente")
            ));
        }

        return lista;
    }

    public static List<Amizade> getTodasAmizadesPendentes() throws SQLException {
        String sql = "SELECT * FROM amizade WHERE amizade_pendente = 1";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Amizade> listaAmizadesPendente = new java.util.ArrayList<>();

        while (rs.next()) {
            listaAmizadesPendente.add(new Amizade(
                    rs.getInt("id_jogador_1"),
                    rs.getInt("id_jogador_2"),
                    rs.getInt("amizade_pendente")));
        }

        return listaAmizadesPendente;
    }

    public static List<Amizade> getTodasAmizadesFeitas() throws SQLException {
        String sql = "SELECT * FROM amizade WHERE amizade_pendente = 0";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Amizade> listaAmizadesPendente = new java.util.ArrayList<>();

        while (rs.next()) {
            listaAmizadesPendente.add(new Amizade(
                    rs.getInt("id_jogador_1"),
                    rs.getInt("id_jogador_2"),
                    rs.getInt("amizade_pendente")));
        }

        return listaAmizadesPendente;
    }

    public static List<Amizade> getAmizadePendenteJogador(int jogador) throws SQLException {
        String sql = "SELECT * FROM amizade WHERE amizade_pendente = 1";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Amizade> listaAmizadePendente = new java.util.ArrayList<>();

        while (rs.next()) {
            if (jogador == rs.getInt("id_jogador_1") || jogador == rs.getInt("id_jogador_2")) {
                listaAmizadePendente.add(new Amizade(
                        rs.getInt("id_jogador_1"),
                        rs.getInt("id_jogador_2"),
                        rs.getInt("amizade_pendente")));
            }
        }

        rs.close();
        stmt.close();

        return listaAmizadePendente;
    }

    public static List<Amizade> getAmizadeFeitaJogador(int jogador) throws SQLException {
        String sql = "SELECT * FROM amizade WHERE amizade_pendente = 0";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Amizade> listaAmizadePendente = new java.util.ArrayList<>();

        while (rs.next()) {
            if (jogador == rs.getInt("id_jogador_1") || jogador == rs.getInt("id_jogador_2")) {
                listaAmizadePendente.add(new Amizade(
                        rs.getInt("id_jogador_1"),
                        rs.getInt("id_jogador_2"),
                        rs.getInt("amizade_pendente")));
            }
        }

        rs.close();
        stmt.close();

        return listaAmizadePendente;
    }

    public static String postAmizadePendente(Amizade amizadePendente) throws SQLException {
        String sql = "INSERT INTO amizade (id_jogador_1, id_jogador_2, amizade_pendente) VALUES (?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, amizadePendente.getId_jogador_1());
        stmt.setInt(2, amizadePendente.getId_jogador_2());
        stmt.setBoolean(3, amizadePendente.isAmizade_pendente());

        stmt.execute();
        stmt.close();

        return "Amizade Pendente adicionado com sucesso";
    }

    public static String putAmizadeFeita(Amizade amizadeFeita) throws SQLException {
        String sql = "UPDATE amizade SET amizade_pendente = ? WHERE id_jogador_1 = ? AND id_jogador_2 = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, 0);
        stmt.setInt(2, amizadeFeita.getId_jogador_1());
        stmt.setInt(3, amizadeFeita.getId_jogador_2());

        stmt.execute();
        stmt.close();

        return "Amizade Feita adicionado com sucesso";
    }

    public static String deleteAmizadeFeita(Amizade amizadeFeita) throws SQLException {
        String sql = "DELETE FROM amizade WHERE id_jogador_1 = ? AND id_jogador_2 = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, amizadeFeita.getId_jogador_1());
        stmt.setInt(2, amizadeFeita.getId_jogador_2());

        stmt.execute();

        stmt.setInt(1, amizadeFeita.getId_jogador_2());
        stmt.setInt(2, amizadeFeita.getId_jogador_1());

        stmt.execute();
        stmt.close();

        return "Amizade removida com sucesso";
    }
}
