package com.example.BackEnd.Controller;

import com.example.BackEnd.Model.Amizade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.BackEnd.Repository.TabelaAmizade.*;

@RestController
@RequestMapping("/api/amizade")
public class AmizadeController {
    @GetMapping("/todos")
    public ResponseEntity<?> getTodosAmizadesPendentes() {
        try {
            return ResponseEntity.ok(getTodasAmizades());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/todos/pendentes")
    public ResponseEntity<?> getAllAmizadePendentes() {
        try {
            return ResponseEntity.ok(getTodasAmizadesPendentes());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/todos/feitas")
    public ResponseEntity<?> getAllAmizadeFeitas() {
        try {
            return ResponseEntity.ok(getTodasAmizadesFeitas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/pendente/{id}")
    public ResponseEntity<?> getAllAmizadePendenteJogador(@PathVariable int id) {
        try {
            return ResponseEntity.ok(getAmizadePendenteJogador(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/feitas/{id}")
    public ResponseEntity<?> getAllAmizadeFeitaJogador(@PathVariable int id) {
        try {
            return ResponseEntity.ok(getAmizadeFeitaJogador(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/criarAmizade")
    public ResponseEntity<?> postNovaAmizadePendente(@RequestBody Amizade amizadePendente) {
        try {
            return ResponseEntity.ok(postAmizadePendente(amizadePendente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/atualizarAmizade")
    public ResponseEntity<?> atualizarAmizade(@RequestBody Amizade amizadePendente) {
        try {
            return ResponseEntity.ok(putAmizadeFeita(amizadePendente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletarAmizade")
    public ResponseEntity<?> deletarAmizade(@RequestBody Amizade amizadePendente) {
        try {
            return ResponseEntity.ok(deleteAmizadeFeita(amizadePendente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
