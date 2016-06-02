package br.com.claudio.drogaria.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class HibernateUtil {
	   private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();
	   
	   
	   public static SessionFactory getFabricaDeSessoes() {
	        return fabricaDeSessoes;
	    }
	   
	   //tranformar uma sessão do hibernate em uma connection para poder ser
	   //acessada pelo Jasper e com isso criar relatórios
	   public static Connection getConexao(){
		   Session sessao = fabricaDeSessoes.openSession();
		   Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {
			   @Override
			public Connection execute(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				return conn;
			}
		});
		   return conexao;
	   }

	    private static SessionFactory criarFabricaDeSessoes() {
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml
	            Configuration configuracao = new Configuration().configure();
	            
	            ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
	            
	            SessionFactory fabrica = configuracao.buildSessionFactory(registro);
	            
	            return fabrica;
	        }
	        catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("A Fábrica de sessão não pôde ser criada. " + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	        
	        
	    }

	    
}
