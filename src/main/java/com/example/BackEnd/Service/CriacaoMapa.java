package com.example.BackEnd.Service;

import java.util.Random;

public class CriacaoMapa {
    public static int[][] gerarMapa(int altura, int largura, int dificuldade) {
        Random random = new Random();

        int[][] mapa = new int[altura][largura];

        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                mapa[i][j] = 0;
            }
        }

        int quantMina = (int) Math.round((altura * largura) * (dificuldade / 100.0));

        for (int i = 0; i < quantMina; i++) {
            int minaAltura =  random.nextInt(altura);
            int minaLargura =  random.nextInt(largura);

            mapa[minaAltura][minaLargura] = 1;
        }

        return mapa;
    }

    public static int contarBombas(int[][] mapa) {
        int quant = 0;

        for (int[] ints : mapa) {
            for (int anInt : ints) {
                if (anInt == 1) {
                    quant++;
                }
            }
        }

        return quant;
    }

    public static int[] contarTamanhoMapa(int[][] mapa) {
        int[] tamanho = new int[2]; // 0 - altura / 1 - largura

        tamanho[0] = mapa.length;
        tamanho[1] = mapa[0].length;

        return tamanho;
    }
}
