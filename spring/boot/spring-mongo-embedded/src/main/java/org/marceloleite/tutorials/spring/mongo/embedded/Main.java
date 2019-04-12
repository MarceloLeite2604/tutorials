package org.marceloleite.tutorials.spring.mongo.embedded;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.mongo.embedded.dao.repository.ClienteRepository;
import org.marceloleite.tutorials.spring.mongo.embedded.model.Cliente;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

	private static final int TOTAL_DE_REGISTROS = 10000000;
	
	private static final int TAMANHO_BULK = 15000;
	
	private static final int REGISTROS_SELECIONADOS = 10000;

	private Random random;

	@Inject
	private ClienteRepository clienteRepository;

	public Main() {
		random = new Random();
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		clienteRepository.deleteAll();

		List<Cliente> clientesSelecionados = inserirRegistros();

		analisarRegistrosSelecionados(clientesSelecionados);
	}

	private void analisarRegistrosSelecionados(List<Cliente> clientesSelecionados) {
		List<Cliente> clientesDoBanco = new ArrayList<>();

		long inicioAnalise = System.currentTimeMillis();
		int clientesCorretos = 0;

		for (Cliente clienteSelecionado : clientesSelecionados) {
			Cliente clienteDoBanco = clienteRepository.findById(clienteSelecionado.getId())
					.orElse(null);

			if (clienteDoBanco != null) {
				clientesDoBanco.add(clienteDoBanco);
				if (clienteDoBanco.getValor() == clienteSelecionado.getValor()) {
					clientesCorretos++;
				}
			}

		}

		long tempoAnalise = System.currentTimeMillis() - inicioAnalise;
		BigDecimal tempoMedioAnalise = BigDecimal.valueOf(tempoAnalise)
				.divide(BigDecimal.valueOf(TOTAL_DE_REGISTROS), 4, RoundingMode.HALF_EVEN);		

		System.out.println("Verificação concluída.");
		System.out.println("Tempo total de verificação: " + tempoAnalise);
		System.out.println("Clientes encontrados: " + clientesDoBanco.size() + " de "
				+ clientesSelecionados.size() + ".");
		System.out.println(
				"Clientes corretos: " + clientesCorretos + " de " + clientesDoBanco.size() + ".");
		System.out.println("Tempo médio de busca e análise de cliente de cliente: " + tempoMedioAnalise + " milissegundos.");
	}

	private List<Cliente> inserirRegistros() {
		List<Cliente> clientes = new LinkedList<>();
		List<Cliente> clientesSelecionados = new LinkedList<>();
		long inicioInclusao = System.currentTimeMillis();
		long lapInclusao = inicioInclusao;
		for (int contador = 1; contador <= TOTAL_DE_REGISTROS; contador++) {
			Cliente cliente = Cliente.builder()
					.withId(UUID.randomUUID())
					.withValor(random.nextLong())
					.build();

			if (selecionarCliente()) {
				clientesSelecionados.add(cliente);
			}

			clientes.add(cliente);
			if (clientes.size() >= TAMANHO_BULK) {
				clienteRepository.insert(clientes);
				clientes.clear();
				System.out.println(TAMANHO_BULK + " clientes inseridos em "
						+ (System.currentTimeMillis() - lapInclusao) + " milissegundos. " + contador
						+ " registros no total.");
				lapInclusao = System.currentTimeMillis();
			}
		}
		clienteRepository.insert(clientes);
		long tempoInclusao = System.currentTimeMillis() - inicioInclusao;
		BigDecimal tempoMedioInclusao = BigDecimal.valueOf(tempoInclusao)
				.divide(BigDecimal.valueOf(TOTAL_DE_REGISTROS), 4, RoundingMode.HALF_EVEN);
		clientes.clear();
		System.out.println(TOTAL_DE_REGISTROS + " registros inseridos em " + tempoInclusao
				+ " milissegundos. Média de inclusão em " + tempoMedioInclusao + " milissegundos.");
		System.out.println("Verificando clientes.");
		return clientesSelecionados;
	}

	private boolean selecionarCliente() {
		return random
				.nextDouble() < ((double) REGISTROS_SELECIONADOS / (double) TOTAL_DE_REGISTROS);
	}

}