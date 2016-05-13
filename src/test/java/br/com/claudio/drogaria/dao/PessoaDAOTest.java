package br.com.claudio.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.claudio.drogaria.domain.Cidade;
import br.com.claudio.drogaria.domain.Pessoa;

public class PessoaDAOTest {
		@Test
		public void salvar(){
			Pessoa pessoa = new Pessoa();
			
			CidadeDAO cidadeDAO = new CidadeDAO();
			Cidade cidade = cidadeDAO.buscar(50l);
			
			pessoa.setNome("Claudio Alessandro");
			pessoa.setBairro("Rio Vermelho");
			pessoa.setCelular("71981384248");
			pessoa.setCep("41950480");
			pessoa.setCidade(cidade);
			pessoa.setCpf("06098774536");
			pessoa.setEmail("teste");
			pessoa.setNumero(new Short("6"));
			pessoa.setRg("1453878289");
			pessoa.setTelefone("7133454267");
			pessoa.setComplemento("teste");
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.salvar(pessoa);
			
		}
		
		@Test
		@Ignore
		public void listar(){
			PessoaDAO pessoaDAO = new PessoaDAO();
			List<Pessoa> resultado = pessoaDAO.listar();
			
			for (Pessoa pessoa : resultado){
				System.out.println(pessoa.getCodigo() + " " + pessoa.getNome());
			}
		}
		
		@Test
		@Ignore
		public void buscar(){
			Long codigo = 40l;
			
			PessoaDAO PessoaDAO = new PessoaDAO();
			Pessoa pessoa = PessoaDAO.buscar(codigo);
			
			if(pessoa == null ){
				System.out.println("Registro não encontrado");
			}
			else{
				System.out.println(pessoa.getCodigo() + " " + pessoa.getNome());
			}
		}
		
		@Test
		@Ignore
		public void excluir(){
			Long codigo = 10l;
			
			PessoaDAO PessoaDAO = new PessoaDAO();;
			Pessoa pessoa = PessoaDAO.buscar(codigo);
			
			if(pessoa == null ){
				System.out.println("Registro não encontrado");
			}
			else{
				PessoaDAO.excluir(pessoa);
				System.out.println("Registro Excluído");
			}
			
		}
		
		@Test
		@Ignore
		public void editar(){
			Long codigo = 40l;
			
			PessoaDAO pessoaDAO = new PessoaDAO();;
			Pessoa pessoa = pessoaDAO.buscar(codigo);
			
			if(pessoa == null ){
				System.out.println("Registro não encontrado");
			}
			else{
				pessoa.setNome("teste");
				pessoa.setBairro("teste");
				pessoa.setCelular("teste");
				pessoa.setCep("teste");
				pessoa.setCpf("teste");
				pessoa.setEmail("teste");
				pessoa.setRg("teste");
				pessoa.setTelefone("teste");
				
				pessoaDAO.editar(pessoa);
				System.out.println("Registro Editado");
			}
		}
}
