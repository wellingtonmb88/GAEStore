package com.cloudstore88.model;

  
public class Carrinho  {
 
	private Produto produto;
	private Integer quantidade;
	private float total;
	
	public Carrinho() { 
	}
	
	public Carrinho(Produto produto) { 
		this.setProduto(produto);
		this.setQuantidade(1);
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
  
	public void incrementarQuantidade() {
		setQuantidade(getQuantidade()+1);
	}
 
	 
	public void setTotal(float total) {
		this.total = total;
	}

	public float getTotal(){
		this.total = (getQuantidade() * getProduto().getPreco());
		return total;
	}
	
	
}
