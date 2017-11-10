package br.com.caelum.leilao.builder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class CriadorDeLeilao {

	private String descricao;
	private Calendar data;
	private List<Lance> lances;
	private boolean encerrado;

	public CriadorDeLeilao() {
		this.data = Calendar.getInstance();
		lances = new ArrayList<Lance>();
	}
	
	public CriadorDeLeilao para(String descricao) {
		this.descricao = descricao;
		return this;
	}
	
	public CriadorDeLeilao naData(Calendar data) {
		this.data = data;
		return this;
	}

	public CriadorDeLeilao lance(Usuario usuario, double valor) {
		lances.add(new Lance(usuario, valor));
		return this;
	}

	public CriadorDeLeilao encerrado() {
		this.encerrado = true;
		return this;
	}

	public Leilao constroi() {
		Leilao leilao = new Leilao(descricao, data);
		for(Lance lanceDado : lances) leilao.propoe(lanceDado);
		if(encerrado) leilao.encerra();
				
		return leilao;
	}

}
