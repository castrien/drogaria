package br.com.claudio.drogaria.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.claudio.drogaria.domain.Pessoa;
import br.com.claudio.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	
	@Ignore
	@Test
	public void salvar(){
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(11l);
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setSenhaSemCriptografia("teste2");
		
		//criando o objeto para criptografar a senha
		SimpleHash hash = new SimpleHash("md5",usuario.getSenhaSemCriptografia());
		//criptografando a senha e salvando
		usuario.setSenha(hash.toHex());
		usuario.setTipo('B');
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}
	
	@Test
	public void autenticar(){
		String cpf = "060.987.745-36";
		String senha = "teste2";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(cpf, senha);
		
		System.out.println("Usuaurio atentica: " + usuario);
	}
}
