package com.example.BackEnd.Model;

public class DadosMapa {
    private int altura;
    private int largura;
    private int quantBombas;

    public DadosMapa(int altura, int largura, int quantBombas) {
        this.altura = altura;
        this.largura = largura;
        this.quantBombas = quantBombas;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getQuantBombas() {
        return quantBombas;
    }

    public void setQuantBombas(int quantBombas) {
        this.quantBombas = quantBombas;
    }
}
