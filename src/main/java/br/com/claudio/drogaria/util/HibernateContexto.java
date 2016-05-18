package br.com.claudio.drogaria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.junit.Test;

public class HibernateContexto implements ServletContextListener  {

	@Override
	public void contextDestroyed(ServletContextEvent evento) {
		HibernateUtil.getFabricaDeSessoes().close();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent evento){
		HibernateUtil.getFabricaDeSessoes();
		
	}

}
