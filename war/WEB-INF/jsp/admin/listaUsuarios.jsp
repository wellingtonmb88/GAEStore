<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<td>E-Mail</td> 
			<td>Ação</td>
		</tr>
		<c:forEach var="usuario" items="${usuarios}">
			<tr>
				<td>${usuario.login}</td>
				<td>${usuario.email}</td> 
				<td><a href="editarAdmin.html?usuarioId=${usuario.id}">Editar</a>
					<a href="deletarUsuario.html?usuarioId=${usuario.id}">Deletar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:out value="${nome} - ${nome2} - ${nome3}"></c:out>
	
	<a href="usuarioPage.html">Adicionar novo usuário</a>
</body>
</html>