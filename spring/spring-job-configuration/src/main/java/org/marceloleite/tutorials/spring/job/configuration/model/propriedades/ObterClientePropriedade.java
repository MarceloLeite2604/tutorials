package org.marceloleite.tutorials.spring.job.configuration.model.propriedades;

public enum ObterClientePropriedade implements Propriedade {

	REGISTROS_LIDOS("registrosLidos", "0"),
	TOTAL_DE_REGISTROS("totalDeRegistros");
	
	private static final String PREFIXO_PROPRIEDADES = "obterCliente.";
	
	private ObterClientePropriedade(String nome, String valorPadrao) {
		this.nome = PREFIXO_PROPRIEDADES + nome;
		this.valorPadrao = valorPadrao;
	}
	
	private ObterClientePropriedade(String nome) {
		this(nome, null);
	}
	
	private String nome;
	
	private String valorPadrao;

	@Override
	public boolean isObrigatorio() {
		return (valorPadrao == null);
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public String getValorPadrao() {
		return valorPadrao;
	}
}
