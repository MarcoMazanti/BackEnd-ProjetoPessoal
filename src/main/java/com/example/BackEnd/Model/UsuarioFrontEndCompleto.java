package com.example.BackEnd.Model;

import com.example.BackEnd.Util.ByteArrayMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class UsuarioFrontEndCompleto {
    private int id;
    private String nome;
    private String email;
    private MultipartFile imagem;

    public UsuarioFrontEndCompleto(String nome, String email, byte[] imagem) {
        this.nome = nome;
        this.email = email;
        this.imagem = new ByteArrayMultipartFile(imagem, "imagem", "imagem.jpg", "image/jpeg");
    }

    public UsuarioFrontEndCompleto(int id, String nome, String email, byte[] imagem) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.imagem = new ByteArrayMultipartFile(imagem, "imagem", "imagem.jpg", "image/jpeg");
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

    public MultipartFile getImagem() {
        return imagem;
    }

    public void setImagem(MultipartFile imagem) {
        this.imagem = imagem;
    }
}
