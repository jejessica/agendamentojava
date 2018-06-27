<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="includes/menu.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

 <div class="container">
  <h3>Lista de Paciente</h3> 
</div>		
	<div class="container">
 		<table class="table">					
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
				</div>
			<form action="PacienteControllerServlet" method="GET">										
			</form>	
			<div class="row">	
			<table class="table">
			<thead>
				<tr>		
					<!-- <th>Cód.</th>-->
					<th class="col-md-6">Nome</th>
					<th class="col-md-2	">CPF</th>
					<th class="col-md-2">Cidade</th>
					<th>E-Mail</th>
					<th class="col-md-3">Telefone</th>
					<th>Nascimento</th>	
					<th class="col-md-1">Sexo</th>
					<!-- -><th>Nr. Sus</th>		-->										
					
				</tr>
				</thead>
					<tbody id="myTable">
				<c:forEach var="tempPaciente" items="${PACIENTE_LIST}">			
					<c:url var="tempLink" value="PacienteControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="pacienteId" value="${tempPaciente.codPaciente}" />
					</c:url>
					<c:url var="deleteLink" value="PacienteControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="pacienteId" value="${tempPaciente.codPaciente}" />
					</c:url>													
					<tr>
						<!-- <td> ${tempPaciente.codPaciente} </td>-->
						<td> ${tempPaciente.nomePaciente} </td>
						<td> ${tempPaciente.cpfPaciente} </td>
						<td>
						<c:forEach items="${CIDADES_FORM}" var="tempCid" >
							<c:if test="${tempPaciente.cidadePaciente == tempCid.codCidade }">
								${tempCid.nomeCidade}
							</c:if>
						</c:forEach>
						</td>									
						<td> ${tempPaciente.emailPaciente} </td>
						<td> ${tempPaciente.telefonePaciente} </td>
						<td> ${tempPaciente.datanascPaciente} </td>	
						<td> ${tempPaciente.sexoPaciente} </td>	
						<!--<td> ${tempPaciente.cartaoPaciente} </td>-->									
						<td> 
							<a class="btn btn-warning" href="${tempLink}">
							<span class="glyphicon btn-glyphicon glyphicon-pencil img-circle text-warning"></span>Atualizar</a> 
						</td>
						<td>
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
</div>
<%@include file="includes/footer.jsp" %>









