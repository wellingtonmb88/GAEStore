package com.cloudstore88.service;

import java.util.List;

import com.cloudstore88.model.Usuario;

public class UsuarioService implements IUsuario {

	private UsuarioDAO usuarioDAO;

	public UsuarioService() {
		usuarioDAO = new UsuarioDAO();
	}

	@Override
	public void salvarUsuario(Usuario usuario) {
		usuarioDAO.salvarUsuario(usuario);
	}

	@Override
	public List<Usuario> listarUsuarios() {

		return usuarioDAO.listarUsuarios();
	}

	@Override
	public Usuario buscaUsuarioPorId(Long id) {

		return usuarioDAO.buscaUsuarioPorId(id);
	}

	@Override
	public void deleteUsuario(Usuario usuario) {
		usuarioDAO.deleteUsuario(usuario);
	}

}
