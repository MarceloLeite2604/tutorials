package br.com.caelum.livraria.dao;

import java.util.List;

import br.com.caelum.livraria.modelo.Livro;


public class LivroDao {

	private Banco banco = new Banco();
	
	public void salva(Livro livro) {
		banco.save(livro);
	}
	
	public List<Livro> todosLivros() {
		return banco.listaLivros();
	}
	
}
