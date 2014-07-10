<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<form:form class="form-horizontal" action="addUsuario.html"
				commandName="usuario">
				<fieldset>

					<!-- Form Name -->
					<legend> Cadastro do Usuário </legend>

					<!-- Hidden input-->
					<input id="id" name="id" type="hidden" value="${usuario.id}" />

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="login">Login</label>
						<div class="col-md-4">
							<form:input path="login" placeholder="Login"
								class="form-control input-md" value="${usuario.login}" />
							<form:errors path="login" cssClass="error" />
						</div>
					</div>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="email">E-Mail</label>
						<div class="col-md-4">
							<form:input path="email" type="email" placeholder="E-Mail"
								class="form-control input-md" value="${usuario.email}" />
							<form:errors path="email" cssClass="error" />

						</div>
					</div>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="senha">Senha</label>
						<div class="col-md-4">
							<form:input path="senha" placeholder="Senha"
								class="form-control input-md" value="${usuario.senha}" />
							<form:errors path="senha" cssClass="error" />


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