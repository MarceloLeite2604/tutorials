package org.marceloleite.tutorials.spring.hsqldb;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.hsqldb.dao.ClienteDAO;
import org.marceloleite.tutorials.spring.hsqldb.model.Cliente;
import org.marceloleite.tutorials.spring.hsqldb.util.BancoDadosUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	private static final long TOTAL_REGISTROS_INSERIDOS = 500000;
	private static final long TOTAL_REGISTROS_SALVOS = 1000;

	private static final double PROBABILIDADE = (double) TOTAL_REGISTROS_SALVOS / (double) TOTAL_REGISTROS_INSERIDOS;

	@Inject
	private ClienteDAO clienteDAO;

	@Inject
	private BancoDadosUtil bancoDadosUtil;

	private Random random;

	public Main() throws NoSuchAlgorithmException {
		random = new Random();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
		return args -> {

			List<Cliente> clientes = new LinkedList<>();
			List<Cliente> clientesSelecionados = new LinkedList<>();

			for (int contador = 1; contador <= TOTAL_REGISTROS_INSERIDOS; contador++) {
				Cliente cliente = criarCliente();
				
				if (selecionarCliente()) {
					clientesSelecionados.add(cliente);
				}

				clientes.add(cliente);
				if (!clientes.isEmpty() && clientes.size() >= 100000) {
					clienteDAO.inserir(clientes);
					LOGGER.info("{} registros inseridos.", contador);
					clientes.clear();
				}
			}

			LOGGER.info("Total de registros na tabela: {}", clienteDAO.contarClientes());
			LOGGER.info("Total de clientes selecionados: {}", clientesSelecionados.size());

			verificarClientes(clientesSelecionados);

		};
	}

	private void verificarClientes(List<Cliente> clientesSelecionados) {
		LOGGER.info("Verificando clientes do banco.");
		long clientesVerificadosCorretamente = 0;
		for (Cliente clienteSelecionado : clientesSelecionados) {
			Cliente clienteDoBanco = clienteDAO.obterPorId(clienteSelecionado.getId());
			if (!clienteSelecionado.equals(clienteDoBanco)) {
				LOGGER.error("Cliente {} é diferente do cliente {}", clienteSelecionado, clienteDoBanco);
			} else {
				clientesVerificadosCorretamente++;
			}
		}
		LOGGER.info("Verificação concluída. Total de clientes corretos: {}", clientesVerificadosCorretamente);
	}

	private Cliente criarCliente() {
		return Cliente.builder()
				.id(UUID.randomUUID())
				.valor(random.nextLong())
				.build();
	}

	private boolean selecionarCliente() {
		return random.nextDouble() <= PROBABILIDADE;
	}

}
