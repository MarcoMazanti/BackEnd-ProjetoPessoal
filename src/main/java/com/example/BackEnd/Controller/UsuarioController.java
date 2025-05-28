package com.example.BackEnd.Controller;

import com.example.BackEnd.Model.UsuarioBackEnd;
import com.example.BackEnd.Repository.TabelaUsuario;
import com.example.BackEnd.Repository.TabelaInfoJogador;
import com.example.BackEnd.Security.EncriptacaoSenha;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.BackEnd.Model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @GetMapping("/{email}")
    public ResponseEntity<?> getUsuario(@PathVariable("email") String email){
        try {
            UsuarioBackEnd usuarioRequerido = TabelaUsuario.getUsuarioUnicoEmail(email);

            if (usuarioRequerido == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            } else {
                InfoJogador infoJogador = TabelaInfoJogador.getInfoJogadorUnico(usuarioRequerido.getId());

                return ResponseEntity.ok(new UsuarioFrontEndCompleto(
                        usuarioRequerido.getId(),
                        usuarioRequerido.getNome(),
                        usuarioRequerido.getEmail(),
                        usuarioRequerido.getImagem(),
                        infoJogador.getPontuacao(),
                        infoJogador.getJogosParticipados(),
                        infoJogador.getVitorias(),
                        infoJogador.getEmpates(),
                        infoJogador.getDerrotas()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?> getUsuarios(){
        try {
            List<UsuarioBackEnd> usuarios = TabelaUsuario.getUsuarios();
            List<UsuarioFrontEndParcial> usuariosFrontEndParcial = new ArrayList<>();

            for (UsuarioBackEnd usuario : usuarios) {
                UsuarioFrontEndParcial usuarioFrontEndParcialAux = new UsuarioFrontEndParcial(usuario.getId(), usuario.getNome(), usuario.getEmail());
                usuariosFrontEndParcial.add(usuarioFrontEndParcialAux);
            }

            return ResponseEntity.ok(usuariosFrontEndParcial);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity<?> getUSuarioFoto(@PathVariable("id") int id) {
        try {
            UsuarioBackEnd usuarioFoto = TabelaUsuario.getUsuarioUnicoID(id);

            if (usuarioFoto == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            } else {
                return ResponseEntity.ok(usuarioFoto.getImagem());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> postLogin(@RequestBody VerificacaoBasica usuario){
        try {
            UsuarioBackEnd usuarioBackEnd = TabelaUsuario.getUsuarioUnicoEmail(usuario.getEmail());

            if (usuario.getEmail().equals(usuarioBackEnd.getEmail()) &&
                    EncriptacaoSenha.validarSenha(usuario.getSenha(), usuarioBackEnd.getSenha())) {

                InfoJogador infoJogador = TabelaInfoJogador.getInfoJogadorUnico(usuarioBackEnd.getId());

                UsuarioFrontEndCompleto usuarioFrontEndCompleto = new UsuarioFrontEndCompleto(
                        usuarioBackEnd.getId(),
                        usuarioBackEnd.getNome(),
                        usuarioBackEnd.getEmail(),
                        usuarioBackEnd.getImagem(),
                        infoJogador.getPontuacao(),
                        infoJogador.getJogosParticipados(),
                        infoJogador.getVitorias(),
                        infoJogador.getEmpates(),
                        infoJogador.getDerrotas());

                return ResponseEntity.ok(usuarioFrontEndCompleto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao realizar login");
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postUsuario(
            @RequestParam("nome") String nome,
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            @RequestPart("imagem") MultipartFile imagem
    ) {
        senha = EncriptacaoSenha.encriptarSenha(senha);

        CadastrarUsuario cadastrarUsuario = new CadastrarUsuario(nome, email, senha, imagem);

        try {
            byte[] imagemByte = cadastrarUsuario.getImagem().getBytes();

            UsuarioBackEnd usuario = new UsuarioBackEnd(
                    cadastrarUsuario.getNome(),
                    cadastrarUsuario.getEmail(),
                    cadastrarUsuario.getSenha(),
                    imagemByte);

            return ResponseEntity.ok(TabelaUsuario.postUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/verificarInexistenciaEmail/{id}")
    public ResponseEntity<?> postUsuario(@PathVariable int id, @RequestParam("email") String email) {
        try {
            List<UsuarioBackEnd> usuarios = TabelaUsuario.getUsuarios();

            for (UsuarioBackEnd usuario : usuarios) {
                if (usuario.getEmail().equals(email) && usuario.getId() != id) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
                }
            }

            return ResponseEntity.ok("Email não cadastrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/verificarInexistenciaEmail")
    public ResponseEntity<?> postVerificarEmailUsuarioCadastro(@RequestPart("email") String email) {
        try {
            List<UsuarioBackEnd> usuarios = TabelaUsuario.getUsuarios();

            for (UsuarioBackEnd usuario : usuarios) {
                if (usuario.getEmail().equals(email)) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
                }
            }

            return ResponseEntity.ok("Email não cadastrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(value = "/atualizar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> putUsuario(
            @PathVariable int id,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem,
            @RequestPart("nome") String nome,
            @RequestPart("email") String email
    ) {
        try {
            AtualizarUsuario usuario = new AtualizarUsuario(imagem, nome, email);

            UsuarioBackEnd usuarioAhAtualizar = TabelaUsuario.getUsuarioUnicoID(id);
            byte[] enviarImg;
            String enviarNome;
            String enviarEmail;

            if (usuario.getImagem() != null) {
                enviarImg = usuario.getImagem();
            } else {
                enviarImg = usuarioAhAtualizar.getImagem();
            }

            if (usuario.getNome() != null) {
                enviarNome = usuario.getNome();
            } else {
                enviarNome = usuarioAhAtualizar.getNome();
            }

            if (usuario.getEmail() != null) {
                enviarEmail = usuario.getEmail();
            } else {
                enviarEmail = usuarioAhAtualizar.getEmail();
            }

            return ResponseEntity.ok(TabelaUsuario.putUsuario(id, enviarNome, enviarEmail, usuarioAhAtualizar.getSenha(), enviarImg));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable int id) {
        try {
            return ResponseEntity.ok(TabelaUsuario.deleteUsuario(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}