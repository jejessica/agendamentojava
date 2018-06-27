<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@include file="includes/menu.jsp" %>
<c:choose>
   <c:when test="${ds_Usuario==('Admin')}">	
 <div class="container">
  <h3>Lista Usuários</h3> 
</div>
	<div class="container">					
			<table class="table">				
				<tr>
					<th>Nome</th>
					<th>Login</th>
					<th>Senha</th>
					<th>Descrição</th>
					<th></th>														
				</tr>				
				<c:forEach var="tempUsuario" items="${USUARIO_LIST}">
					<c:url var="tempLink" value="UsuarioControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="usuarioId" value="${tempUsuario.codUsuario}" />
					</c:url>
					<c:url var="deleteLink" value="UsuarioControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="usuarioId" value="${tempUsuario.codUsuario}" />
					</c:url>											
					<tr>
						<td> ${tempUsuario.nomeUsuario} </td>
						<td> ${tempUsuario.loginUsuario} </td>
						<td> ${tempUsuario.senhaUsuario} </td>
						<td> ${tempUsuario.dsUsuario} </td>				
						<td> 					
						<a href="${tempLink}" class="btn btn-warning"  title="Atualizar usuário">
						<span class="glyphicon btn-glyphicon glyphicon-pencil img-circle text-warning"></span>Editar</a> 	 
						<a type="button" href="${deleteLink}" class="btn btn-danger">
						<span class="glyphicon btn-glyphicon glyphicon-trash img-circle text-danger"></span>Excluir</a>					
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:when>
	  <c:when test="${ds_Usuario!=('Admin')}">	
	  <div class="container">
    	<div class="alert alert-danger">
  			<strong>Erro!</strong> Você não possui autorização para acessar essa página.
		</div>
	</div>
</c:when>
</c:choose>
</body>
</html>








