package com.example.BackEnd.Model;

public class Conversa {
    private int idAmizade;
    private int idJogador;
    private String mensagem;

    public Conversa(int idAmizade, int idJogador, String mensagem) {
        this.idAmizade = idAmizade;
        this.idJogador = idJogador;
        this.mensagem = mensagem;
    }

    public int getIdAmizade() {
        return idAmizade;
    }

    public void setIdAmizade(int idAmizade) {
        this.idAmizade = idAmizade;
    }

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
