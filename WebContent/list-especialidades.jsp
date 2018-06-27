<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="includes/menu.jsp" %>

 <div class="container">
  <h3>Lista de Especialidade</h3> 
</div>
	<div class="container"> 		
			<table class="table">			
				<tr>
					<th>Código</th>
					<th>Nome</th>
					<th></th>
				</tr>				
				<c:forEach var="tempEspecialidade" items="${ESPECIALIDADE_LIST}">					
					<c:url var="tempLink" value="EspecialidadeControllerServlet" >
						<c:param name="command" value="LOAD" />
						<c:param name="especialidadeId" value="${tempEspecialidade.codEspecialidade}" />
					</c:url>
					<c:url var="deleteLink" value="EspecialidadeControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="especialidadeId" value="${tempEspecialidade.codEspecialidade}" />
					</c:url>																		
					<tr>
						<td> ${tempEspecialidade.codEspecialidade} </td>
						<td> ${tempEspecialidade.nomeEspecialidade} </td>
						<td> 
								<a href="${tempLink}" class="btn btn-warning">
								<span class="glyphicon btn-glyphicon glyphicon-pencil img-circle text-warning"></span>Editar</a> 
						<c:choose>
   							<c:when test="${ds_Usuario==('Admin')}">										   
								<a href="${deleteLink}"	class="btn btn-danger">
								<span class="glyphicon btn-glyphicon glyphicon-trash img-circle text-danger"></span>Apagar</a>	
							</c:when>
						</c:choose>	
						</td>
					</tr>				
				</c:forEach>				
			</table>		
		</div>	
	</div>
<%@include file="includes/footer.jsp" %>







