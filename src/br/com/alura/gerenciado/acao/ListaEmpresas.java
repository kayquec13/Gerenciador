package br.com.alura.gerenciado.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

public class ListaEmpresas implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessao = request.getSession();
		if(sessao.getAttribute("usuarioLogado") == null) {
			return "redirect:entrada?acao=LoginForm";
		}
		
		System.out.println("Listando empresas");
		
		//Criando um objeto banco		
		Banco banco = new Banco();
		
		//Criando uma lista de empresas		
		List<Empresa> lista = banco.getEmpresa();
		
		//Pegando a lista de empresas
		request.setAttribute("empresas", lista);
		
		//encaminhando a requisição para a pagina jsp
		return "forward:listasEmpresas.jsp";
	}
	
}
