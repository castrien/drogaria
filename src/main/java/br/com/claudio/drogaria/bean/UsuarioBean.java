package br.com.claudio.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Messages;

import br.com.claudio.drogaria.dao.PessoaDAO;
import br.com.claudio.drogaria.dao.UsuarioDAO;
import br.com.claudio.drogaria.domain.Pessoa;
import br.com.claudio.drogaria.domain.Usuario;
import br.com.claudio.drogaria.util.HibernateUtil;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
	private List<Usuario> usuarios;
	private Usuario usuario;
	private List<Pessoa> pessoas;
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuaios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	@PostConstruct
	public void listar(){
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		}
		catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuarios");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		try {
			usuario = new Usuario();
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo usuario");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			SimpleHash hash = new SimpleHash("md5",usuario.getSenha());
			
			usuario.setSenha(hash.toHex());
			
			usuarioDAO.merge(usuario);
			
			
			usuario = new Usuario();
			
			listar();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
			Messages.addGlobalInfo("Usuário salvo com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuario");
			erro.printStackTrace();
		}
	}
	
}
