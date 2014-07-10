<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="jumbotron">

		<c:if test="${carrinhoSize > 0 }">
			<c:if test="${not empty usuarioNome }">
				<a href="checkOutPage.html" style="margin-left: 90%;"
					class="btn btn-success">Check - Out !</a>
			</c:if>
			<h3 style="margin-left: 70%;" class="text-info">

				<c:if test="${carrinhoSize == 1}">Carrinho com
				<c:out value="${carrinhoSize}" />
				Produto!</c:if>
				<c:if test="${carrinhoSize > 1}">Carrinho com
				<c:out value="${carrinhoSize}" />
				Produtos!</c:if>

			</h3>

		</c:if>
		<c:if test="${carrinhoSize == 0 || empty carrinhoSize}">
			<h3 style="text-align: right;" class="text-info">Carrinho vazio!</h3>
		</c:if>

		<table class="table table-hover table-bordered"
			style="background-color: white; text-align: center;">
			<tr class="info">
				<td>Nome</td>
				<td>Descrição</td>
				<!-- 				<td>Estoque</td> -->
				<td>Preço</td>
				<td>Ação</td>
			</tr>
			<c:forEach var="produto" items="${produtos}">
				<tr>
					<td>${produto.nome}</td>
					<td>${produto.descricao}</td>
					<%-- <td>${produto.quantidade}</td> --%>
					<td><fmt:formatNumber value="${produto.preco}" type="currency"
							minFractionDigits="2" /></td>
					<td><a href="addProdutoCarrinho.html?produtoId=${produto.id}"
						class="btn btn-primary"> Add Carrinho</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>