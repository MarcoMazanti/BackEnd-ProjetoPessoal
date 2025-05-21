package com.example.BackEnd.Model;

public class InfoJogador {
    private int id;
    private String nome;
    private int pontuacao;
    private int ranking;
    private int jogosParticipados;
    private int vitorias;
    private int empates;
    private int derrotas;

    public InfoJogador(int id, String nome, int pontuacao, int ranking, int jogosParticipados, int vitorias, int empates, int derrotas) {
        this.id = id;
        this.nome = nome;
        this.pontuacao = pontuacao;
        this.ranking = ranking;
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

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
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
