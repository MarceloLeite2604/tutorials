package org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes;

import java.util.concurrent.atomic.AtomicLong;

import org.marceloleite.tutorials.spring.job.configuration.model.propriedades.ObterClientePropriedade;
import org.marceloleite.tutorials.spring.job.configuration.util.PropriedadeUtil;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ContextoExecucaoObterClientesItemStreamReader {

	@Value("${programa.totalDeRegistros}")
	private long totalDeRegistros;

	private AtomicLong registrosLidos;

	public long getTotalDeRegistros() {
		return totalDeRegistros;
	}

	public void setTotalDeRegistros(long totalDeRegistros) {
		this.totalDeRegistros = totalDeRegistros;
	}

	public synchronized AtomicLong getRegistrosLidos() {
		return registrosLidos;
	}

	public synchronized void setRegistrosLidos(AtomicLong registrosLidos) {
		this.registrosLidos = registrosLidos;
	}

	public void restaurarContexto(ExecutionContext executionContext) {
		setRegistrosLidos(new AtomicLong(obterRegistrosLidos(executionContext)));
		totalDeRegistros = obterTotalRegistros(executionContext);
	}

	public void salvarContexto(ExecutionContext executionContext) {
		definirRegistrosLidos(executionContext);
		definirTotalRegistros(executionContext);
	}

	private Long obterRegistrosLidos(ExecutionContext executionContext) {
		String valor = PropriedadeUtil.obterPropriedade(ObterClientePropriedade.REGISTROS_LIDOS,
				executionContext);
		return Long.parseLong(valor);
	}

	private Long obterTotalRegistros(ExecutionContext executionContext) {
		String valor = PropriedadeUtil.obterPropriedade(ObterClientePropriedade.TOTAL_DE_REGISTROS,
				executionContext, Long.toString(totalDeRegistros));
		return Long.parseLong(valor);
	}

	private void definirRegistrosLidos(ExecutionContext executionContext) {
		executionContext.put(ObterClientePropriedade.REGISTROS_LIDOS.getNome(),
				Long.toString(getRegistrosLidos().longValue()));
	}

	private void definirTotalRegistros(ExecutionContext executionContext) {
		executionContext.put(ObterClientePropriedade.TOTAL_DE_REGISTROS.getNome(),
				Long.toString(totalDeRegistros));
	}
}
