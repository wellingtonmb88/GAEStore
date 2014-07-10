<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="header">

	<c:if test="${not empty usuarioNome}">
		<h4 class="text-success">Bem vindo ${usuarioNome} !</h4>
	</c:if>
	<ul class="nav nav-pills pull-right">
		<li class="${active == 'listaUsuarios' ? 'active' : '' }"><a
			href="listaUsuarios.html">Usuários</a></li>
		<li class="${active == 'listaProdutos' ? 'active' : '' }"><a
			href="listaProdutos.html">Produtos</a></li>
		<li class="${active == 'perfil' ? 'active' : ''  }"><a
			href="perfil.html">Perfil</a></li>

		<li><a href="logout.html">Logout</a></li>

	</ul>

	<h3 class="text-muted">Cloud Store</h3>
</div>

<br />
<hr>
