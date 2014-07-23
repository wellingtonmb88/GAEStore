package com.cloudstore88.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.cloudstore88.model.Produto;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.googlecode.objectify.Key;

public class ProdutoDAO implements IProduto {

	Cache cache;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ProdutoDAO() {

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
	public void salvarProduto(Produto produto) {
		ofy().save().entities(produto).now();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> listarProdutos() {

		String key = "listaProdutos";
		List<Produto> produtos = (List<Produto>) cache.get(key);

		if (produtos == null) {
			cache.put(key, produtos);
			produtos = ofy().load().type(Produto.class).list();
		}

		return produtos;
	}

	@Override
	public Produto buscaProdutoPorId(Long id) {
		Key<Produto> k = Key.create(Produto.class, id);
		return ofy().load().key(k).get();
	}

	@Override
	public void deleteProduto(Produto produto) {
		ofy().delete().entity(produto).now();
	}

}
