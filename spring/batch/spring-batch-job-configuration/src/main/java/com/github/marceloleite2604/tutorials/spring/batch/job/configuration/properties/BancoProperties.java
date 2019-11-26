package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties;

import java.util.Map;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class BancoProperties {

	/**
	 * Nome da classe utilizada para comunicação com o banco de dados.
	 */
	@NotBlank
	private String driverClassName;
	
	/**
	 * URL Completa para conexão com o banco de dados.
	 */
	private String url;
	
	/**
	 * Protocolo utilizado para comunicação com o banco de dados.
	 */
	private String scheme;
	
	/**
	 * Nome do servidor (ou endereço IP) do servidor de banco de dados.
	 */
	private String host;
	
	/**
	 * Porta do servidor utilizada para comunicação com o banco de dados.
	 */
	private Integer port;
	
	/**
	 * Caminho no servidor para acesso ao banco de dados.
	 */
	private String path;
	
	/**
	 * Propriedades adicionais a serem incluídas na comunicação com o banco de dados.
	 */
	private Map<String, String> outrasPropriedades;
	
	/**
	 * Nome do usuário para acesso ao banco de dados.
	 */
	@NotBlank
	private String usuario;
	
	/**
	 * Senha criptografada do usuário utilizado no acesso ao banco de dados.
	 */
	@NotBlank
	private String senhaCriptografada;
	
	/**
	 * Máximo de conexões em aguardo com o banco.
	 */
	@Min(1)
	private Integer maximoConexoesAguardo;
	
	/**
	 * Total de conexões com o banco.
	 */
	@Min(1)
	private Integer totalDeConexoes;

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map<String, String> getOutrasPropriedades() {
		return outrasPropriedades;
	}

	public void setOutrasPropriedades(Map<String, String> outrasPropriedades) {
		this.outrasPropriedades = outrasPropriedades;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenhaCriptografada() {
		return senhaCriptografada;
	}

	public void setSenhaCriptografada(String senhaCriptografada) {
		this.senhaCriptografada = senhaCriptografada;
	}

	public Integer getMaximoConexoesAguardo() {
		return maximoConexoesAguardo;
	}

	public void setMaximoConexoesAguardo(Integer maximoConexoesAguardo) {
		this.maximoConexoesAguardo = maximoConexoesAguardo;
	}

	public Integer getTotalDeConexoes() {
		return totalDeConexoes;
	}

	public void setTotalDeConexoes(Integer totalDeConexoes) {
		this.totalDeConexoes = totalDeConexoes;
	}
}
