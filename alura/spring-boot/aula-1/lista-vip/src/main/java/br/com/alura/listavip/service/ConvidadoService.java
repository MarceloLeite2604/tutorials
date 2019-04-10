package br.com.alura.listavip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.listavip.model.Convidado;
import br.com.alura.listavip.repository.ConvidadoRepository;

/*
 * Camada de serviço do nosso projeto.
 */
@Service
public class ConvidadoService {

	@Autowired
	private ConvidadoRepository repository;

	public ConvidadoService() {
	}

	public Iterable<Convidado> obterTodos() {
		Iterable<Convidado> convidados = repository.findAll();
		return convidados;
	}

	public void salvar(Convidado convidado) {
		repository.save(convidado);
	}

}