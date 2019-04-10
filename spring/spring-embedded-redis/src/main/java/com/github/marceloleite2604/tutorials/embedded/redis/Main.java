package com.github.marceloleite2604.tutorials.embedded.redis;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import redis.clients.jedis.Jedis;

@SpringBootApplication
@EnableAutoConfiguration(
		exclude = { DataSourceAutoConfiguration.class,
				DataSourceTransactionManagerAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class })
public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	private static final long TOTAL_REGISTROS_INSERIDOS = 10000000;
	private static final long TOTAL_REGISTROS_SALVOS = 10000;

	private static final double PROBABILIDADE = (double) TOTAL_REGISTROS_SALVOS
			/ (double) TOTAL_REGISTROS_INSERIDOS;

	private Random random;

	public Main() throws NoSuchAlgorithmException {
		random = new Random();
	}

	public static void main(String[] args) throws Exception {
		System.exit(SpringApplication.exit(SpringApplication.run(Main.class, args)));
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
		return args -> {
			try (Jedis jedis = new Jedis()) {
				UUID uuid = null;
				String valor;
				Map<UUID, String> clientesSelecionados = new HashMap<>();

				for (int contador = 1; contador <= TOTAL_REGISTROS_INSERIDOS; contador++) {
					uuid = UUID.randomUUID();
					valor = Long.toString(random.nextLong());
					if (selecionarCliente()) {
						clientesSelecionados.put(uuid, valor);
					}

					jedis.set(uuid.toString(), valor);
					if (contador != 0 && contador % 100000 == 0) {
						LOGGER.info("{} registros inseridos.", contador);
					}
				}

				LOGGER.info("Verificando dados.");

				clientesSelecionados.forEach((key, value) -> {
					String valorRedis = jedis.get(key.toString());
					if (!value.equals(valorRedis)) {
						LOGGER.error("Valor para o uuid {} não é igual: {} != {} ", key, value,
								valorRedis);
					}
				});
				LOGGER.info("Verificação de dados concluída.");
			}
		};
	}

	private boolean selecionarCliente() {
		return random.nextDouble() <= PROBABILIDADE;
	}

}
