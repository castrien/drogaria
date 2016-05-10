package br.com.claudio.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.claudio.drogaria.domain.Cidade;
import br.com.claudio.drogaria.domain.Estado;

public class CidadeDAOTest {
	@Test
	@Ignore
	public void salvar(){
		EstadoDAO estadoDAO = new EstadoDAO();
		
		Estado estado = estadoDAO.buscar(30l);
		
		Cidade cidade = new Cidade();
		cidade.setNome("Recife");
		cidade.setEstado(estado);
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}
	
	@Test
	@Ignore
	public void listar(){
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();
		
		for(Cidade cidade : resultado){
			System.out.println("Código: " + cidade.getCodigo());
			System.out.println("Nome: " + cidade.getNome());
			System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
			System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codigo = 50l;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		System.out.println("Código: " + cidade.getCodigo());
		System.out.println("Nome: " + cidade.getNome());
		System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
		System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
		System.out.println();
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 70l;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		cidadeDAO.excluir(cidade);
	}
	
	@Test
	public void editar(){
		Long codigoCidade = 60l;
		Long codigoEstado = 10l;
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigoEstado);
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		cidade.setNome("Vitória da Conquita");
		cidade.setEstado(estado);
		
		cidadeDAO.editar(cidade);
	}
}
