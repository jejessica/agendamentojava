<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="includes/menu.jsp" %>

 <div class="container">
  <h3>Atualizar Medico</h3>
	<%-- Script que formata os campos corretamente --%>
	<%-- Requer jquery.mask(.min).js --%>
	<script type="text/javascript">
		$(document).ready(function() {

			$("#rg").mask("999.999.999-W", {
				translation : {
					'W' : {
						pattern : /[X0-9]/
					}
				},
				reverse : true
			})

			$("#cpf").mask("000.000.000-00")
			$("#telefone").mask("(00) 0000-00009")
			$("#telefone").blur(function(event) {
				if ($(this).val().length == 15) {
					$("#telefone").mask("(00) 00000-0009")
				} else {
					$("#telefone").mask("(00) 0000-0009")
				}
			})
		})
	</script>
</div> 
	<div class="container">		
		<form action="MedicoControllerServlet" method="GET">		
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="medicoId" value="${MEDICO.codMedico}" />			
			<table class="table">				<tbody>			
					<tr>
						<td><label>Nome:</label></td>
						<td><input class="form-control" style="width:50%" type="text" name="nomeMedico" maxlength="50" required
								   value="${MEDICO.nomeMedico}" /></td>
					</tr>									
					<tr>
						<td><label>CRM:</label></td>
						<td><input class="form-control" style="width:30%" type="text" id="crm"name="crmMedico" maxlength="20" required
								   value="${MEDICO.crmMedico}" /></td>
					</tr>					
					<tr>
						<td><label>Especialidade:</label></td>
						<td>
						<select class="form-control" style="width:30%" name="especialidadeMedico">
							<c:forEach items="${ESPECIALIDADES_FORM}" var="tempEsp">    						
    							<c:choose>
    							<c:when test="${MEDICO.especialidadeMedico == tempEsp.codEspecialidade }">
        							<option value="${tempEsp.codEspecialidade}" selected><c:out value="${tempEsp.nomeEspecialidade}" /></option>
    							</c:when>    							
    							<c:otherwise>
    								<option value="${tempEsp.codEspecialidade}"><c:out value="${tempEsp.nomeEspecialidade}" /></option>
    							</c:otherwise>
    							</c:choose>    							
    						</c:forEach>    						
						</select>
						</td>
					</tr>					
						<tr>
						<td><label>RG:</label></td>
						<td><input class="form-control" style="width:30%" type="text" id="rg" name="rgMedico" maxlength="11" 
								   value="${MEDICO.rgMedico}" /></td>
					</tr>						
						<tr>
						<td><label>CPF:</label></td>
						<td><input class="form-control" style="width:30%" type="text" id="cpf" name="cpfMedico" maxlength="11"
								   value="${MEDICO.cpfMedico}" /></td>
					</tr>	
					
						<tr>
						<td><label>Endereço:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="enderecoMedico" maxlength="50"
								   value="${MEDICO.enderecoMedico}" /></td>
					</tr>					
					<tr>
						<td><label>Cidade:</label></td>
						<td>
						<select class="form-control" style="width:30%" name="cidadeMedico" >
							<c:forEach items="${CIDADES_FORM}" var="tempCid">    							
    							<c:choose>
    								<c:when test="${MEDICO.cidadeMedico == tempCid.codCidade }">
        								<option value="${tempCid.codCidade}" selected><c:out value="${tempCid.nomeCidade}" /></option>
        							</c:when>
        							<c:otherwise>
        								<option value="${tempCid.codCidade}"><c:out value="${tempCid.nomeCidade}" /></option>
        							</c:otherwise>
        						</c:choose>	        							
    						</c:forEach>    						
						</select>
						</td>
					</tr>					
						<tr>
						<td><label>Bairro:</label></td>
						<td><input class="form-control" type="text" style="width:30%" name="bairroMedico" maxlength="20"
								   value="${MEDICO.bairroMedico}" /></td>
					</tr>						
						<tr>
						<td><label>Estado:</label></td>
						<td><input class="form-control" type="text" style="width:30%" name="estadoMedico" maxlength="2" required readonly
								   value="${MEDICO.estadoMedico}" /></td>
					</tr>						
					<tr>
						<td><label>Telefone:</label></td>
						<td><input class="form-control" style="width:30%" type="tel" id="telefone" name="telefoneMedico" maxlength="20"
								   value="${MEDICO.telefoneMedico}" /></td>
					</tr>					
					<tr>
						<td><label>E-mail:</label></td>
						<td><input class="form-control" style="width:50%" type="email" name="emailMedico" max="50"
								   value="${MEDICO.emailMedico}" /></td>
					</tr>					
					<tr>
						<td><label>Nascimento:</label></td>
						<td><input class="form-control" style="width:30%" type="date" name="datanascMedico" required
								   value="${MEDICO.datanascMedico}" /></td>
					</tr>					
					<tr>
						<td><label></label></td>
						<td>
							<button type="submit" value="Salvar" class="btn btn-success save" />
							 <span class="glyphicon btn-glyphicon glyphicon-ok img-circle text-success"></span>Salvar</a>
						</td>
					</tr>				
				</tbody>
			</table>
		</form>
		<p>
			<a href="MedicoControllerServlet" class="btn btn-info">
			<span class="glyphicon btn-glyphicon  glyphicon-chevron-left img-circle text-info"></span>Voltar à Lista</a>
		</p>
	</div>
</body>
</html>











