package com.cloudstore88.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.cloudstore88.model.Usuario;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.googlecode.objectify.Key;

public class UsuarioDAO implements IUsuario {

	Cache cache;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UsuarioDAO() {

		Map props = new HashMap();
		props.put(GCacheFactory.EXPIRATION_DELTA, 3600);

		try {
			CacheFactory cacheFactory = CacheManager.getInstance()
					.getCacheFactory();
			cache = cacheFactory.createCache(props);
		} catch (CacheException e) {
			System.out.println("Erro CacheException: " + e);
		}
	}

	@Override
	public void salvarUsuario(Usuario usuario) {
		ofy().save().entities(usuario).now();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarUsuarios() {

		String key = "listaUsuarios";
		List<Usuario> usuarios = (List<Usuario>) cache.get(key);
		if (usuarios == null) {
			cache.put(key, usuarios);
			usuarios = ofy().load().type(Usuario.class).list();
		}
		return usuarios;
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
