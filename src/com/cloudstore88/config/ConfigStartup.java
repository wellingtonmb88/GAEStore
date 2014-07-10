package com.cloudstore88.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cloudstore88.model.Produto;
import com.cloudstore88.model.Usuario;
import com.googlecode.objectify.ObjectifyService;

/**
 * Componente necessário para registrar no Objectify quais são as entidades que ele deve gerenciar.
 * 
 * Código executado durante a inicialização do aplicativo web.
 */
public class ConfigStartup implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ObjectifyService.register(Usuario.class);
		ObjectifyService.register(Produto.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}

}