<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="includes/menu.jsp" %>

	<div class="container">
		<h3>Cadastro de Paciente</h3>		
		<%-- Script que formata os campos corretamente --%>
		<%-- Requer jquery.mask(.min).js --%>
		<script type="text/javascript">
			$(document).ready(function(){
				
				$("#rg").mask("999.999.999-W", {
					translation: {
						'W': {
							pattern: /[X0-9]/
						}
					},
					reverse: true
				})
				
				$("#cpf").mask("000.000.000-00")
				$("#telefone").mask("(00) 0000-00009")
				$("#telefone").blur(function(event){
					if ($(this).val().length == 15){
						$("#telefone").mask("(00) 00000-0009")	
					}else{
						$("#telefone").mask("(00) 0000-0009")
					}
				})				
			})
			</script>
		<form action="PacienteControllerServlet" method="GET">		
			<input type="hidden" name="command" value="ADD" />		
			<table class="table">
					<tr>
						<td><label>Nome:</label></td>
						<td><input class="form-control" style="width:50%" type="text" id="nome" name="nomePaciente" placeholder="Digitar nome" maxlength="50" required/></td>
					</tr>
					<tr>
						<td><label>RG:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="rgPaciente" id="rg" placeholder="Digitar RG" maxlength="13" required/></td>
					</tr>					
					<tr>
						<td><label>CPF:</label></td>
						<td><input  class="form-control" style="width:30%" type="text" id="cpf" name="cpfPaciente" placeholder="Digitar CPF" maxlength="14" required/></td>
					</tr>
					<tr>
						<td><label>Endereço:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="enderecoPaciente" placeholder="Digitar Rua" maxlength="50" required/></td>
					</tr>					
					<tr>
						<td><label>Cidade:</label></td>
						<td>
						<select class="form-control" style="width:30%" name="cidadePaciente" required>
							<option value="" > <c:out value = "Seleciona"/></option>
    						<c:forEach items="${CIDADES_FORM}" var="tempCid">
        						<option value="${tempCid.codCidade}"><c:out value="${tempCid.nomeCidade}" /></option>
    						</c:forEach>
						</select>
						</td>
					</tr>					
					<tr>
						<td><label>Bairro:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="bairroPaciente" placeholder="Digitar bairro" maxlength="20" required /></td>
					</tr>
					<tr>
						<td><label>Estado:</label></td>
						<td>
							<input type="text" class="form-control" style="width:10%" name="estadoPaciente" maxlength="2" value="SP" size="2" readonly required/>
						</td>
					</tr>
					<tr>
						<td><label>Telefone:</label></td>
						<td><input class="form-control" style="width:30%" type="tel" id="telefone" name="telefonePaciente" placeholder="Digitar telefone" maxlength="14" required/></td>
					</tr>
					<tr>
						<td><label>E-Mail:</label></td>
						<td><input class="form-control" style="width:50%" type="email" name="emailPaciente" placeholder="Digitar E-mail" maxlength="50" required /></td>
					</tr>					
					<tr>
						<td><label>Data Nascimento:</label></td>
						<td><input class="form-control" style="width:30%" type="date" name="datanascPaciente" id="datanasc" placeholder="00/00/0000" required/></td>
					</tr>					
					<tr>					
						<td><label>Sexo:</label></td>
						<td>
						<select class="form-control" style="width:30%" name="sexoPaciente" required>
							<option value="" > <c:out value = "Selecionar"/></option>
        						<option value="F"><c:out value="Feminino" /></option>
        						<option value="M"><c:out value="Masculino" /></option>
    						
						</select>
						</td>
					</tr>
					
					<tr>
						<td><label>Nr. Sus:</label></td>
						<td><input class="form-control" style="width:30%" type="text" name="cartaoPaciente" placeholder="Digitar Nr. SUS" required /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><button type="submit" class="btn btn-success" id="validar" value="Salvar" onClick="valida()" class="save" />
							 <span class="glyphicon btn-glyphicon glyphicon-ok img-circle text-success"></span></span>Salvar</button>
						</td>
					</tr>
			</table>
		</form>
		<p>
			<a  class="btn btn-info" href="PacienteControllerServlet">
			<span class="glyphicon btn-glyphicon  glyphicon-chevron-left img-circle text-info"></span>Voltar à Lista</a>
		</p>
	</div>
<%-- Script que retira a mascara dos dados antes de enviar --%>
<%-- Requer jquery.mask(.min).js --%>
<script type="text/javascript">
	$("#formCadastroPaciente").submit(function() {
		$("#rg").unmask();
		$("#cpf").unmask();
		$("#telefone").unmask();
	})
</script>

</body>

</html>











