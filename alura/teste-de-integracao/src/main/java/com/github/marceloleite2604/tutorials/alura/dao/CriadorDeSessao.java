package com.github.marceloleite2604.tutorials.alura.dao;

import com.github.marceloleite2604.tutorials.alura.dominio.Lance;
import com.github.marceloleite2604.tutorials.alura.dominio.Leilao;
import com.github.marceloleite2604.tutorials.alura.dominio.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class CriadorDeSessao {

	private static AnnotationConfiguration config;
	private static SessionFactory sf;
	
	public Session getSession() {
		if(sf == null) {
			sf = getConfig().buildSessionFactory();
		}
		
		return sf.openSession();
	}

	public Configuration getConfig() {
		if(config == null) {
			config = new AnnotationConfiguration()
		    .addAnnotatedClass(Lance.class)
		    .addAnnotatedClass(Leilao.class)
		    .addAnnotatedClass(Usuario.class)
			.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
			.setProperty("hibernate.connection.url", "jdbc:hsqldb:caelum.db;shutdown=true")
			.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
			.setProperty("hibernate.connection.username", "sa")
			.setProperty("hibernate.connection.password", "")
			.setProperty("hibernate.show_sql", "true");
		}
		return config;
	}
}
