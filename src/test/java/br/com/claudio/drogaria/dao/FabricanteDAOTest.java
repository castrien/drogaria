package br.com.claudio.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.claudio.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	@Test
	
	public void salvar(){
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Teste");
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
		
	}
	
	@Test
	@Ignore
	public void listar(){
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> resultado = fabricanteDAO.listar();
		
		for (Fabricante fabricante : resultado){
			System.out.println(fabricante.getCodigo() + " " + fabricante.getDescricao());
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codigo = 40l;
		
		FabricanteDAO fabricantedao = new FabricanteDAO();
		Fabricante fabricante = fabricantedao.buscar(codigo);
		
		if(fabricante == null ){
			System.out.println("Registro não encontrado");
		}
		else{
			System.out.println(fabricante.getCodigo() + " " + fabricante.getDescricao());
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 10l;
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();;
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		if(fabricante == null ){
			System.out.println("Registro não encontrado");
		}
		else{
			fabricanteDAO.excluir(fabricante);
			System.out.println("Registro Excluído");
		}
		
	}
	@Ignore
	@Test
	public void editar(){
		Long codigo = 40l;
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();;
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		if(fabricante == null ){
			System.out.println("Registro não encontrado");
		}
		else{
			fabricante.setDescricao("HP");
			fabricanteDAO.editar(fabricante);
			System.out.println("Registro Editado");
		}
	}
	
	@Ignore
	@Test
	public void merge(){
		//Fabricante fabricante = new Fabricante();
		//fabricante.setDescricao("Fabricante A");
		
		//FabricanteDAO fabricanteDAO = new FabricanteDAO();
		//fabricanteDAO.salvar(fabricante);
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(160l);
		fabricante.setDescricao("Fabricante B");
		fabricanteDAO.merge(fabricante);
		
	}
}
