package br.com.alura.gerenciador.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco {

	private static List<Empresa> lista = new ArrayList<>();
	private static List<Usuario> listaUsuario = new ArrayList<>();
	private static Integer chaveSequencial = 1;
	
	static {
		Empresa empresa = new Empresa();
		empresa.setId(chaveSequencial++);
		empresa.setNome("Conam");
		Empresa empresa2 = new Empresa();
		empresa2.setId(chaveSequencial++);
		empresa2.setNome("Fromtis");
		
		lista.add(empresa);
		lista.add(empresa2);
		
		Usuario u1 = new Usuario();
		u1.setLogin("kayque");
		u1.setSenha("1234");
		
		Usuario u2 = new Usuario();
		u2.setLogin("Joao");
		u2.setSenha("12345");
		
		listaUsuario.add(u1);
		listaUsuario.add(u2);
	}
	
	public void adiciona(Empresa empresa) {
		empresa.setId(Banco.chaveSequencial++);
		lista.add(empresa);
	}

	public List<Empresa> getEmpresa(){
		return Banco.lista;
	}

	public void removeEmpresa(Integer id) {
		
		Iterator<Empresa> it = lista.iterator();
		while (it.hasNext()) {
			Empresa emp = it.next();
			if(emp.getId() == id) {
				it.remove();
			}
		}it.hasNext();
		
		for (Empresa empresa : lista) {
			if(empresa.getId() == id) {
				lista.remove(empresa);
			}
			
		}
		
	}

	public Empresa buscaEmpresaId( Integer id) {
		
		for (Empresa empresa : lista) {
			if(empresa.getId() == id) {
				return empresa;
			}			
		}
		
		return null;
	}

	public Usuario existeUsuario(String login, String senha) {
	    for(Usuario usuario : listaUsuario) {
	        if(usuario.ehIgual(login, senha)) { 
	            return usuario;

	        }
	    }
	    return null;
	}
	
}
