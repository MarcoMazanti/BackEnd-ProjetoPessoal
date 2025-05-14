package com.example.BackEnd.Model;

public class UsuarioBackEnd {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private byte[] imagem;

    public UsuarioBackEnd(int id, String nome, String email, String senha, byte[] imagem) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.imagem = imagem;
    }

    public UsuarioBackEnd(String nome, String email, String senha, byte[] imagem) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}