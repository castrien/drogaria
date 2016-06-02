package br.com.claudio.drogaria.bean;

import java.awt.Frame;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

import br.com.claudio.drogaria.dao.FabricanteDAO;
import br.com.claudio.drogaria.dao.ProdutoDAO;
import br.com.claudio.drogaria.domain.Fabricante;
import br.com.claudio.drogaria.domain.Produto;
import br.com.claudio.drogaria.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

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
	
/*	public void imprimir(){
		try {
			//capturar caminho da memoria
			String caminho = Faces.getRealPath("/reports/produtos.jasper");
			//criando um Map para receber um parametro
			Map<String, Object> parametros = new HashMap<>();
			//pegando uma connection com o hibernate(de uma session)
			Connection conexao = HibernateUtil.getConexao();
			//imprimir o relatorio populado
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			//ativando a impressao no navegador(control p)
			JasperPrintManager.printReport(relatorio, true);
		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um relatório");
			erro.printStackTrace();
		}
	} */
	
	public void imprimir() {
		  try {    
		          String path = "C:/Users/Eurico/Programação Web3 com Java/workspace/Drogaria/src/main/webapp/reports/produto.jasper";
		 Connection conexao = HibernateUtil.getConexao();
		          Map<String, Object> parametros = new HashMap<>();
		          
		          DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("frmListagem:tabela");
		    Map<String, Object> filtros = tabela.getFilters();
		    String produto = (String) filtros.get("descricao");
		          //System.out.println("Filtro: "+produto);//Usei para testar a variavel
		          if(produto == null){
		           parametros.put("PRODUTO", "%%");                 
		          }else{
		           parametros.put("PRODUTO", "%"+produto+"%");   
		          }
		         
		          JasperPrint relatorio = JasperFillManager.fillReport(path, parametros,conexao);
		          
		          JasperViewer view = new JasperViewer(relatorio, false);
		          view.setVisible(true);
		          view.setExtendedState(Frame.MAXIMIZED_BOTH);
		          view.setTitle("Relatório de Produtos");
		          
		  } catch (JRException | NullPointerException erro) {
		   Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
		   erro.printStackTrace();
		  }
	}
}


