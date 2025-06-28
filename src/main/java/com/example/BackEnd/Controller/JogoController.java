package com.example.BackEnd.Controller;

import com.example.BackEnd.Model.Jogo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.BackEnd.Repository.TabelaJogo.*;
import static com.example.BackEnd.Repository.TabelaJogo.getJogoAndamento;

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
            Jogo jogo = getJogoAndamento(id);

            if (jogo != null) {
                return ResponseEntity.ok(jogo);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> postJogo(@PathVariable int id) {
        try {
            if (getJogoAndamento(id) == null) {
                int[][] array = new int[10][10];
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        array[i][j] = 1;
                    }
                }
                
                Jogo jogo = new Jogo(id, array);
                
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
