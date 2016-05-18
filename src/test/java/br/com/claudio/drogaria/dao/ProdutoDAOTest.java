package br.com.claudio.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.claudio.drogaria.domain.Fabricante;
import br.com.claudio.drogaria.domain.Produto;

public class ProdutoDAOTest {
	
	
	@Test
	public void salvar(){
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(40l);
		
		Produto produto = new Produto();
		produto.setDescricao("Teste");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("20.20"));
		produto.setQuantidade(new Short("10"));
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
	}
	
	@Ignore
	@Test
	public void listar(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar();
		
		for(Produto produto: resultado){
			System.out.println(produto.getDescricao() + " - " + produto.getQuantidade() + " - " + produto.getFabricante().getDescricao());
		}
	}
}
