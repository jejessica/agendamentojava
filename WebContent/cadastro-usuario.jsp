<%@include file="includes/menu.jsp" %>
<c:choose>
   <c:when test="${ds_Usuario==('Admin')}">	
	<div class="container">
		<h3>Cadastro de Usuário</h3>	
	</div>	
<div class="container">	
	<form action="UsuarioControllerServlet" method="GET">	
			<input type="hidden" name="command" value="ADD" />		
		<table class="table">
			<tbody>
				<tr>
					<td><label>Nome:</label></td>
					<td><input class="form-control" style="width:50%" type="text" name="nomeUsuario" placeholder="Nome usuário" maxlength="20" required/></td>
				</tr>
				<tr>
					<td><label>Login:</label></td>
					<td><input class="form-control" style="width:30%" type="text" name="loginUsuario" placeholder="Login" maxlength="10" required/></td>
				</tr>				
				<tr>
					<td><label>Senha:</label></td>
					<td><input class="form-control" style="width:30%" type="password" name="senhaUsuario" placeholder="*****" maxlength="10" required/></td>
				</tr>
				<tr>
					<td><label>Descrição:</label></td>
						<td><select class="form-control" style="width:30%" name="dsUsuario" required>
								<option>Atendente</option>
								<option>Admin</option>												
							</select>
						</td>
				</tr>
				<tr>					
				<tr>
					<td><label></label></td> 
						<td><button type="submit" class="btn btn-success" value="Salvar" class="save" />
						 <span class="glyphicon btn-glyphicon glyphicon-ok img-circle text-success"></span>Salvar</button>
						</td>
				</tr>
					
			</tbody>
		</table>
	</form>		
		<p><a class="btn btn-info" href="UsuarioControllerServlet">
		<span class="glyphicon btn-glyphicon  glyphicon-chevron-left img-circle text-info"></span>Voltar à Lista</a></p>
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










