package org.marceloleite.tutorials.spring.hsqldb.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.marceloleite.tutorials.spring.hsqldb.model.Script;
import org.springframework.stereotype.Component;

@Component
public class BancoDadosUtil {

	public static final int TOTAL_BANCO_DADOS = 1;

	public static final int MAXIMO_BATCHES = 1000;

	public static final String CAMINHO_DIRETORIO_SCRIPTS = "src/main/resources/scripts/";

	private static final String CAMINHO_DIRETORIO_BANCO_DADOS = "hsqldb";

	private static final Map<Script, String> scripts = new EnumMap<>(Script.class);

	@Named("dataSources")
	@Inject
	private List<DataSource> dataSources;

	public boolean executarScript(Script script) {
		String sql = obterScript(script);
		for (DataSource dataSource : dataSources) {
			try (Connection connection = dataSource.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.execute();
			} catch (SQLException excecao) {
				throw new RuntimeException(
						"Erro ao executar o script do arquivo \"" + script.getCaminhoArquivo() + "\".", excecao);
			}
		}
		return true;
	}

	public String obterScript(Script script) {
		try {
			if (!scripts.containsKey(script)) {
				scripts.put(script, FileUtils.readFileToString(new File(script.getCaminhoArquivo()), "UTF-8"));
			}
			return scripts.get(script);
		} catch (IOException excecao) {
			throw new RuntimeException("Erro ao ler o conteúdo do arquivo \"" + script.getCaminhoArquivo() + "\".",
					excecao);
		}
	}

	public void excluirBancoDados() {
		for (int contador = 0; contador < TOTAL_BANCO_DADOS; contador++) {
			try {
				FileUtils.deleteDirectory(new File(CAMINHO_DIRETORIO_BANCO_DADOS));
			} catch (IOException excecao) {
				throw new RuntimeException(
						"Erro ao excluir o diretório do banco de dados \"" + CAMINHO_DIRETORIO_BANCO_DADOS + "\".",
						excecao);
			}
		}

	}

}
