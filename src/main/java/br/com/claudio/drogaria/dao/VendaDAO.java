package br.com.claudio.drogaria.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omnifaces.util.Messages;

import br.com.claudio.drogaria.domain.ItemVenda;
import br.com.claudio.drogaria.domain.Produto;
import br.com.claudio.drogaria.domain.Venda;
import br.com.claudio.drogaria.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda> {
	
	public void salvar(Venda venda, List<ItemVenda> itensVenda){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
				
				try{
					transacao = sessao.beginTransaction();
					
					sessao.save(venda);
					
					for(int posicao = 0; posicao < itensVenda.size(); posicao++){
						ItemVenda itemVenda = itensVenda.get(posicao);
						itemVenda.setVenda(venda);
						
						sessao.save(itemVenda);
						
						Produto produto = itemVenda.getProduto();
						//convertendo o int que é gerada da soma para string e depois para Short
						int qtd = produto.getQuantidade() - itemVenda.getQuantidade();
						//verificando se o estoque não dará negativo
						if(qtd >= 0){
							produto.setQuantidade(new Short(produto.getQuantidade() - itemVenda.getQuantidade() +""));
						
							sessao.update(produto);
						}else{
							throw new RuntimeException("Quantidade insuficiente no estoque");
						}
					}
					transacao.commit();
				}catch(RuntimeException erro){
					if(transacao != null){
						transacao.rollback();
					}
					throw erro;
				}
		finally {
			sessao.close();
		}
	}
	}

