package br.com.claudio.drogaria.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.claudio.drogaria.dao.UsuarioDAO;
import br.com.claudio.drogaria.domain.Pessoa;
import br.com.claudio.drogaria.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {

	private Usuario usuario;
	private Usuario usuarioLogado;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@PostConstruct
	public void iniciar(){
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsaurioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public void autenticar(){
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(),usuario.getSenha());
			
			if(usuarioLogado == null ){
				Messages.addGlobalError("CPF e/ou senha incorretos");
				return;
			}
			
			Faces.redirect("./pages/index.xhtml");
		}catch(IOException erro){
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
	
	public boolean temPermissoes(List<String> permissoes){
		for(String permissao : permissoes){
			if(usuarioLogado.getTipo() == permissao.charAt(0)){
				return true;
			}
		}
		return false;
	}
}
