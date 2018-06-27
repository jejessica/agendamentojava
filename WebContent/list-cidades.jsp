<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Agendamento de Consultas SUS</title>	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Lista de Cidades Cadastradas</h2>
		</div>
	</div>
	<div id="container">	
		<div id="content">		
			<table>		
				<tr>
					<th>Código</th>
					<th>Estado</th>
					<th>Nome</th>
				</tr>			
			 	<c:forEach var="tempCidade" items="${CIDADE_LIST}">																				
					<tr>
						<td> ${tempCidade.codCidade} </td>
						<td> ${tempCidade.sgEstado} </td>
						<td> ${tempCidade.nomeCidade} </td>
					</tr>			
				</c:forEach> 			
			</table>		
		</div>
	</div>
</body>
</html>








