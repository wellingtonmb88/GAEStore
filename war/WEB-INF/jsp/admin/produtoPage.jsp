<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="container">

		<div class="jumbotron" style="text-align: center;">

			<form:form class="form-horizontal" action="addProduto.html"
				commandName="produto">
				<fieldset>

					<!-- Form Name -->
					<legend>
						<c:if test="${empty produto}">
							Novo Produto
						</c:if>
						<c:if test="${not empty produto}">
							Editar Produto
						</c:if>

					</legend>

					<!-- Hidden input-->
					<input id="id" name="id" type="hidden" value="${produto.id}">


					<!-- Text input-->
					<div class="form-group">

						<label class="col-md-4 control-label" for="nome">Nome</label>
						<div class="col-md-4">

							<form:input path="nome" placeholder="Nome"
								class="form-control input-md" value="${produto.nome}" />
							<form:errors path="nome" cssClass="error" />
						</div>
					</div>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="descricao">Descrição</label>
						<div class="col-md-4">
							<form:input id="descricao" path="descricao"
								placeholder="Descrição" class="form-control input-md"
								value="${produto.descricao}" />

							<form:errors path="descricao" cssClass="error" />

						</div>
					</div>


					<!-- Select -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="selectbasic">Categoria</label>
						<div class="col-md-4">
							<form:select path="categoria" class="form-control">
								<option value="0">Eletrônicos</option>
								<option value="1">Acessórios para eletrônicos</option>
								<option value="2">Smartphones/Tablets</option>
								<option value="3">Acessórios para Smartphones/Tablets</option>
							</form:select>
						</div>
					</div>


					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="quantidade">Quantidade</label>
						<div class="col-md-4">
							<form:input path="quantidade" placeholder="Quantidade"
								type="number" class="form-control input-md"
								value="${produto.quantidade}" />
							<form:errors path="quantidade" cssClass="error" />

						</div>
					</div>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="preco">Preço</label>
						<div class="col-md-4"> 
							<form:input path="preco" placeholder="Preço" type="number" step="0.01"
								class="form-control input-md" value="${produto.preco}" />
								 
							<form:errors path="preco" cssClass="error" id="errorsPreco" />
						</div>
					</div>

					<!-- Button -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="salvar"></label>
						<div class="col-md-4">
							<button type="submit" id="salvar" name="salvar"
								class="btn btn-primary">Salvar</button>
						</div>
					</div>

				</fieldset>
			</form:form>

			<br /> <br />

		</div>

	</div>

	<!-- /container -->

</body>
</html>