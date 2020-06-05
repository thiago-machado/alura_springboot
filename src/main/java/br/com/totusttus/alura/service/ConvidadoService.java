package br.com.totusttus.alura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.totusttus.alura.model.Convidado;
import br.com.totusttus.alura.repository.ConvidadoRepository;

@Service
public class ConvidadoService {

	/*
	 * https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
	 */
	@Autowired
	private ConvidadoRepository repository;

	public Iterable<Convidado> obterTodos() {
		Iterable<Convidado> convidados = repository.findAll();
		return convidados;
	}
	
	public Convidado buscarPorID(Long id) {
		return repository.findById(Long.valueOf(id)).get();
	}
	
	public void deletar(Convidado convidado) {
		repository.delete(convidado);
	}

	public void salvar(Convidado convidado) {
		repository.save(convidado);
	}
}
