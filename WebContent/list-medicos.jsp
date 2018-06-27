<%@include file="includes/menu.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <div class="container">
  <h3>Lista de Médicos</h3> 
</div>	
	<div class="container">				
				<div class="row">
			        <div class="col-sm-6 col-sm-offset-3">
			            <div id="imaginary_container"> 
			                <div class="input-group stylish-input-group">
			                    <input id="paciente" type="text" class="form-control"  placeholder="Pesquisar..." >
			                    <span class="input-group-addon">
			                    <button id="paciente" style="border:none; background-color: transparent;">			                       
			                            <span class="glyphicon glyphicon-search"></span>
			                    </button>        			                       
			                    </span>
			                </div>
			            </div>
			        </div>
				</div><br/>
			<form action="MedicoControllerServlet" method="GET">
			</form>
			<table class="table">
				<tr>
					<thead>
					<th>CRM</th>
					<th>Nome</th>
					<th>Especialidade</th>
					<th>Telefone</th>
					<th></th>													
				</tr>
				</thead>
				<tbody id="myTable">
				<c:forEach var="tempMedico" items="${MEDICO_LIST}">				
					<c:url var="tempLink" value="MedicoControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="medicoId" value="${tempMedico.codMedico}" />
					</c:url>
					<c:url var="deleteLink" value="MedicoControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="medicoId" value="${tempMedico.codMedico}" />
					</c:url>																	
					<tr>
						<td> ${tempMedico.crmMedico} </td>
						<td> ${tempMedico.nomeMedico} </td>
						<td>
						<c:forEach items="${ESPECIALIDADES_FORM}" var="tempEspec" >
							<c:if test="${tempMedico.especialidadeMedico == tempEspec.codEspecialidade }">
								${tempEspec.nomeEspecialidade}
							</c:if>
						</c:forEach>
						</td>								
						<td> ${tempMedico.telefoneMedico} </td>									
						<td> 
							<a class="btn btn-warning" href="${tempLink}">
							<span class="glyphicon btn-glyphicon glyphicon-pencil img-circle text-warning"></span>Atualizar</a> 
						<c:choose>
   							<c:when test="${ds_Usuario==('Admin')}">									  
								<a class="btn btn-danger" href="${deleteLink}">
								<span class="glyphicon btn-glyphicon glyphicon-trash img-circle text-danger"></span>Apagar</a>	
							</c:when>
						</c:choose>	
						</td>
					</tr>			
				</c:forEach>
				</tbody>
			</table>	
		</div>	
	</div>
<%@include file="includes/footer.jsp" %>>









