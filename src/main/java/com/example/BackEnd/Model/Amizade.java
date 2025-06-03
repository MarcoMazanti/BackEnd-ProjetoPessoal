package com.example.BackEnd.Model;

public class Amizade {
    private int id_jogador_1;
    private int id_jogador_2;
    private boolean amizade_pendente;

    public Amizade(int id_jogador_1, int id_jogador_2, int amizade_pendente) {
        this.id_jogador_1 = id_jogador_1;
        this.id_jogador_2 = id_jogador_2;
        this.amizade_pendente = amizade_pendente == 1;
    }

    public Amizade(int id_jogador_1, int id_jogador_2) {
        this.id_jogador_1 = id_jogador_1;
        this.id_jogador_2 = id_jogador_2;
        this.amizade_pendente = true;
    }

    public Amizade() {
        this.amizade_pendente = true;
    }

    public int getId_jogador_1() {
        return id_jogador_1;
    }

    public void setId_jogador_1(int id_jogador_1) {
        this.id_jogador_1 = id_jogador_1;
    }

    public int getId_jogador_2() {
        return id_jogador_2;
    }

    public void setId_jogador_2(int id_jogador_2) {
        this.id_jogador_2 = id_jogador_2;
    }

    public boolean isAmizade_pendente() {
        return amizade_pendente;
    }

    public void setAmizade_pendente(boolean amizade_pendente) {
        this.amizade_pendente = amizade_pendente;
    }
}

