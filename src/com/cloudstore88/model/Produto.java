package com.cloudstore88.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@SuppressWarnings("serial")
@Entity
public class Produto implements Serializable {

	@Id
	private Long id;

	@NotNull(message = "O Nome n�o pode ser nulo!")
	@Size(min = 5, message = "O Nome deve conter no m�nimo 5 caracteres!")
	private String nome;

	@NotNull(message = "A Descri��o n�o pode ser nula!")
	@Size(min = 5, message = "A Descri��o deve conter no m�nimo 5 caracteres!")
	private String descricao;

	@NotNull(message = "A Quantidade n�o pode ser nula!")
	@Min(value = 1, message = "A Quantidade n�o pode ser menor que 1!")
	private Integer quantidade;

	@NotNull(message = "O Pre�o n�o pode ser nulo!")
	@NumberFormat(style = Style.CURRENCY)
	@Min(value = 1, message = "Por favor insira um valor maior que 0!")
	@Max(value = 1000000, message = "Por favor insira um valor menor que 1.000.000 !")
	private float preco;
	
	@NotNull(message = "A Categoria n�o pode ser nulo!")
	private String categoria;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Produto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

}
