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

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @GetMapping("/{email}")
    public ResponseEntity<?> getUsuario(@PathVariable("email") String email){
        try {
            UsuarioBackEnd usuarioRequerido = TabelaUsuario.getUsuarioUnico(email);

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

    @PostMapping("/login")
    public ResponseEntity<?> postLogin(@RequestBody VerificacaoBasica usuario){
        try {
            UsuarioBackEnd usuarioBackEnd = TabelaUsuario.getUsuarioUnico(usuario.getEmail());

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
}