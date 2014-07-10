package com.cloudstore88.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cloudstore88.model.Carrinho;
import com.cloudstore88.model.Produto;
import com.cloudstore88.service.ProdutoService;

@Controller
@SessionAttributes
public class CarrinhoController {

	//Variáveis globais.
	public static List<Carrinho> carrinho = new ArrayList<>();
	private boolean erroQuantidade = false;
	private ProdutoService produtoService = new ProdutoService();

	/**
	 * Adiciona produto ao carrinho, se existir o produto no carrinho apenas incrementa sua quantidade
	 *  se não adiciona um novo.
	 */
	@RequestMapping(value = "/User/addProdutoCarrinho", method = RequestMethod.GET)
	public String addProduto(@RequestParam("produtoId") String id,
			Model model, HttpServletRequest request) {

		
		Long produtoId = Long.parseLong(id);
		Produto produto = null;
		int quantProdutos = 0;
		
		List<Produto> produtos = produtoService.listarProdutos();

		//Encontro o produto na lista de produtos pelo Id.
		for (Produto p : produtos) {

			if (produtoId.equals(p.getId())) {

				produto = p;
			}

		}
 
		Carrinho item = new Carrinho(produto);

		boolean hasItem = false;

		//Incrementa quantidade do produto.
		for (Carrinho itemDoPedido : carrinho) {

			if (itemDoPedido.getProduto().getId().equals(produtoId)) {
				itemDoPedido.incrementarQuantidade();
				hasItem = true;
			}
			quantProdutos += itemDoPedido.getQuantidade(); 
		}

		//Se o carrinho estiver vazio adiciona um produto novo 
		// e incrementa o número de produtos do carrinho.
		if (carrinho.isEmpty()) {
			quantProdutos++;
			carrinho.add(item); 
		} 
		else {
			//Adiciona produto novo no carrinho caso ele não exista.
			if (!hasItem) {
				quantProdutos++;
				carrinho.add(item); 
			}
		}
		
		//Adiciona um atributo na session com o número de produtos no carrinho.
		HttpSession session = request.getSession();
		session.setAttribute("carrinhoSize", quantProdutos);
 
		 
		 return "redirect:listaProdutos.html";
	}

	/**
	 * Deletar produto do carrinho.
	 */
	
	@RequestMapping(value = "/User/deletarProdutoCarrinho", method = RequestMethod.GET)
	public String deletarProduto(@RequestParam("produtoId") String id,
			Model model, HttpServletRequest request) {

		Long produtoId = Long.parseLong(id);
		int quantProdutos = 0;
		Carrinho item = null;

		//Encontro o produto na lista de produtos pelo Id.
		//E atualiza o número de produtos no carrinho.
		for (Carrinho itemDoPedido : carrinho) {

			if (itemDoPedido.getProduto().getId().equals(produtoId)) {

				item = itemDoPedido;
			}

			quantProdutos +=itemDoPedido.getQuantidade();
		}

		//Adiciona um atributo na session com o número de produtos no carrinho.
		HttpSession session = request.getSession();
		session.setAttribute("carrinhoSize", quantProdutos);
		
		//Adiciona atributo a página.
		model.addAttribute("active", "listaCarrinho");

		//Exclui o produto do carrinho.
		carrinho.remove(item);

		return "redirect:listaCarrinho.html";
	}

	/**
	 * Atualiza quatidade de produtos no carrinho.
	 */
	@RequestMapping(value = "/User/updateProdutoCarrinho", method = RequestMethod.GET)
	public String updateProduto(HttpServletRequest req, Model model) {

		erroQuantidade = false;

		Long produtoId = Long.parseLong(req.getParameter("id"));
		String qnt = req.getParameter("quantidade").trim();
		int quantidade = 1;

		//Tratamento de erro caso o usuário digite valores inválidos no campo número de produtos.
		try {
			quantidade = Integer.valueOf(qnt);

			if (quantidade > 0) {

				int count = 0;

				for (Carrinho itemDoPedido : carrinho) {

					if (itemDoPedido.getProduto().getId().equals(produtoId)) {

						itemDoPedido.setQuantidade(quantidade);

						carrinho.set(count, itemDoPedido);
					}

					count++;
				}
			} else {

				erroQuantidade = true;
			}

		} catch (NumberFormatException | NullPointerException e) {
			erroQuantidade = true;
		}

		model.addAttribute("active", "listaCarrinho");

		return "redirect:listaCarrinho.html";
	}

	/**
	 * Mostra os produtos do carrinho.
	 */
	@RequestMapping("/User/listaCarrinho")
	public ModelAndView showProdutos(Model model, HttpServletRequest request) {

		float total = 0;

		int quantProdutos = 0;
		
		for (Carrinho itemDoPedido : carrinho) {

			total += itemDoPedido.getTotal(); 
			quantProdutos +=itemDoPedido.getQuantidade();
		}

		//Envia o valor total da compra.
		model.addAttribute("totalFinal", total);

		model.addAttribute("active", "listaCarrinho");
		
		HttpSession session = request.getSession();
		session.setAttribute("caller", "carrinho"); 
		//Adiciona um atributo na session com o número de produtos no carrinho.
		session.setAttribute("carrinhoSize", quantProdutos);

		//Valida se há erros na atualização do número de produtos
		if (erroQuantidade)
			model.addAttribute("erroQuantidade", "erro");

		return new ModelAndView("user/listaCarrinho", "carrinho", carrinho);
	}

	/**
	 * Mostra produtos na lista do checkOut.
	 * Mostra produtos recomendados de acordo com os produtos na lista de produtos do checkOut.
	 */
	@RequestMapping("/User/checkOutPage")
	public ModelAndView showCheckOut(Model model) {

		float total = 0;

		List<Produto> listaProdutosCarrinho = new ArrayList<>();

		List<Produto> listaProdutosRecomendados = new ArrayList<>();

		for (Carrinho itemDoPedido : carrinho) {
			listaProdutosCarrinho.add(itemDoPedido.getProduto());
			total += itemDoPedido.getTotal();
		}

		for (Produto itemDoPedido : listaProdutosCarrinho) {

			for (Produto produto : produtoService.listarProdutos()) {

				// Adiciona apenas produtos da mesma categoria com ID´s
				// diferentes
				if (((produto.getCategoria().toUpperCase()
						.contains(itemDoPedido.getCategoria().toUpperCase())) || 
						(produto.getCategoria().toUpperCase()
						 .endsWith(itemDoPedido.getCategoria().toUpperCase()))) && 
					    (!checarIdProduto(produto.getId()))) {

					if (!listaProdutosRecomendados.contains(produto)) {

						listaProdutosRecomendados.add(produto);
					}

				}

			}

		}

		//Envia lista de produtos recomendados.
		model.addAttribute("produtosRecomendados", listaProdutosRecomendados);

		//Envia o valor total da compra.
		model.addAttribute("totalFinal", total);

		model.addAttribute("active", "listaCheckOut");

		//Valida se há erros na atualização do número de produtos
		if (erroQuantidade)
			model.addAttribute("erroQuantidade", "erro");
		  
		return new ModelAndView("user/listaCheckOut", "carrinhoCheckOut",
				carrinho);
	}

	//Verifica se já existe um produto específico
	private boolean checarIdProduto(Long id) {

		for (Carrinho itemDoPedido : carrinho) {

			if (itemDoPedido.getProduto().getId().equals(id)) {

				return true;
			}
		}

		return false;
	}
}