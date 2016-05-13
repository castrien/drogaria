package br.com.claudio.drogaria.dao;

import org.junit.Test;

import br.com.claudio.drogaria.domain.Pessoa;
import br.com.claudio.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	
	@Test
	public void salvar(){
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(10l);
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setSenha("teste");
		usuario.setTipo('A');
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}
}
