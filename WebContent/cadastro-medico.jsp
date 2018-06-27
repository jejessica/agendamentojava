<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="includes/menu.jsp" %>

	<div class="container">
		<h3>Cadastro de Médico</h3>

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

			$("#crm").mask("0000999999-SS")

		})
	</script>
	<form action="MedicoControllerServlet" method="GET">		
			<input type="hidden" name="command" value="ADD" />			
			<table class="table">					<tr>
						<td><label>Nome:</label></td>
						<td><input class="form-control" style="width:50%" type="text" name="nomeMedico" placeholder="Digitar nome" maxlength="50" required /></td>
					</tr>					
					<tr>
						<td><label>CRM:</label></td>
						<td><input class="form-control" style="width:30%" type="text" id="crm"name="crmMedico" placeholder="Digitar CRM" maxlength="20" required/></td>
					</tr>					
					<tr>
						<td><label>Especialidade:</label></td>
						<td>
						<select class="form-control" style="width:30%"  name="especialidadeMedico" required>
							<option value="" > <c:out value = "-Selecionar-"/></option>
    						<c:forEach items="${ESPECIALIDADES_FORM}" var="tempEsp">
        						<option value="${tempEsp.codEspecialidade}"><c:out value="${tempEsp.nomeEspecialidade}" /></option>
    						</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td><label>RG:</label></td>
						<td><input class="form-control" style="width:30%" type="text" id="rg" name="rgMedico" placeholder="Digitar RG" maxlength="11" required/></td>
					</tr>					
					<tr>
						<td><label>CPF:</label></td>
						<td><input class="form-control" style="width:30%" type="text" id="cpf" name="cpfMedico" placeholder="Digitar CPF" maxlength="11" required /></td>
					</tr>
					<tr>
						<td><label>Endereço:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="enderecoMedico" placeholder="Digitar Rua" maxlength="50"/></td>
					</tr>					
					<tr>
						<td><label>Cidade:</label></td>
						<td>
						<select class="form-control" style="width:30%" name="cidadeMedico" required>
							<option value="" > <c:out value = "-Selecionar-"/></option>
    						<c:forEach items="${CIDADES_FORM}" var="tempCid">
        						<option value="${tempCid.codCidade}"><c:out value="${tempCid.nomeCidade}" /></option>
    						</c:forEach>
						</select>
						</td>
					</tr>					
					<tr>
						<td><label>Bairro:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="bairroMedico" maxlength="20" placeholder="Digitar Bairro"/></td>
					</tr>
					<tr>
						<td><label>Estado:</label></td>
							<td><input type="text" class="form-control" style="width:10%" name="estadoMedico" maxlength="2" value="SP" size="2" readonly required/></td>
					</tr>
					<tr>
						<td><label>Telefone:</label></td>
						<td><input class="form-control" style="width:30%" type="tel" id="telefone" name="telefoneMedico" placeholder="Digitar telefone" maxlength="20"/></td>
					</tr>
					<tr>
						<td><label>E-Mail:</label></td>
						<td><input class="form-control" style="width:50%"  type="email" name="emailMedico" placeholder="Digitar E-mail" maxlength="50"/></td>
					</tr>
					
					<tr>
						<td><label>Nascimento:</label></td>
						<td><input class="form-control" style="width:30%" type="date" id="datanasc" name="datanascMedico" placeholder="00/00/0000" required/></td>
					</tr>					
					<tr>
						<td><label></label></td>
						<td>
							<button type="submit" class="btn btn-success" value="Salvar" class="save" />
							 <span class="glyphicon btn-glyphicon glyphicon-ok img-circle text-success"></span>Salvar</button>
						</td>
					</tr>
			</table>
		</form>
		<p>
			<a  class="btn btn-info" href="MedicoControllerServlet">
			<span class="glyphicon btn-glyphicon  glyphicon-chevron-left img-circle text-info"></span>Voltar à Lista</a>
		</p>
	</div>
</body>

</html>











