<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="includes/menu.jsp" %>

 <div class="container">
  <h3>Atualizar Paciente</h3>
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
		<form action="PacienteControllerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="pacienteId" value="${PACIENTE.codPaciente}" />	
			<table class="table">
				<tbody>
					<tr>
						<td><label>Nome:</label></td>
						<td><input class="form-control" style="width:50%" type="text" name="nomePaciente" id="nome" maxlength="50" size="50" required
								   value="${PACIENTE.nomePaciente}" required/></td>
					</tr>							
						<tr>
						<td><label>RG:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="rgPaciente" id="rg" maxlength="13" required
								   value="${PACIENTE.rgPaciente}" required/></td>
					</tr>						
					<tr>
						<td><label>CPF:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="cpfPaciente" id="cpf" maxlength="14" required
								   value="${PACIENTE.cpfPaciente}" required/></td>
					</tr>					
						<tr>
						<td><label>Endereço:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="enderecoPaciente" maxlength="50" required
								   value="${PACIENTE.enderecoPaciente}" required/></td>
					</tr>			
					<tr>
						<td><label>Cidade:</label></td>
						<td>
							<select class="form-control" style="width:30%" name="cidadePaciente">
							<c:forEach items="${CIDADES_FORM}" var="tempCid">											
								<c:choose>
										<c:when test="${tempCid.codCidade == PACIENTE.cidadePaciente }">
											<option value="${tempCid.codCidade}" selected><c:out value="${tempCid.nomeCidade }"/></option>
										</c:when>
									<c:otherwise>
										<option value="${tempCid.codCidade}"><c:out value="${tempCid.nomeCidade }"/></option>
									</c:otherwise>
								</c:choose>
								</c:forEach>
							</select>
						</td>
					</tr>				
					<tr>
						<td><label>Bairro:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="bairroPaciente" maxlength="20" required
								   value="${PACIENTE.bairroPaciente}" /></td>
					</tr>						
					<tr>
						<td><label>Estado:</label></td>
						<td><input class="form-control" style="width:30%"  type="text" name="estadoPaciente" maxlength="2" readonly required
								   value="${PACIENTE.estadoPaciente}" /></td>
					</tr>						
					<tr>
						<td><label>Telefone:</label></td>
						<td><input class="form-control" style="width:30%" type="text" id="telefone" name="telefonePaciente" maxlength="14"
								   value="${PACIENTE.telefonePaciente}" /></td>
					</tr>					
					<tr>
						<td><label>E-mail:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="emailPaciente" maxlength="50"
								   value="${PACIENTE.emailPaciente}" /></td>
					</tr>					
					<tr>
						<td><label>Nascimento:</label></td>
						<td><input class="form-control" style="width:30%" type="date" id="datanasc" name="datanascPaciente" required
								   value="${PACIENTE.datanascPaciente}" /></td>
					</tr>			
					
					<tr>
						
						<td><input class="form-control" style="width:30%" type="hidden" name="sexoPaciente" required
								   value="${PACIENTE.sexoPaciente}" /></td>
					</tr>	
							
					<tr>
						<td><label>Cartao Sus:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="cartaoPaciente" 
								   value="${PACIENTE.cartaoPaciente}" /></td>
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
			<a href="PacienteControllerServlet" class="btn btn-info">
			<span class="glyphicon btn-glyphicon  glyphicon-chevron-left img-circle text-info"></span>Voltar à Lista</a>
		</p>
	</div>
</body>

</html>











