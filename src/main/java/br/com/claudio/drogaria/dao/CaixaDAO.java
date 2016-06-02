package br.com.claudio.drogaria.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.claudio.drogaria.domain.Caixa;
import br.com.claudio.drogaria.util.HibernateUtil;

public class CaixaDAO extends GenericDAO<Caixa> {

	public Caixa buscar(Date dataAbertura){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			//pegando uma sessao com o creteria
			Criteria consulta = sessao.createCriteria(Caixa.class);
			//gerando o where da consulta, "dataAbertura" é o campo da tabela do banco(classe Caixa.java)
			consulta.add(Restrictions.eqOrIsNull("dataAbertura", dataAbertura));
			//se o caixa do dia já está aberto retorna-o, se n retorna null
			Caixa resultado = (Caixa) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		}finally {
			sessao.close();
		}
	}
}
