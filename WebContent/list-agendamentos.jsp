<%@ page import="java.sql.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@include file="includes/menu.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
 <div class="container">
  <h3>Agendamentos</h3> 
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
			</form>								
			<br/><br/><br/>
			<thead>
				<tr>
					<th>Código</th>
					<th>Data</th>
					<th>Hora</th>
					<th>Paciente</th>
					<th>Médico</th>
					<th>Especialidade</th>				
					<th>Status</th>
					<th>Ações</th>					
				</tr>	
				</thead>
				<tbody id="myTable">		
				<c:forEach var="tempAgendamento" items="${AGENDAMENTO_LIST}">					
					<c:url var="tempLink" value="AgendamentoControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="AgendamentoId" value="${tempAgendamento.codAgendamento}" />
					</c:url>
					<c:url var="deleteLink" value="AgendamentoControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="AgendamentoId" value="${tempAgendamento.codAgendamento}" />
					</c:url>																											
					<tr>
						<td> ${tempAgendamento.codAgendamento} </td>						
						<td>${tempAgendamento.dtAgendamento} </td>								
						<td> ${tempAgendamento.horaAgendamento} </td>						
						<td>
						<c:forEach items="${PACIENTES_FORM}" var="tempPac" >
							<c:if test="${tempAgendamento.pacienteAgendamento == tempPac.codPaciente }">
								${tempPac.nomePaciente}
							</c:if>
						</c:forEach>
						</td>											
						<td>
						<c:forEach items="${MEDICOS_FORM}" var="tempMed" >
							<c:if test="${tempAgendamento.medicoAgendamento == tempMed.codMedico }">
								${tempMed.nomeMedico}
							</c:if>
						</c:forEach>
						</td>									
						<td>
						<c:forEach items="${ESPECIALIDADES_FORM}" var="tempEspec" >
							<c:if test="${tempAgendamento.especialidadeAgendamento == tempEspec.codEspecialidade }">
								${tempEspec.nomeEspecialidade}
							</c:if>
						</c:forEach>
						</td>																
						<td> ${tempAgendamento.statusAgendamento} </td>				
						<td> 
								<a class="btn btn-warning" href="${tempLink}">
								<span class="glyphicon btn-glyphicon glyphicon-pencil img-circle text-danger"></span>Editar</a>	
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
<%@include file="includes/footer.jsp" %>








