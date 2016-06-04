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

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean3 implements Serializable {
	private Long codigoProduto;
	private List <Produto> produtos;
	private Produto produto;
	private ProdutoDAO produtoDAO;
	private List<Fabricante> fabricantes;
	private FabricanteDAO fabricanteDAO;
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	public Long getCodigoProduto() {
		return codigoProduto;
	}
	
	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	
	@PostConstruct
	public void iniciar(){
		produtoDAO = new ProdutoDAO();
		fabricanteDAO = new FabricanteDAO();
	}
	
	public void listar(){
		try {
			produtos = produtoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}
	
	public void carregarEdicao(){
		try {
			produto = produtoDAO.buscar(codigoProduto);
			fabricantes = fabricanteDAO.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar os dados para edição");
			erro.printStackTrace();
		}
	}
}
