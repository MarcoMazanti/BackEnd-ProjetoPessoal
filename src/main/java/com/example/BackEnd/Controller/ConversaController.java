package com.example.BackEnd.Controller;

import com.example.BackEnd.Model.Conversa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.BackEnd.Repository.TabelaConversa.*;

@RestController
@RequestMapping("/api/conversa")
public class ConversaController {
    @GetMapping("/{idAmizade}")
    public ResponseEntity<?> getConversa(@PathVariable int idAmizade) {
        try {
            return ResponseEntity.ok(getConversaID(idAmizade));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> postNovaMensagem(@RequestBody Conversa conversa) {
        try {
            return ResponseEntity.ok(postMensagem(conversa));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteConversaJogador(@RequestBody int id_amizade) {
        try {
            return ResponseEntity.ok(deleteConversa(id_amizade));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
