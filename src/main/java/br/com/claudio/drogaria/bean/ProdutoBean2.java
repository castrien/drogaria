package br.com.claudio.drogaria.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.claudio.drogaria.dao.ProdutoDAO;
import br.com.claudio.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean2 implements Serializable {
	private Produto produto;
	private Boolean exibePainelDados;
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Boolean getExibePainelDados() {
		return exibePainelDados;
	}
	
	public void setExibePainelDados(Boolean exibePainelDados) {
		this.exibePainelDados = exibePainelDados;
	}
	
	@PostConstruct
	public void novo(){
		produto = new Produto();
		exibePainelDados = false;
	}
	
	public void buscar(){
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto resultado = produtoDAO.buscar(produto.getCodigo());
			if(resultado == null){
				exibePainelDados = false;
				Messages.addGlobalWarn("Não existe produto cadastrado com esse código");
			}else{
				exibePainelDados = true;
				produto = resultado;
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Aconteceu um erro ao tentar buscar o produto");
			erro.printStackTrace();
		}
	}
}
