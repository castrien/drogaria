package br.com.claudio.drogaria.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.claudio.drogaria.dao.ClienteDAO;
import br.com.claudio.drogaria.dao.FuncionarioDAO;
import br.com.claudio.drogaria.dao.ProdutoDAO;
import br.com.claudio.drogaria.dao.VendaDAO;
import br.com.claudio.drogaria.domain.Cliente;
import br.com.claudio.drogaria.domain.Funcionario;
import br.com.claudio.drogaria.domain.ItemVenda;
import br.com.claudio.drogaria.domain.Produto;
import br.com.claudio.drogaria.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {
	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;
	private Venda venda;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;
	

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}
	
	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@PostConstruct
	public void novo() {
		try {
			venda = new Venda();
			venda.setPrecototal(new BigDecimal("0.00"));
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
			itensVenda = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;

		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {

			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setValorParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));

			itensVenda.add(itemVenda);
		}else{
			ItemVenda itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			itemVenda.setValorParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}
		
		calcular();

	}
	
	public void remover(ActionEvent evento){
	ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");
	
	int achou = -1;
	
	for(int posicao = 0;posicao < itensVenda.size(); posicao++){
		if(itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())){
			achou = posicao;
		}
	}
	if(achou > -1){
		itensVenda.remove(achou);
	}
	calcular();
	}
	
	public void calcular(){
		venda.setPrecototal(new BigDecimal("0.00"));
		
		for(int posicao = 0; posicao < itensVenda.size(); posicao++){
			ItemVenda itemVenda = itensVenda.get(posicao);
			venda.setPrecototal(venda.getPrecototal().add(itemVenda.getValorParcial())); 
		}
	}
	
	public void finalizar(){
		try {
			venda.setFuncionario(null);
			venda.setCliente(null);
			venda.setHorario(new Date());
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listarOrdenado();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a venda");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			if(venda.getPrecototal().signum() == 0){
				Messages.addGlobalInfo("Infome pelo menos um item para a venda");
			}else{
				VendaDAO vendaDAO = new VendaDAO();
				vendaDAO.salvar(venda, itensVenda);
				novo();
				Messages.addGlobalInfo("Venda realizada com sucesso!");
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar salvar venda");
			erro.printStackTrace();
		}
	}
}
