package br.com.totusttus.alura.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

/**
 * Antes, o controller de convidados estava acessando diretamente o repositório
 * de convidados, o que de certa forma pode não parecer problemático, mas não
 * está dentro dos padrões de projetos adequados.
 * 
 * A solução adequada é que para obter e salvar convidados em nossa aplicação,
 * não é acessar a base de dados diretamente do controller, mas sim por meio de
 * um serviço. Criamos então essa classe com a anotação @Service.
 * 
 * Após isso, moveremos o código que recupera todos os convidados que está na
 * classe ConvidadoController para um método que chamaremos de obterTodos nesta
 * nova classe.
 * 
 * Por fim, o Controller deverá ter essa classe de serviço como um
 * atributo @Autowired.
 * 
 * E por meio de uma instância dessa classe, teremos acesso aos dados da base de
 * dados.
 * 
 * @author thiago.machado
 *
 */
@Service
public class EmailService {

	public void enviar(String nome, String emailConvidado) {

		System.out.println("Enviando e-mail...");

		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587);
			email.setAuthenticator(new DefaultAuthenticator("machado.priest@gmail.com", "killers81"));
			email.setSSLOnConnect(true);

			email.setFrom("machado.priest@gmail.com");
			email.setSubject("Você foi convidado pelo ListaVIP");
			email.setMsg("Olá " + nome + ". Você acaba de ser convidado pelo ListaVIP.");
			email.addTo(emailConvidado);
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		}
		System.out.println("Encerrando envio de e-mail...");
	}

}
