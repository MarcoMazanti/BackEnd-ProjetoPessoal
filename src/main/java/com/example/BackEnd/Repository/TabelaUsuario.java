package com.example.BackEnd.Repository;

import com.example.BackEnd.Model.UsuarioBackEnd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.example.BackEnd.Repository.BancoDeDados.conexao;

public class TabelaUsuario {
    public static List<UsuarioBackEnd> getUsuarios() throws SQLException {
        String sql = "SELECT * FROM usuario";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<UsuarioBackEnd> listaUsuarios = new java.util.ArrayList<>();

        while (rs.next()) {
            UsuarioBackEnd usuario = new UsuarioBackEnd(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getBytes("imagem"));

            listaUsuarios.add(usuario);
        }

        rs.close();
        stmt.close();

        return listaUsuarios;
    }

    public static UsuarioBackEnd getUsuarioUnicoEmail(String email) throws SQLException {
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

    public static UsuarioBackEnd getUsuarioUnicoID(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, id);

        ResultSet resultado = stmt.executeQuery();
        UsuarioBackEnd usuarioID = null;

        if (resultado.next()) {
            usuarioID = new UsuarioBackEnd(
                    resultado.getInt("id"),
                    resultado.getString("nome"),
                    resultado.getString("email"),
                    resultado.getString("senha"),
                    resultado.getBytes("imagem"));
        }

        resultado.close();
        stmt.close();

        return usuarioID;
    }

    public static List<UsuarioBackEnd> getUsuarioNome(String nome) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE nome = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setString(1, nome);

        ResultSet resultado = stmt.executeQuery();
        List<UsuarioBackEnd> usuarioNome = null;

        if (resultado.next()) {
            usuarioNome.add(new UsuarioBackEnd(
                    resultado.getInt("id"),
                    resultado.getString("nome"),
                    resultado.getString("email"),
                    resultado.getString("senha"),
                    resultado.getBytes("imagem")));
        }

        resultado.close();
        stmt.close();

        return usuarioNome;
    }

    public static String postUsuario(UsuarioBackEnd usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, senha, imagem) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setBytes(4, usuario.getImagem());

        stmt.execute();
        stmt.close();

        // adcionar o usuario nas demais tabelas
        UsuarioBackEnd usuarioLogado = getUsuarioUnicoEmail(usuario.getEmail());

        // Tabela info_jogador
        TabelaInfoJogador.postInfoJogador(usuarioLogado.getId(), usuarioLogado.getNome());

        return "Usuario cadastrado com sucesso!";
    }

    public static String putUsuario(int id, String nome, String email, String senha, byte[] imagem) throws SQLException {
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
    }

    public static String deleteUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, id);

        stmt.execute();
        stmt.close();

        return "Usuario excluido com sucesso!";
    }
}