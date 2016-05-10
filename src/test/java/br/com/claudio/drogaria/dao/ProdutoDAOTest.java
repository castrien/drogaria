package br.com.claudio.drogaria.dao;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.claudio.drogaria.domain.Fabricante;
import br.com.claudio.drogaria.domain.Produto;

public class ProdutoDAOTest {
	
	@Test
	public void salvar(){
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(40l);
		
		Produto produto = new Produto();
		produto.setDescricao("Neosaldina 30mg com 16 comprimidos");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("13.70"));
		produto.setQuantidade(new Short("7"));
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
	}
}
