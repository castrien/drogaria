package br.com.claudio.drogaria.dao;

import org.junit.Test;

import br.com.claudio.drogaria.domain.Estado;

public class EstadoDAOTest {
	@Test
	public void salvar(){
		Estado estado = new Estado();
		estado.setNome("Pernanbuco");
		estado.setSigla("PE");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}
}
