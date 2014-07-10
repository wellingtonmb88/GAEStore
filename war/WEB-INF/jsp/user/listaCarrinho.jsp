<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="jumbotron">
		<c:if test="${not empty carrinho}">
			<h3>Seu Carrinho de Compras!</h3>

			<table class="table table-hover table-bordered"
				style="text-align: center; background-color: white;">
				<tr class="info">
					<td>Nome</td>
					<td>Descrição</td>
					<td>Quantidade <c:if test="${not empty erroQuantidade}">
							<h5 style="color: red; text-align: center;">Valor informado
								inválido!</h5>
						</c:if>
					</td>
					<td>Preço</td>
					<td>Total</td>
					<td></td>

				</tr>
				<c:forEach var="produtos" items="${carrinho}">

					<tr>
						<td>${produtos.produto.nome}</td>
						<td>${produtos.produto.descricao}</td>

						<td>
							<form action="updateProdutoCarrinho.html">
								<input type="text" value="${produtos.quantidade}"
									name="quantidade" size="2px" /> <input type="hidden"
									value="${produtos.produto.id}" name="id" /> <input
									type="submit" value="Atualizar" />

							</form>
						</td>
						<td><fmt:formatNumber value="${produtos.produto.preco}"
								type="currency" minFractionDigits="2" /></td>
						<td><fmt:formatNumber value="${produtos.total}"
								type="currency" minFractionDigits="2" /></td>

						<td><a
							href="deletarProdutoCarrinho.html?produtoId=${produtos.produto.id}">Deletar</a>
						</td>

					</tr>
				</c:forEach>



			</table>
			<table class=" table table-bordered "
				style="margin-left: 75%; width: 15%; text-align: center; background-color: white;">

				<tr class="info">
					<td>Total Compra</td>
				</tr>

				<tr>
					<td><fmt:formatNumber value="${totalFinal}" type="currency"
							minFractionDigits="2" /></td>
				</tr>
			</table>
			<br />

			<a href="listaProdutos.html" class="btn btn-info">Continuar
				Compras!</a>

			<a href="checkOutPage.html" style="margin-left: 70%;"
				class="btn btn-success">Check - Out !</a>
		</c:if>

		<c:if test="${empty carrinho }">
			<h3 class="text-danger">Seu Carrinho de Compras está Vazio!</h3>
		</c:if>
	</div>
</body>
</html>