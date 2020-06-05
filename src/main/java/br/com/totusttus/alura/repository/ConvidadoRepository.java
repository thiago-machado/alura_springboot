package br.com.totusttus.alura.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.totusttus.alura.model.Convidado;

/**
 * Com CrudRepository ganhamos um CRUD genérico. Ver um exmeplo na classe
 * ConvidadoController.
 * Ou seja, ganhamos todos os métodos necessários, como: find(), save() e delete().
 * 
 * 
 * A interface apenas tem os indicadores onde quais classes são a entidade e o
 * identificador único, que neste caso são: classe Convidado e o atributo do
 * tipo Long.
 * 
 * @author thiago.machado
 *
 */
public interface ConvidadoRepository extends CrudRepository<Convidado, Long> {

	/*
	 * Internamente, o CrudRepository criará a seguinte Query: SELECT * FROM <table> WHERE nome = ?
	 */
	List<Convidado> findByNome(String nome);
}
