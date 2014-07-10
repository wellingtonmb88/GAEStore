package com.cloudstore88.service;

import java.util.List;

import com.cloudstore88.model.Produto;

public interface IProduto  {
	
	public void salvarProduto(Produto produto); 
	public List<Produto> listarProdutos();
	public Produto buscaProdutoPorId(Long id);
	public void deleteProduto(Produto produto);

}
