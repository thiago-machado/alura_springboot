package br.com.totusttus.alura;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class Configuracao {

	public static void main(String[] args) {
		SpringApplication.run(Configuracao.class, args);
	}

	/*
	 * É através da anotação @Bean que o Spring Boot sabe que ele deve gerenciar
	 * esse bean. Assim é possível usa-lo onde desejar dentro da aplicação atráves
	 * da injeção de dependencia.
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/alura_springboot");
		dataSource.setUsername("usuarioteste");
		dataSource.setPassword("teste123");
		return dataSource;
	}
}
