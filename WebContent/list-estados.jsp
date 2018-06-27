<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Agendamento de Consultas SUS</title>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Lista de Estados Cadastrados</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">					
			<table>			
				<tr>
					<th>Sigla</th>
					<th>Nome</th>
				</tr>				
				<c:forEach var="tempEstado" items="${ESTADO_LIST}">																					
					<tr>
						<td> ${tempEstado.sgEstado} </td>
						<td> ${tempEstado.nomeEstado} </td>
					</tr>				
				</c:forEach>				
			</table>		
		</div>	
	</div>
</body>
</html>








