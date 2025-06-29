package com.example.BackEnd.Controller;

import com.example.BackEnd.Model.DadosMapa;
import com.example.BackEnd.Model.Jogo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static com.example.BackEnd.Repository.TabelaJogo.*;
import static com.example.BackEnd.Repository.TabelaJogo.getJogoAndamento;
import static com.example.BackEnd.Service.CriacaoMapa.*;
import static com.example.BackEnd.Service.VerificacaoCelula.verificacaoCelula;

@RestController
@RequestMapping("/api/jogo")
public class JogoController {
    @GetMapping("/todos_jogos/{id}")
    public ResponseEntity<?> getAllJogosJogador(@PathVariable int id) {
        try {
            return ResponseEntity.ok(getJogosJogador(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/ultimo_jogo/{id}")
    public ResponseEntity<?> getLastJogoJogador(@PathVariable int id) {
        try {
            return ResponseEntity.ok(getJogoAndamento(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/partida/{id}")
    public ResponseEntity<?> getDadosPartida(@PathVariable int id) {
        try {
            Jogo jogo = getJogoAndamento(id);

            int quantBombas = contarBombas(jogo.getArray());
            int[] tamanhoMapa = contarTamanhoMapa(jogo.getArray());

            DadosMapa dadosMapa = new DadosMapa(tamanhoMapa[0],  tamanhoMapa[1], quantBombas);


            return ResponseEntity.ok(dadosMapa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/partida/celula/{id}/{altura}/{largura}")
    public ResponseEntity<?> getCondicaoCelula(@PathVariable int id, @PathVariable int altura, @PathVariable int largura) {
        try {
            Jogo jogo = getJogoAndamento(id);

            return ResponseEntity.ok(verificacaoCelula(jogo.getArray(), altura, largura));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{id}/{altura}/{largura}/{dificuldade}")
    public ResponseEntity<?> postJogo(
            @PathVariable int id,
            @PathVariable int altura,
            @PathVariable int largura,
            @PathVariable int dificuldade) {
        try {
            if (getJogoAndamento(id) == null) {
                int[][] mapa = gerarMapa(altura, largura, dificuldade);

                Jogo jogo = new Jogo(id, mapa);
                
                return ResponseEntity.ok(postNovoJogo(jogo));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O Jogador já está em um jogo!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("{id}/{pontuacao}")
    public ResponseEntity<?> putJogo(@PathVariable int id, @PathVariable int pontuacao) {
        try {
            if (getJogoAndamento(id) == null) {
                return ResponseEntity.ok(updateJogo(id, pontuacao));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
