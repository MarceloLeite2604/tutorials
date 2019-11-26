package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.configuration;

public final class NomesBeans {
	
	public static final String SPRING_BATCH_BANCO_PROPERTIES = "springBatchBancoProperties";

	public static final String SPRING_BATCH_DATA_SOURCE = "springBatchDataSource";
	
	public static final String PROGRAMA_BANCO_PROPERTIES = "programaBancoProperties";

	public static final String PROGRAMA_DATA_SOURCE = "programaDataSource";

	public static final String PROGRAMA_ENTITY_MANAGER_FACTORY_BEAN = "programaEntityManagerFactoryBean";

	public static final String PROGRAMA_TRANSACTION_MANAGER = "programaTransactionManager";
	
	public static final String PREPARO_AMBIENTE_STEP = "preparoAmbienteStep";
	
	public static final String AQUISICAO_USUARIOS_STEP = "aquisicaoUsuariosStep";

	public static final String PERSISTENCIA_USUARIOS_MANAGER_STEP = "persistenciaUsuariosManagerStep";

	public static final String PERSISTENCIA_USUARIOS_WORKER_STEP = "persistenciaUsuariosWorkerStep";

	public static final String LIMPEZA_AMBIENTE_STEP = "limpezaAmbienteStep";

	public static final String CRIADOR_USUARIOS_JOB = "criadorUsuariosJob";

	public static final String TASK_EXECUTOR_PERSISTENCIA_USUARIOS = "taskExecutorPersistenciaUsuarios";

	public static final String SLED = "sled";

	public static final String FILE_UTIL = "fileUtil";

	public static final String REST_TEMPLATE = "restTemplate";	
	
	private NomesBeans() {
		// Construtor privado para evitar a criação de objetos deste tipo.
	}

}
