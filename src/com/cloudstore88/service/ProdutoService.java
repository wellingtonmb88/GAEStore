package com.cloudstore88.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.cloudstore88.model.Produto;
import com.googlecode.objectify.Key;

public class ProdutoService implements IProduto {
 

	@Override
	public void salvarProduto(Produto produto) {
		ofy().save().entities(produto).now();
	}

	@Override
	public List<Produto> listarProdutos() {
 
		return ofy().load().type(Produto.class).list();
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
