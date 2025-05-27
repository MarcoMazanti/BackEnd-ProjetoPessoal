package com.example.BackEnd.Model;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class AtualizarUsuario {
    private byte[] imagem;
    private String nome;
    private String email;

    public AtualizarUsuario(MultipartFile imagem, String nome, String email) throws IOException {
        if (imagem != null) {
            this.imagem = imagem.getBytes();
        } else {
            this.imagem = null;
        }
        this.nome = nome;
        this.email = email;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
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
}
