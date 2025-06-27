package com.example.BackEnd;

import com.example.BackEnd.Model.InfoJogador;
import com.example.BackEnd.Model.UsuarioBackEnd;
import com.example.BackEnd.Repository.BancoDeDados;
import com.example.BackEnd.Repository.TabelaUsuario;
import com.example.BackEnd.Repository.TabelaInfoJogador;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BackEndApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(BackEndApplication.class, args);

		BancoDeDados.Conectar();

		List<UsuarioBackEnd> listaUsuarios = TabelaUsuario.getUsuarios();
		List<InfoJogador> listaInfoJogadores = TabelaInfoJogador.getInfoJogador();
		for (UsuarioBackEnd usuario : listaUsuarios) {
			int verificarInfo = 0;
			for (InfoJogador infoJogador : listaInfoJogadores) {
				if (infoJogador.getId() == usuario.getId()) {
					verificarInfo = 1;
					break;
				}
			}

			if (verificarInfo == 0) {
				TabelaInfoJogador.postInfoJogador(usuario.getId(), usuario.getNome());
			}
		}
	}

	// Finaliza a conexão com o banco de dados quando o programa for finalizado
	@PreDestroy
	public void finalizarPrograma() {
		BancoDeDados.Desconectar();
	}
}