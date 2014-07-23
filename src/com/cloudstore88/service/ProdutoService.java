package com.cloudstore88.service;

import java.util.List;

import com.cloudstore88.model.Produto;

public class ProdutoService implements IProduto {

	private ProdutoDAO produtoDAO;

	public ProdutoService() {
		produtoDAO = new ProdutoDAO();
	}

	@Override
	public void salvarProduto(Produto produto) {
		produtoDAO.salvarProduto(produto);
	}
 
	@Override
	public List<Produto> listarProdutos() {

		List<Produto> produtos = produtoDAO.listarProdutos();

		return produtos;
	}

	@Override
	public Produto buscaProdutoPorId(Long id) {
		Produto produto = produtoDAO.buscaProdutoPorId(id);
		return produto;
	}

	@Override
	public void deleteProduto(Produto produto) {
		produtoDAO.deleteProduto(produto);
	}

}
