package com.example.BackEnd.Controller;

import com.example.BackEnd.Model.UsuarioBackEnd;
import com.example.BackEnd.Repository.ManipularDados;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.BackEnd.Model.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:63342")
public class UsuarioController {
    @GetMapping("/{email}")
    public ResponseEntity<?> getUsuario(@PathVariable("email") String email){
        try {
            UsuarioBackEnd usuarioRequerido = ManipularDados.getUsuario(email);

            if (usuarioRequerido == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            } else {
                return ResponseEntity.ok(usuarioRequerido);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> postLogin(@RequestBody VerificacaoBasica usuario){
        try {
            UsuarioBackEnd usuarioRequerido = ManipularDados.getUsuario(usuario.getEmail());

            if (usuario.getEmail().equals(usuarioRequerido.getEmail()) && usuario.getSenha().equals(usuarioRequerido.getSenha())) {
                return ResponseEntity.ok(new UsuarioFrontEndCompleto(
                        usuarioRequerido.getId(),
                        usuarioRequerido.getNome(),
                        usuarioRequerido.getEmail(),
                        usuarioRequerido.getImagem()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postUsuario(
            @RequestParam("nome") String nome,
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            @RequestPart("imagem") MultipartFile imagem
    ) {
        CadastrarUsuario cadastrarUsuario = new CadastrarUsuario(nome, email, senha, imagem);

        try {
            byte[] imagemByte = cadastrarUsuario.getImagem().getBytes();

            UsuarioBackEnd usuario = new UsuarioBackEnd(
                    cadastrarUsuario.getNome(),
                    cadastrarUsuario.getEmail(),
                    cadastrarUsuario.getSenha(), imagemByte);

            return ResponseEntity.ok(ManipularDados.postUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}