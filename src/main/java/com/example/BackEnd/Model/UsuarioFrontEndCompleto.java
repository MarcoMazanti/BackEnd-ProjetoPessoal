package com.example.BackEnd.Model;

import java.util.Base64;

public class UsuarioFrontEndCompleto {
    private int id;
    private String nome;
    private String email;
    private String imagem;
    private int pontuacao;
    private int jogosParticipados;
    private int vitorias;
    private int empates;
    private int derrotas;

    public UsuarioFrontEndCompleto(int id, String nome, String email, byte[] imagem, int pontuacao, int jogosParticipados, int vitorias, int empates, int derrotas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        if (imagem != null && imagem.length > 0) {
            this.imagem = Base64.getEncoder().encodeToString(imagem);
        } else {
            this.imagem = null;
        }
        this.pontuacao = pontuacao;
        this.jogosParticipados = jogosParticipados;
        this.vitorias = vitorias;
        this.empates = empates;
        this.derrotas = derrotas;
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

    public int getJogosParticipados() {
        return jogosParticipados;
    }

    public void setJogosParticipados(int jogosParticipados) {
        this.jogosParticipados = jogosParticipados;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }
}
