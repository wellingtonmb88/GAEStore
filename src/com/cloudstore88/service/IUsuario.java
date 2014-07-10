package com.cloudstore88.service;

import java.util.List;

import com.cloudstore88.model.Usuario;

public interface IUsuario  {
	
	public void salvarUsuario(Usuario usuario); 
	public List<Usuario> listarUsuarios();
	public Usuario buscaUsuarioPorId(Long id);
	public void deleteUsuario(Usuario usuario);

}
