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

	<div class="jumbotron" style="float: left; width: 70%;">
		<c:if test="${not empty carrinhoCheckOut}">
			<h3>Check - Out!</h3>

			<table class="table table-hover table-bordered"
				style="text-align: center;">
				<tr class="info">
					<td>Nome</td>
					<td>Descri��o</td>
					<td>Quantidade <c:if test="${not empty erroQuantidade}">
							<h5 style="color: red; text-align: center;">Valor informado
								inv�lido!</h5>
						</c:if>
					</td>
					<td>Pre�o</td>
					<td>Total</td>
					<td></td>

				</tr>
				<c:forEach var="produtos" items="${carrinhoCheckOut}">

					<tr style="background-color: white;">
						<td>${produtos.produto.nome}</td>
						<td>${produtos.produto.descricao}</td>

						<td>
							<form action="updateCheckOut.html">
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
							href="deletarProdutoCheckOut.html?produtoId=${produtos.produto.id}">Deletar</a>
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
			<a href="listaProdutos.html" class="btn btn-info"
				style="float: left;">Continuar Compras!</a>
			<a href="fecharCheckOut.html" style="float: right;"
				class="btn btn-success">Finalizar Compra!</a>
		</c:if>

		<c:if test="${empty carrinhoCheckOut }">
			<h3 class="text-danger">Seu Carrinho de Compras est� Vazio!</h3>
		</c:if>
	</div>

	<!--  Recomenda��es de compra ao Cliente  -->

	<div style="text-align: center; margin-left: 75%;">
		<c:if test="${not empty produtosRecomendados }">
			<br />
			<hr>
			<h3 class="text-danger">Recomendados</h3>

			<table class="table table-hover table-bordered">
				<tr class="info">
					<td>Nome</td>
					<td>Descri��o</td>
					<td>Pre�o</td>
					<td>A��o</td>
				</tr>
				<c:forEach var="produto" items="${produtosRecomendados}">
					<tr>
						<td>${produto.nome}</td>
						<td>${produto.descricao}</td>
						<td><fmt:formatNumber value="${produto.preco}"
								type="currency" minFractionDigits="2" /></td>
						<td><a href="addProdutoCheckOut.html?produtoId=${produto.id}">Add
								CheckOut</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>

</body>
</html>