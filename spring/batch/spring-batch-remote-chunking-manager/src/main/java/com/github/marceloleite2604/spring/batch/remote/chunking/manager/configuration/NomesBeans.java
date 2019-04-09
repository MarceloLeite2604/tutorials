package com.github.marceloleite2604.spring.batch.remote.chunking.manager.configuration;

public final class NomesBeans {

	public static final String ACTIVE_MQ_CONNECTION_FACTORY = "activeMQConnectionFactory";

	public static final String STEP_ENVIO_DADOS = "stepEnvioDados";

	public static final String JOB_REMOTE_CHUNKING = "jobRemoteChunking";

	public static final String INTEGRATION_FLOW_SAIDA_REQUISICOES = "integrationFlowSaidaRequisicoes";

	public static final String INTEGRATION_FLOW_ENTRADA_RESPOSTAS = "integrationFlowEntradaRespostas";

	public static final String MESSAGE_CHANNEL_REQUESTS = "messageChannelRequests";

	public static final String POLLABLE_CHANNEL_RESPONSES = "pollableChannelResponses";

	private NomesBeans() {
		// Construtor privado para evitar a criação de objetos desta classe.
	}

}
