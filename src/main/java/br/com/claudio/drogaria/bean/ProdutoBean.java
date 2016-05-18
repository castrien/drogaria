package br.com.claudio.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.claudio.drogaria.dao.FabricanteDAO;
import br.com.claudio.drogaria.dao.ProdutoDAO;
import br.com.claudio.drogaria.domain.Fabricante;
import br.com.claudio.drogaria.domain.Produto;

@ViewScoped
@ManagedBean
@SuppressWarnings("serial")
public class ProdutoBean implements Serializable {
	Produto produto;
	List<Produto> produtos;
	List<Fabricante> fabricantes;
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	@PostConstruct
	public void listar(){
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		try {
			produto = new Produto();
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try{
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.merge(produto);
			novo();
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
			listar();
			Messages.addFlashGlobalInfo("Produto salvo com sucesso!");
		}catch(RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo produto");
			erro.printStackTrace();
		}
	}

}
