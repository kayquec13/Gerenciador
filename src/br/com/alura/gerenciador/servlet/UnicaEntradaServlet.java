package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciado.acao.Acao;

//Servlert unico, responsavel por direcionar as ações
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//String utilizada para pegar a açao, seja ela remover, mostrar ou listar 							
		
		System.out.println("unica entrada");
		
		String paramAcao = request.getParameter("acao");
		
		HttpSession sessao = request.getSession();
		boolean usuarioNaoEstaLogado = (sessao.getAttribute("usuarioLogado") == null);
		boolean ehUmaAcaoProtegida =  !(paramAcao.equals("Login") || paramAcao.equals("LoginForm")) ;
		
					
		String nomeDaClasse = "br.com.alura.gerenciado.acao."+ paramAcao;				
		
		
		if(ehUmaAcaoProtegida && usuarioNaoEstaLogado) {	
			System.out.println("teste teste teste");
			response.sendRedirect("entrada?acao=LoginForm");	
			return;
		}
		
		String nome;
		try {
			Class classe = Class.forName(nomeDaClasse);//Carrega a classe com o nome da classe
			Object obj  = classe.newInstance();		
			Acao acao = (Acao) obj;
			nome = acao.executa(request,response);										
		} catch ( ClassNotFoundException |InstantiationException  |   IllegalAccessException e) {
			
			throw new ServletException(e);
		}
		
		
		String[] tipoEEndereco = nome.split(":");
	    if(tipoEEndereco[0].equals("forward")) {
	        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]);
	        rd.forward(request, response);
	    } else {
	        response.sendRedirect(tipoEEndereco[1]);

	    }			
	}
}
