<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="header">

	<c:if test="${not empty usuarioNome}">
		<h4 class="text-success">Bem vindo ${usuarioNome} !</h4>
	</c:if>

	<ul class="nav nav-pills pull-right">
		<li class="${active == 'listaProdutos' ? 'active' : ''  }"><a
			href="listaProdutos.html">Produtos</a></li>
		<li class="${active == 'listaCarrinho' ? 'active' : ''  }"><a
			href="listaCarrinho.html">Carrinho</a></li>
		<c:if test="${not empty usuarioNome}">
			<c:if test="${not empty carrinhoCheckOut}">
				<li class="${active == 'listaCheckOut' ? 'active' : ''  }"><a
					href="checkOutPage.html">Check-Out</a></li>
			</c:if>
			<li class="${active == 'perfil' ? 'active' : ''  }"><a
				href="perfil.html">Perfil</a></li>
			<li><a href="logout.html">LogOut</a></li>
		</c:if>
		<c:if test="${empty usuarioNome}">
			<li><a href="login.html">Login</a></li>
		</c:if>

	</ul>
	<h3 class="text-muted">Cloud Store</h3>
</div>

<br />
<hr>


