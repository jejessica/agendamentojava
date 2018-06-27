<%@include file="includes/menu.jsp" %>

 <div class="container">
  <h3>Atualizar Usuário</h3> 
</div>	
	<div class="container">		
		<form action="UsuarioControllerServlet" method="GET">		
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="usuarioId" value="${USUARIO.codUsuario}" />			
			<table class="table">
				<tbody>
					<tr>
						<td><label>Nome:</label></td>
						<td><input class="form-control" style="width:50%" type="text" name="nomeUsuario" maxlength="20" 
								   value="${USUARIO.nomeUsuario}" /></td>
					</tr>					
						<tr>
						<td><label>Login:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="loginUsuario" maxlength="10"
								   value="${USUARIO.loginUsuario}" /></td>
					</tr>						
						<tr>
						<td><label>Senha:</label></td>
						<td><input class="form-control" style="width:30%" type="password" name="senhaUsuario" maxlength="10"
								   value="${USUARIO.senhaUsuario}" /></td>
					</tr>						
						<tr>
						<td><label>Descrição:</label></td>
						<td><select class="form-control" style="width:30%" name="dsUsuario" >
							<option>Comum</option>
							<option>Admin</option>												
							</select>						
						</tr>										
					<tr>
						<td><label></label></td>
						<td>
							<button type="submit" value="Salvar" class="btn btn-success save" />
							<span class="glyphicon btn-glyphicon glyphicon-ok img-circle text-success"></span>Salvar</button>
						</td>
					</tr>					
				</tbody>
			</table>
		</form>			
		<p>
			<a href="UsuarioControllerServlet" class="btn btn-info">
			<span class="glyphicon btn-glyphicon  glyphicon-chevron-left img-circle text-info"></span>Voltar à Lista</a>
		</p>
	</div>
</body>
</html>











