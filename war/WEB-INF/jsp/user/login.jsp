<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet"
	href="/resources/css/bootstrap.min.css" />
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>

<title>Hello GAE</title>
</head>

<body>

	<div class="container">

		<div class="jumbotron" style="text-align: center;">
			<h1>Login de Cliente</h1>
			<br /> <br />
			<form class="navbar-form navbar-center" action="validarUsuario.html"
				method="post">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Login"
						name="login" /> <input type="password" class="form-control"
						placeholder="Senha" name="senha" />
				</div>
				<button type="submit" class="btn btn-default">Entrar</button>
			</form>
			<br /> <br />

			<p>
				Novo no Sistema? <a href="usuarioPage.html">Inscreva-se agora</a>
			</p>
		</div>

	</div>

	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

</body>


</html>