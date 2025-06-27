package com.example.BackEnd.Model;

import java.util.Base64;

public class UsuarioFrontEndCompleto {
    private int id;
    private String nome;
    private String email;
    private String imagem;
    private int pontuacao;

    public UsuarioFrontEndCompleto(int id, String nome, String email, byte[] imagem, int pontuacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        if (imagem != null && imagem.length > 0) {
            this.imagem = Base64.getEncoder().encodeToString(imagem);
        } else {
            this.imagem = null;
        }
        this.pontuacao = pontuacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
