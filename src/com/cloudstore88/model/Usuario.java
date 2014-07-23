package com.cloudstore88.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@SuppressWarnings("serial")
@Entity
@Cache
public class Usuario implements Serializable {

	@Id
	private Long id;

	@NotNull(message = "O Login não pode ser nulo!")
	@Size(min = 5, max = 30, message = "O Login deve conter no mínimo 5 e máximo 30 caracteres!")
	private String login;

	@NotNull(message = "Email não pode ser nulo!")
	@Size(min = 5, max = 30, message = "Email inválido!")
	private String email;

	@NotNull(message = "Por favor digite sua senha!")
	@Size(min = 6, max = 15, message = "Sua senha deve ter entre 6 e 15 caracteres!")
	private String senha;

	public Usuario() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
