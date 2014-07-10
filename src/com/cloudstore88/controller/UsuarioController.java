package com.cloudstore88.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cloudstore88.model.Usuario;
import com.cloudstore88.service.UsuarioService;

@Controller
@SessionAttributes
public class UsuarioController {

	//Variáveis
	private final String MASTER_ADMIN_LOGIN = "adminMaster";
	private final String MASTER_ADMIN_SENHA = "@123456!";
	private final String MASTER_ADMIN_EMAIL = "adminMaster@gmail.com";
	private boolean newUser = false;
	private UsuarioService usuarioService = new UsuarioService();
	private Usuario usuario;
	private List<Usuario> usuarios = new ArrayList<>();

	// ////////////////////////////////////---- ADMIN
	// CONTROLLER-----////////////////////////////////////////////////

	/**
	 * Verifica se o login do usuário é válido. 
	 */
	@RequestMapping(value = "/Admin/validarUsuario", method = RequestMethod.POST)
	public ModelAndView validarAdmin(@RequestParam("login") String login,
			@RequestParam("senha") String senha, HttpServletRequest request) {

		Usuario masterUsuario = new Usuario();

		masterUsuario.setLogin(MASTER_ADMIN_LOGIN);
		masterUsuario.setSenha(MASTER_ADMIN_SENHA);
		masterUsuario.setEmail(MASTER_ADMIN_EMAIL);

		String redirect = "admin/login";

		if ((login.equals(MASTER_ADMIN_LOGIN) && senha
				.equals(MASTER_ADMIN_SENHA))) {

			usuario = masterUsuario;
			redirect = "admin/usuarioPage";

			HttpSession session = request.getSession();
			session.setAttribute("usuarioNome", usuario.getLogin());

		}

		return new ModelAndView(redirect, "usuario", usuario);
	}

	/**
	 * Direciona a página de  login.
	 */
	@RequestMapping("/Admin/login")
	public ModelAndView loginAdmin() {

		return new ModelAndView("admin/login");
	}

	/**
	 * Adiciona novo usuário 
	 */
	@RequestMapping(value = "/Admin/addUsuario", method = RequestMethod.POST)
	public String addAdmin(@Valid @ModelAttribute("usuario") Usuario usuario,
			BindingResult result) {

		if (result.hasErrors()) {
			return "admin/usuarioPage";
		} else {
			newUser = true;
			usuarioService.salvarUsuario(usuario);
		}

		return "redirect:listaUsuarios.html";
	}
	
	/**
	 * Edita usuário.
	 */
	
	@RequestMapping(value = "/Admin/editarAdmin", method = RequestMethod.GET)
	public ModelAndView editarUsuarioAdmin(@RequestParam("usuarioId") String id) {

		Long usuarioId = Long.parseLong(id);

		Usuario u = usuarioService.buscaUsuarioPorId(usuarioId);

		return new ModelAndView("admin/usuarioPage", "usuario", u);
	}

	/**
	 * Exclui usuário.
	 */
	@RequestMapping(value = "/Admin/deletarUsuario", method = RequestMethod.GET)
	public String deletarUsuario(@RequestParam("usuarioId") String id) {

		Long usuarioId = Long.parseLong(id);

		Usuario u = usuarioService.buscaUsuarioPorId(usuarioId);

		usuarioService.deleteUsuario(u);

		return "redirect:listaUsuarios.html";
	}

	/**
	 * Mostra a lista de usuários
	 */
	@RequestMapping("/Admin/listaUsuarios")
	public ModelAndView listarUsuarios(HttpServletResponse response,
			Model uiModel) {

		uiModel.addAttribute("active", "listaUsuarios");

		if (newUser) {
			response.setIntHeader("Refresh", 2);
			newUser = false;
		}

		return new ModelAndView("admin/listaUsuarios", "usuarios",
				usuarioService.listarUsuarios());
	}
	
