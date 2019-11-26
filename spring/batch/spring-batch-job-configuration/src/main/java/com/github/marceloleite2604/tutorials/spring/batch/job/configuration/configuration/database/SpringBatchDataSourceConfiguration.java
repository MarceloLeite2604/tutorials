package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.configuration.database;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.configuration.NomesBeans;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.exception.SpringBatchJobConfigurationRuntimeException;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties.BancoProperties;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util.CriptografiaUtil;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Named;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SpringBatchDataSourceConfiguration {

	private static final String MENSAGEM_ERRO_ELABORACAO_URL_BANCO = "Não foi possível elaborar a URL de acesso ao banco.";

	@Bean(NomesBeans.SPRING_BATCH_DATA_SOURCE)
	@Primary
	public DataSource criarSpringBatchDataSource(
			@Named(NomesBeans.SPRING_BATCH_BANCO_PROPERTIES) BancoProperties springBatchBancoPropriedades,
			CriptografiaUtil criptografiaUtil) {
		return criarDataSource(springBatchBancoPropriedades, criptografiaUtil);
	}

	private DataSource criarDataSource(BancoProperties bancoPropriedades,
			CriptografiaUtil criptografiaUtil) {
		String usuario = bancoPropriedades.getUsuario();
		String senha = criptografiaUtil.decriptografar(bancoPropriedades.getSenhaCriptografada());

		String url = elaborarUrl(bancoPropriedades);

		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, usuario,
				senha);

		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
				connectionFactory, null);

		GenericObjectPool<PoolableConnection> genericObjectPool = new GenericObjectPool<>(
				poolableConnectionFactory);

		genericObjectPool.setMaxIdle(bancoPropriedades.getMaximoConexoesAguardo());
		genericObjectPool.setMaxTotal(bancoPropriedades.getTotalDeConexoes());

		poolableConnectionFactory.setPool(genericObjectPool);
		return new PoolingDataSource<>(genericObjectPool);
	}

	@Bean(NomesBeans.PROGRAMA_DATA_SOURCE)
	public DataSource criarProgramaDataSource(
			@Named(NomesBeans.PROGRAMA_BANCO_PROPERTIES) BancoProperties programaBancoPropriedades,
			CriptografiaUtil criptografiaUtil) {

		return criarDataSource(programaBancoPropriedades, criptografiaUtil);
	}

	private String elaborarUrl(BancoProperties bancoPropriedades) {
		
		if (!StringUtils.isBlank(bancoPropriedades.getUrl())) {
			return bancoPropriedades.getUrl();
		}
		
		try {
			// TODO Validar propriedades 
			return new URIBuilder().setScheme(bancoPropriedades.getScheme())
					.setHost(bancoPropriedades.getHost())
					.setPort(bancoPropriedades.getPort())
					.setPath(bancoPropriedades.getPath())
					.setParameters(elaborarParametros(
							bancoPropriedades.getOutrasPropriedades()))
					.build()
					.toASCIIString();
		} catch (URISyntaxException excecao) {
			throw new SpringBatchJobConfigurationRuntimeException(
					MENSAGEM_ERRO_ELABORACAO_URL_BANCO, excecao);
		}
	}

	private List<NameValuePair> elaborarParametros(Map<String, String> parametros) {
		return parametros.entrySet()
				.stream()
				.map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}

}
