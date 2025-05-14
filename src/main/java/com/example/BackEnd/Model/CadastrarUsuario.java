package com.example.BackEnd.Model;

import org.springframework.web.multipart.MultipartFile;

public class CadastrarUsuario {
    private String nome;
    private String email;
    private String senha;
    private MultipartFile imagem;

    public CadastrarUsuario(String nome, String email, String senha, MultipartFile imagem) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public MultipartFile getImagem() {
        return imagem;
    }

    public void setImagem(MultipartFile imagem) {
        this.imagem = imagem;
    }
}
