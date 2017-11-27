package br.com.alura.maven;

import org.junit.Assert;
import org.junit.Test;

public class ProdutoTest {

	@Test
	public void verificaPrecoComImposto() {
		Produto bala = new Produto("juquinha", 0.10);
		Assert.assertEquals(0.11, bala.getPrecoComImposto(), 0.00001);
	}
}
