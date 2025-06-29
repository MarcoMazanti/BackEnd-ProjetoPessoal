package com.example.BackEnd.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Jogo {
    private int id;
    private int id_jogador;
    private int[][] array;
    private Timestamp data;
    private int pontuacao;
    private boolean ehFim;

    public Jogo(int id, int id_jogador, int[][] array, Timestamp data, int pontuacao, int ehFim) {
        this.id = id;
        this.id_jogador = id_jogador;
        this.array = array;
        this.data = data;
        this.pontuacao = pontuacao;
        this.ehFim = ehFim == 1;
    }

    public Jogo(int id_jogador, int[][] array) {
        this.id_jogador = id_jogador;
        this.array = array;
        data = Timestamp.valueOf(LocalDateTime.now());
        pontuacao = 0;
        ehFim = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_jogador() {
        return id_jogador;
    }

    public void setId_jogador(int id_jogador) {
        this.id_jogador = id_jogador;
    }

    public int[][] getArray() {
        return array;
    }

    public void setArray(int[][] array) {
        this.array = array;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public boolean isEhFim() {
        return ehFim;
    }

    public void setEhFim(boolean ehFim) {
        this.ehFim = ehFim;
    }

    public static String arrayToString(int[][] array) {

        return Arrays.deepToString(array);
    }
}
