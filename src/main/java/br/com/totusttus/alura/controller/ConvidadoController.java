package br.com.totusttus.alura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.totusttus.alura.model.Convidado;
import br.com.totusttus.alura.service.ConvidadoService;
import br.com.totusttus.alura.service.EmailService;

@Controller
public class ConvidadoController {
	
	@Autowired
	private ConvidadoService convidadoService;
	/*
	 * Normalmente, as páginas são criadas com JSP padrão. Mas como estamos
	 * utilizando Spring Boot com o starter web que nos disponibiliza o Spring MVC,
	 * utilizaremos outra engine de templates. Uma do próprio Spring, chamada
	 * Thymeleaf.
	 * 
	 * Então, vamos criar a pasta "templates" em "src/main/resources". Dentro dessa
	 * pasta, vamos criar um HTML chamado index.html
	 * 
	 * Para funcionar o engine de templates na nossa aplicação, precisamos adicionar
	 * a dependência do Thymeleaf.
	 * 
	 * Para a adição dos arquivos estáticos do Bootstrap, criaremos uma nova pasta
	 * chamada "static", dentro da pasta "resources".
	 * 
	 * 
	 * ***IMPORTANTE***
	 * 
	 * Observações: Muito do que vimos até agora, está dentro de um conceito muito
	 * comum presente nos frameworks mais modernos que é a Convenção sobre a
	 * Configuração. Antes precisaríamos configurar uma série de recursos para ter a
	 * aplicação funcionando. Agora apenas seguindo algumas convenções, pulamos
	 * todas as configurações e apenas focamos no que é importante.
	 * 
	 * Das convenções que vimos até aqui temos: Os templates das páginas são
	 * guardados na pasta templates dentro de resources e também a convenção de onde
	 * armazenar os arquivos estáticos (css, js, imagens, etc.) que ficam dentro da
	 * pasta static.
	 */
	@RequestMapping("/")
	public String index() {

		/*
		 * Nome do arquivo HTML sem extensão, já que engine de template fará as
		 * associações.
		 */
		return "index";
	}

	/*
	 * O passo a seguir é capturar todos os registros presentes no banco de dados
	 * usando o objeto repository e deixamos disponível para a página por meio de um
	 * outro objeto, chamado Model, que será recebido como parâmetro no método
	 * listaConvidados.
	 * 
	 * O model será disponibilizado para a view (página) pelo Spring. O método usará
	 * o findAll do repository para retornar todos os registro em um Iterable por
	 * onde podemos iterar. Adicionamos os convidados como atributo de model e
	 * retornamos o nome do template.
	 * 
	 * Como não estamos usando mais JSP, teremos que utilizar um outro starter do
	 * Spring Boot para que nossos templates possam capturar os objetos que estamos
	 * enviando para estes e fazer com que a página fique dinâmica. É aqui que
	 * começamos a usar o Thymeleaf. No pom.xml teremos mais uma dependência.
	 */
	@RequestMapping("lista_convidados")
	public String listaConvidados(Model model) {

		// Pegando todos os convidados. findAll é a mesma coisa que "SELECT * FROM
		// <table>"
		Iterable<Convidado> convidados = convidadoService.obterTodos();
		model.addAttribute("convidados", convidados); // enviando lista para o HTML

		return "lista_convidados";
	}

	/*
	 * Mapeando "salvar" que será requisitado via POST.
	 * Recebendo por parâmetro (@RequestParam): nome, email e telefone.
	 * Também estamos recebendo por parâmetro o Model para poder retornar dados à tela.
	 */
	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
			@RequestParam("telefone") String telefone, Model model) {

		convidadoService.salvar(new Convidado(nome, email, telefone)); // Persistindo a informação na tela

		new EmailService().enviar(nome, email);
		
		return "redirect:lista_convidados"; // chamando novamente lista_convidados
	}
	
	@RequestMapping(value = "deletar", method = RequestMethod.POST)
	public String deletar(@RequestParam("id") String id, Model model) {
		Convidado convidado = convidadoService.buscarPorID(Long.valueOf(id));
		
		if(convidado != null)
			convidadoService.deletar(convidado);
		
		return "redirect:lista_convidados"; // chamando novamente lista_convidados
	}

}
