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

import com.cloudstore88.model.Produto;
import com.cloudstore88.service.ProdutoService;

@Controller
@SessionAttributes
public class ProdutoController {

	//Variáveis
	public static List<Produto> produtos = new ArrayList<>();
	public static String[] categorias = {"Eletrônicos", "Acessórios para eletrônicos", "Smartphones/Tablets",
		"Acessórios para Smartphones/Tablets"};
	private boolean newProduto = false;
	private ProdutoService produtoService = new ProdutoService() ;

/////////////////////////--- ADMIN CONTROLLER ----////////////////////////////////////////////////////////
	/**
	 *Adiciona produto
	 */
	@RequestMapping(value = "/Admin/addProduto", method = RequestMethod.POST)
	public String addProduto(@Valid @ModelAttribute("produto") Produto produto,
			BindingResult result, HttpServletRequest req) {
  
		if(result.hasErrors()){
		 
			return "admin/produtoPage";
		}else{

			int categoria = Integer.parseInt(req.getParameter("categoria"));
			produto.setCategoria(categorias[categoria]);
			System.out.println("Categoria = "+ categorias[categoria]);
			newProduto = true;
			produtoService.salvarProduto(produto);
		} 

		return "redirect:listaProdutos.html";
	}

	/**
	 * Edita produto
	 */
	@RequestMapping(value = "/Admin/editarProduto", method = RequestMethod.GET)
	public ModelAndView editarProduto(@RequestParam("produtoId") String id, Model uiModel) {

		Long produtoId = Long.parseLong(id);
		Produto produto = produtoService.buscaProdutoPorId(produtoId); 

		uiModel.addAttribute("categorias", categorias);
 
		return new ModelAndView("admin/produtoPage", "produto", produto);
	}

	/**
	 * Deleta produto
	 */
	@RequestMapping(value = "/Admin/deletarProduto", method = RequestMethod.GET)
	public String deletarProduto(@RequestParam("produtoId") String id) {

		Long produtoId = Long.parseLong(id);
		Produto produto = produtoService.buscaProdutoPorId(produtoId); 
		
		produtoService.deleteProduto(produto);

		return "redirect:listaProdutos.html";
	}

	/**
	 * Mostra a lista de produtos
	 */
	@RequestMapping(value="/Admin/listaProdutos")
	public ModelAndView showProdutos(HttpServletResponse response, Model uiModel) {

		uiModel.addAttribute("active","listaProdutos");
		
		produtos = produtoService.listarProdutos();
		
		if (newProduto) {
 			response.setIntHeader("Refresh", 2); 
 			newProduto = false; 
		}

		return new ModelAndView("admin/listaProdutos", "produtos", produtos );
	}

	/**
	 * Carrega página de formulário de produto novo.
	 */
	@RequestMapping("/Admin/produtoPage")
	public ModelAndView formProduto(Model uiModel) {

		uiModel.addAttribute("produto", new Produto());
		uiModel.addAttribute("categorias", categorias);
		return new ModelAndView("admin/produtoPage");

	}
	
/////////////////////////--- USUARIO CONTROLLER ----////////////////////////////////////////////////////////
	/**
	 * Mostra a lista de produtos
	 */
	@RequestMapping(value="/User/listaProdutos")
	public ModelAndView showProdutosUser(HttpServletResponse response, Model uiModel, HttpServletRequest request ) {

		uiModel.addAttribute("active","listaProdutos"); 

		//Adiciona a session o número de produtos no carrinho.
		HttpSession session = request.getSession();
		session.setAttribute("carrinhoSize", session.getAttribute("carrinhoSize"));

		return new ModelAndView("user/listaProdutos", "produtos", produtoService.listarProdutos());
	}
 
}