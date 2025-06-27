package com.example.BackEnd.Controller;

import com.example.BackEnd.Model.RankingJogador;
import com.example.BackEnd.Repository.TabelaInfoJogador;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {
    @GetMapping("/jogadores")
    public ResponseEntity<?> getRankingJogadores() {
        try {
            List<RankingJogador> ranking = TabelaInfoJogador.getRanking();

            return ResponseEntity.ok(ranking);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/jogador/{id}")
    public ResponseEntity<?> getRankingJogador(@PathVariable("id") int id) {
        try {
            RankingJogador rankingJogador = TabelaInfoJogador.getRankingJogador(id);

            if (rankingJogador != null) {
                return ResponseEntity.ok(rankingJogador);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
