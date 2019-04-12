package com.github.marceloleite2604.tutorials.spring.security.database.properties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(CaminhosPropriedades.PROGRAMA_BANCO)
@Validated
public class ProgramaBancoProperties {

	/**
	 * Nome da classe utilizada como driver na conexão com o banco de dados.
	 */
	@NotBlank
	private String driverClassName;

	/**
	 * URL de acesso ao banco de dados.
	 */
	@NotBlank
	private String url;

	/**
	 * Nome de usuário utilizado para conexão com o banco de dados.
	 */
	@NotBlank
	private String usuario;

	/**
	 * Senha criptografada do usuário para conexão com o banco de dados.
	 */
	@NotBlank
	private String senha;
	
	/**
	 * Quantidade de conexões no pool a serem abertas com o banco de dados. 
	 */
	@Min(1L)
	private Integer quantidadeDeConexoes = 1;

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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getQuantidadeDeConexoes() {
		return quantidadeDeConexoes;
	}

	public void setQuantidadeDeConexoes(Integer quantidadeDeConexoes) {
		this.quantidadeDeConexoes = quantidadeDeConexoes;
	}
}
