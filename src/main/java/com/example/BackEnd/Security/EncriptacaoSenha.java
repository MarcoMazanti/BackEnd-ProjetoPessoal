package com.example.BackEnd.Security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncriptacaoSenha {
    public static String encriptarSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public static boolean validarSenha(String senhaFront, String senhaBancoDeDados) {
        return BCrypt.checkpw(senhaFront, senhaBancoDeDados);
    }
}
