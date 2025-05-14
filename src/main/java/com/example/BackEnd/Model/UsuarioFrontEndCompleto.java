package com.example.BackEnd.Model;

import com.example.BackEnd.Util.ByteArrayMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

public class UsuarioFrontEndCompleto {
    private int id;
    private String nome;
    private String email;
    private String imagem;

    public UsuarioFrontEndCompleto(int id, String nome, String email, byte[] imagem) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        if (imagem != null && imagem.length > 0) {
            this.imagem = Base64.getEncoder().encodeToString(imagem);
        } else {
            this.imagem = null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
