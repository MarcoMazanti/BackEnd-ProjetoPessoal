package com.example.BackEnd.Repository;

import com.example.BackEnd.Model.UsuarioBackEnd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.BackEnd.Repository.BancoDeDados.conexao;

public class ManipularDados {
    public static UsuarioBackEnd getUsuario(String email) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setString(1, email);

        ResultSet resultado = stmt.executeQuery();
        UsuarioBackEnd usuario = null;

        if (resultado.next()) {
            usuario = new UsuarioBackEnd(
                    resultado.getInt("id"),
                    resultado.getString("nome"),
                    resultado.getString("email"),
                    resultado.getString("senha"),
                    resultado.getBytes("imagem"));
        }

        resultado.close();
        stmt.close();

        return usuario;
    }

    public static String postUsuario(UsuarioBackEnd usuario){
        try {
            String sql = "INSERT INTO usuario (nome, email, senha, imagem) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setBytes(4, usuario.getImagem());

            stmt.execute();
            stmt.close();

            return "Usuario cadastrado com sucesso!";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String putUsuario(int id, String nome, String email, String senha, byte[] imagem) {
        try {
            String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, imagem = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.setBytes(4, imagem);
            stmt.setInt(5, id);

            stmt.execute();
            stmt.close();

            return "Usuario alterado com sucesso!";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String deleteUsuario(String email) throws SQLException {
        String sql = "DELETE FROM usuario WHERE email = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setString(1, email);

        stmt.execute();
        stmt.close();

        return "Usuario excluÃdo com sucesso!";
    }
}