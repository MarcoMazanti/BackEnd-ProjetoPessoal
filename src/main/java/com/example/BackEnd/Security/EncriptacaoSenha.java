package com.example.BackEnd.Security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncriptacaoSenha {
    public static String encriptarSenha(String senha){
        String hash = BCrypt.hashpw(senha, BCrypt.gensalt());

        return hash;
    }

    public static boolean validarSenha(String senhaFront, String senhaBancoDeDados){
        return BCrypt.checkpw(senhaFront, senhaBancoDeDados);
    }
}
