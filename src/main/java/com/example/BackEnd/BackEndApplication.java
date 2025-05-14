package com.example.BackEnd;

import com.example.BackEnd.Repository.BancoDeDados;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);

		BancoDeDados.Conectar();
	}

	// Finaliza a conexão com o banco de dados quando o programa for finalizado
	@PreDestroy
	public void finalizarPrograma() {
		BancoDeDados.Desconectar();
	}
}