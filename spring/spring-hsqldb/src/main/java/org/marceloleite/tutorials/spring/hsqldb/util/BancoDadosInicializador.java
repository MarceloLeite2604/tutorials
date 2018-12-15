package org.marceloleite.tutorials.spring.hsqldb.util;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.marceloleite.tutorials.spring.hsqldb.model.Script;
import org.springframework.stereotype.Component;

@Component
public class BancoDadosInicializador {

	@Inject
	private BancoDadosUtil bancoDadosUtil;

	@PostConstruct
	public void postConstruct() {
		inicializar();
	}
	
	private void inicializar() {
		bancoDadosUtil.excluirBancoDados();
		bancoDadosUtil.executarScript(Script.CRIAR_TABELA_CLIENTES);
	}

}
