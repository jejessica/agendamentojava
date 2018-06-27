<%@include file="includes/menu.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <div class="container">
  <h3>Atualizar Agendamento</h3> 
</div> 	
	<div class="container">		
		<form action="AgendamentoControllerServlet" method="GET">		
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="AgendamentoId" value="${AGENDAMENTO.codAgendamento}" />			
			<table class="table">
				<tbody>					
					<tr>
						<td><label>Data:</label></td>
						<td><input class="form-control" style="width:35%" id="datefield" type="date" name="dtAgendamento" required 
								   value="${AGENDAMENTO.dtAgendamento}" /></td>
								   
					<script>
						var today = new Date();
						var dd = today.getDate()+1;
						var mm = today.getMonth()+1; //January is 0!
						var yyyy = today.getFullYear();
						 if(dd<10){
						        dd='0'+dd
						    } 
						    if(mm<10){
						        mm='0'+mm
						    } 
		
						today = yyyy+'-'+mm+'-'+dd;
						document.getElementById("datefield").setAttribute("min", today);
					</script>
					
					</tr>									
					<tr>
						<td><label>Hora:</label></td>
						<td><input class="form-control" style="width:35%" type="time" name="horaAgendamento" min="07:00" max="16:00" step="1800" required
								   value="${AGENDAMENTO.horaAgendamento}" /></td>
					</tr>						
					<tr>
						<td><label>Paciente:</label></td>
						<td>
						 <input class="form-control" type="hidden" name="pacienteAgendamento" 
								   value="${AGENDAMENTO.pacienteAgendamento}" /> 
								   
						<c:forEach items="${PACIENTES_FORM}" var="tempPac" >
							<c:if test="${AGENDAMENTO.pacienteAgendamento == tempPac.codPaciente }">
								${tempPac.nomePaciente}
							</c:if>
						</c:forEach>
								   </td>
					</tr>				
					<tr>
						<td><label>Medico:</label></td>
						<td>
						<input class="form-control" type="hidden" name="medicoAgendamento" 
								   value="${AGENDAMENTO.medicoAgendamento}" />
								   
						<c:forEach items="${MEDICOS_FORM}" var="tempMed" >
							<c:if test="${AGENDAMENTO.medicoAgendamento == tempMed.codMedico }">
								${tempMed.nomeMedico}
							</c:if>
						</c:forEach>
								</td>
					</tr>						
					<tr>
						<td><label>Especialidade:</label></td>
						<td>
						<input class="form-control" type="hidden" name="especialidadeAgendamento" maxlength="11" 
								   value="${AGENDAMENTO.especialidadeAgendamento}" />
								   
						<c:forEach items="${ESPECIALIDADES_FORM}" var="tempEspec" >
							<c:if test="${AGENDAMENTO.especialidadeAgendamento == tempEspec.codEspecialidade }">
								${tempEspec.nomeEspecialidade}
							</c:if>
						</c:forEach>
						</td>
					</tr>	
				<tr>
					<td><label>Status :</label></td>
						<td><select class="form-control" style="width:35%" name="statusAgendamento">
 							<option value="Aberto">Aberto</option>
							<option value="Em andamento">Em andamento</option>
							<option value="Finalizado">Finalizado</option> 
							<option value="Cancelado">Cancelado</option>   
				 		</select></td>
		  	   </tr>										
				<tr>
					<td><label></label></td>
						<td><button type="submit" value="Salvar" class="btn btn-success save" />
							<span class="glyphicon btn-glyphicon glyphicon-ok img-circle text-success"></span>Salvar
						</td>
					</tr>					
				</tbody>
			</table>
		</form>		
		<div style="clear: both;"></div>		
		<p>
			<a href="AgendamentoControllerServlet" class="btn btn-info">
			<span class="glyphicon btn-glyphicon  glyphicon-chevron-left img-circle text-info"></span>Voltar à Lista</a>
		</p>
	</div>
</body>
</html>











