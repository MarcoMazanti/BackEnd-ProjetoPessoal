package com.example.BackEnd.Repository;

import com.example.BackEnd.Model.Conversa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.BackEnd.Repository.BancoDeDados.conexao;

public class TabelaConversa {
    public static List<Conversa> getConversaID(int idAmizade) throws SQLException {
        String sql = "select * from Conversa where id_amizade = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, idAmizade);

        ResultSet rs = stmt.executeQuery();
        List<Conversa> lista = new ArrayList<>();

        while (rs.next()) {
            lista.add(new Conversa(
                    rs.getInt("id_amizade"),
                    rs.getInt("id_jogador"),
                    rs.getString("mensagem")));
        }

        rs.close();
        stmt.close();

        return lista;
    }

    public static String postMensagem(Conversa conversa) throws SQLException {
        String sql = "INSERT INTO conversa (id_amizade, id_jogador, mensagem) VALUES (?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, conversa.getIdAmizade());
        stmt.setInt(2, conversa.getIdJogador());
        stmt.setString(3, conversa.getMensagem());

        stmt.executeUpdate();

        stmt.close();

        return "Mensagem postada com sucesso!";
    }

    public static String deleteConversa(int idAmizade) throws SQLException {
        String sql = "DELETE FROM Conversa WHERE id_amizade = ?\n";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, idAmizade);

        stmt.executeUpdate();

        stmt.close();

        return "Mensagem deletada com sucesso!";
    }
}
