package org.marceloleite.tutorials.spring.batch.remote.chunking.configuration;

public final class NomesBeans {
	
	public static final String ACTIVE_MQ_CONNECTION_FACTORY = "activeMQConnectionFactory";
	
	public static final String STEP_ENVIO_DADOS = "stepEnvioDados";

	public static final String JOB_REMOTE_CHUNKING = "jobRemoteChunking";
	
	public static final String INTEGRATION_FLOW_ENTRADA_REQUISICOES = "integrationFlowEntradaRequisicoes";

	public static final String INTEGRATION_FLOW_SAIDA_RESPOSTAS = "integrationFlowSaidaRespostas";
	
	private NomesBeans() {
		// Construtor privado para evitar a criação de objetos desta classe.
	}

}
