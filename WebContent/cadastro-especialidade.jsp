 
<%@page import="com.sus.web.jdbc.EspecialidadeControllerServlet"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@include file="includes/menu.jsp" %>
	 
  <div class="container">
     <h3>Cadastro de Especialidades</h3>   
  </div>
	
	<div class="container"> 	
		<form action="EspecialidadeControllerServlet" method="POST">		
			<input type="hidden" name="command" value="ADD" />			
			<table class="table">
				<tbody>
					<tr>
						<td><label>Especialidade:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="nomeEspecialidade" placeholder="Especialidade" maxlength="30" required/></td>
					</tr>						
					<tr>					
						<td><label></label></td>
						<td><button type="submit" value="Salvar" class="btn btn-success save" />
						 <span class="glyphicon btn-glyphicon glyphicon-ok img-circle text-success"></span>Salvar</button>
						</td>
					</tr>					
				</tbody>
			</table>								
		</form>				
		<p>
			<a  class="btn btn-info" href="EspecialidadeControllerServlet">
			<span class="glyphicon btn-glyphicon  glyphicon-chevron-left img-circle text-info"></span>Voltar à Lista</a>
		</p>
	</div>
</body>

</html>











