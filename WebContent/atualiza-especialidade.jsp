<%@include file="includes/menu.jsp" %>

 <div class="container">
  <h3>Atualizar Especialidade</h3> 
</div>
	<div class="container"> 
		<form action="EspecialidadeControllerServlet" method="POST">		
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="especialidadeId" value="${ESPECIALIDADE.codEspecialidade}" />			
			<table class="table">
				<tbody>				
					<tr>
						<td><label>Nome:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="nomeEspecialidade" 
								   value="${ESPECIALIDADE.nomeEspecialidade}"
								   maxlength="30" /></td>
					</tr>									
					<tr>
						<td><label></label></td>
						<td>
						<button type="submit" value="Salvar" class=" btn btn-success save" />
						<span class="glyphicon btn-glyphicon glyphicon-ok img-circle text-success"></span>Salvar</button>
						</td>
					</tr>					
				</tbody>
			</table>
		</form>		
		<p>
			<a href="EspecialidadeControllerServlet" class="btn btn-info" >
			<span class="glyphicon btn-glyphicon  glyphicon-chevron-left img-circle text-info"></span>Voltar à Lista</a>
		</p>
	</div>
</body>
</html>











