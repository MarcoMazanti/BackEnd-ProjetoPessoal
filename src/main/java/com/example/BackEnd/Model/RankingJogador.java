package com.example.BackEnd.Model;

public class RankingJogador {
    private int id;
    private String nome;
    private int vitorias;
    private int pontuacao;
    private int ranking;

    public RankingJogador(int id, String nome, int vitorias, int pontuacao, int ranking) {
        this.id = id;
        this.nome = nome;
        this.vitorias = vitorias;
        this.pontuacao = pontuacao;
        this.ranking = ranking;
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

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
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
}
