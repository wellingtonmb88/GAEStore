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
 
	<table class="table table-hover table-bordered" style="text-align: center; background-color: white;">
		<tr class="info">
			<td>Nome</td>
			<td>Descrição</td>
			<td>Categoria</td>
			<td>Estoque</td>
			<td>Preço</td>
			<td>Ação</td>
		</tr>
		<c:forEach var="produto" items="${produtos}">
			<tr>
				<td>${produto.nome}</td>
				<td>${produto.descricao}</td>
				<td>${produto.categoria}</td>
				<td>${produto.quantidade}</td>
				<td><fmt:formatNumber value="${produto.preco}" type="currency"
							minFractionDigits="2" /></td>
				<td><a href="editarProduto.html?produtoId=${produto.id}">Editar</a>
					<a href="deletarProduto.html?produtoId=${produto.id}">Deletar</a> 					
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="produtoPage.html">Adicionar novo produto</a>
</body>
</html>