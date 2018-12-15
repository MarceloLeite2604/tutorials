package org.marceloleite.tutorials.spring.hsqldb.model;

import org.marceloleite.tutorials.spring.hsqldb.util.BancoDadosUtil;

public enum Script {
	
	CRIAR_TABELA_CLIENTES(BancoDadosUtil.CAMINHO_DIRETORIO_SCRIPTS + "criar_tabela_clientes.sql"),
	INSERT_CLIENTE(BancoDadosUtil.CAMINHO_DIRETORIO_SCRIPTS + "insert_cliente.sql"),
	SELECT_CLIENTE(BancoDadosUtil.CAMINHO_DIRETORIO_SCRIPTS + "select_cliente.sql"),
	COUNT_CLIENTES(BancoDadosUtil.CAMINHO_DIRETORIO_SCRIPTS + "count_clientes.sql");

	private String caminhoArquivo;

	private Script(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}
}
