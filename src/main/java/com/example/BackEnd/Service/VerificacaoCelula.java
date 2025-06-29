package com.example.BackEnd.Service;

public class VerificacaoCelula {
    public static int verificacaoCelula(int[][] mapa, int altura, int largura) {
        if (mapa[altura][largura] == 0) {
            int somaCelula = 0;
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    int ni = altura + i;
                    int nj = largura + j;

                    if (ni >= 0 && ni < mapa.length) {
                        if (nj >= 0 && nj < mapa[ni].length) {
                            if (mapa[ni][nj] == 1) {
                                somaCelula++;
                            }
                        }
                    }
                }
            }

            return somaCelula;
        } else {
            return 10;
        }
    }
}