	/**
	 * Carrega as informações do uruário.
	 */
	@RequestMapping("/Admin/perfil")
	public ModelAndView perfilAdmin(Model uiModel) {

		uiModel.addAttribute("active", "perfil");

		return new ModelAndView("admin/usuarioPage", "usuario", usuario);

	}
	
	/**
	 * Mostra o formulário de novo usuário.
	 */
	@RequestMapping("/Admin/usuarioPage")
	public ModelAndView formAdmin(Model uiModel) {

		uiModel.addAttribute("usuario", new Usuario());

		return new ModelAndView("admin/usuarioPage");

	}

	/**
	 * Faz o log-out e invalida a session do usuário.
	 */
	@RequestMapping("/Admin/logout")
	public ModelAndView logoutAdmin(HttpServletRequest request) {

		HttpSession session = request.getSession();

		session.invalidate();

		return new ModelAndView("admin/login");

	}

	// //////////////////////---- USUARIO CONTROLLER
	// -------///////////////////////////////////////////////////////////

	/**
	 * Acessa a página de login
	 */
	@RequestMapping("/User/login")
	public ModelAndView loginUsuario( HttpServletRequest request) {


		HttpSession session = request.getSession();
		String caller = (String) session.getAttribute("caller");
  
		request.setAttribute("caller", caller);
		
		return new ModelAndView("user/login", "usuarios", usuarios);
	}

	/**
	 * Adiciona novo usuário 
	 */
	@RequestMapping(value = "/User/addUsuario", method = RequestMethod.POST)
	public String addUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
			BindingResult result) {

		if (result.hasErrors()) {
			return "user/usuarioPage";
		} else {
			newUser = true;
			usuarioService.salvarUsuario(usuario);
		}

		return "redirect:listaProdutos.html";
	}

	/**
	 * Faz a validação do usuário.
	 */
	@RequestMapping(value = "/User/validarUsuario", method = RequestMethod.POST)
	public String validarUsuario(@RequestParam("login") String login,
			@RequestParam("senha") String senha, HttpServletRequest request, Model model) {

		String redirect = "login";

		usuarios = usuarioService.listarUsuarios();

		for (Usuario u : usuarios) {

			if (login.equals(u.getLogin()) && senha.equals(u.getSenha())) {

				redirect = "listaProdutos";

				usuario = u;

				//Adiciona um usuário a session.
				HttpSession session = request.getSession();
				session.setAttribute("usuarioNome", usuario.getLogin());
				 
				String caller = (String) session.getAttribute("caller");
 
				
				if (caller != null) {
					if (caller.equals("carrinho")) {
						return "redirect:checkOutPage.html";
					}
				}


			}

		}
 
		return "redirect:" + redirect + ".html";
	}
	
	/**
	 * Editar informações do usuário.
	 */
	@RequestMapping(value = "/editarUsuario", method = RequestMethod.GET)
	public ModelAndView editarUsuario(@RequestParam("usuarioId") String id) {

		Long produtoId = Long.parseLong(id);

		for (Usuario u : usuarios) {

			if (produtoId.equals(u.getId())) {

				usuario = u;
			}

		}

		return new ModelAndView("usuarioPage", "usuario", usuario);
	}

	/**
	 * Mostra o formulário de novo usuário.
	 */
	@RequestMapping("/User/usuarioPage")
	public ModelAndView formUsuario(Model uiModel) {

		return new ModelAndView("user/usuarioPage", "usuario", new Usuario());

	}

	/**
	 * Carrega as informações do uruário.
	 */
	@RequestMapping("/User/perfil")
	public ModelAndView perfilUsuario(Model uiModel) {

		uiModel.addAttribute("active", "perfil");
		return new ModelAndView("user/usuarioPage", "usuario", usuario);

	}

	/**
	 * Faz o log-out e invalida a session do usuário.
	 */
	@RequestMapping("/User/logout")
	public String logoutUsuario(HttpServletRequest request) {

		HttpSession session = request.getSession();

		session.invalidate();

		return "redirect:listaProdutos.html";

	}

}