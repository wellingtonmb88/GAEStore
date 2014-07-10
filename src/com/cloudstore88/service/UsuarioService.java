package com.cloudstore88.service;

import java.util.List;

import com.cloudstore88.model.Usuario;
import com.googlecode.objectify.Key; 
import static com.googlecode.objectify.ObjectifyService.ofy;

public class UsuarioService implements IUsuario {
 

	@Override
	public void salvarUsuario(Usuario usuario) {
		ofy().save().entities(usuario).now();
 
	}
 
	@Override
	public List<Usuario> listarUsuarios() {
 
		return ofy().load().type(Usuario.class).list();
	}

	@Override
	public Usuario buscaUsuarioPorId(Long id) { 
		Key<Usuario> k = Key.create(Usuario.class, id);
		return ofy().load().key(k).get();
	}

	@Override
	public void deleteUsuario(Usuario usuario) {
		ofy().delete().entity(usuario).now();		
	}

}
