<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
//Verifica se está logado 
String ds_Usuario = (String) session.getAttribute("ds_Usuario");
if(ds_Usuario!=null)
{
//usuário válido
}
else
{
//retorna caso o usuário esteja inválido
	
response.sendRedirect("http://localhost:8080/agendamentojava/acesso-negado.jsp");
}

%>
<!DOCTYPE html>
<head>
<title>Agendamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script src="js/jquery.mask.min.js"></script>

<script type="text/javascript">
function esp_change()
{
    var especialidade = $(".especialidade").val();
 
    $.ajax({
        type: "POST",
        url: "medicoAgendamento.jsp",
        data: "cd_Especialidade="+especialidade,
        cache: false,
        success: function(response)
        {
            $(".medico").html(response);
        }
    });
}

$(document).ready(function(){
            $("#nome").autocomplete("autocomplete-paciente.jsp", {
                        scroll: true,
                        scrollHeight: 10,
                        maxHeight: 10,
                        width: 280,
                        selectFirst: true
                    });
});

</script>

<script>
$(document).ready(function(){
  $("#paciente").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});

</script>
</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="AgendamentoControllerServlet?command=CADASTRO" >Novo Agendamento</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="AgendamentoControllerServlet">Inicio</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Listas <span class="caret"></span></a>
        <ul class="dropdown-menu">
         <!-- <li><a href="AgendamentoControllerServlet">Agendamento</a></li> -->
          <li><a href="EspecialidadeControllerServlet">Especialidade</a></li>
          <li><a href="MedicoControllerServlet">Medico</a></li>
          <li><a href="PacienteControllerServlet">Paciente</a></li>
          <c:choose>
        		<c:when test="${ds_Usuario==('Admin')}">	 
            		<li><a href="UsuarioControllerServlet" >Usuário</a></li>
            	</c:when>
				<c:when test="${ds_Usuario!=('Admin')}">
					<li><a href="UsuarioControllerServlet">Usuário</a></li>
				</c:when>
			</c:choose>
        </ul>
      </li>
       <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Cadastros <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="EspecialidadeControllerServlet?command=CADASTRO">Especialidade</a></li>
          <li><a href="MedicoControllerServlet?command=CADASTRO">Medico</a></li>
          <li><a href="PacienteControllerServlet?command=CADASTRO">Paciente</a></li>
           <c:choose>
        		<c:when test="${ds_Usuario==('Admin')}">	 
          			<li><a href="UsuarioControllerServlet?command=CADASTRO">Usuário</a></li>
        		</c:when>
        		<c:when test="${ds_Usuario!=('admin')}">	 
          			<li><a href="UsuarioControllerServlet?command=CADASTRO" >Usuário</a></li>
 				</c:when>
 			</c:choose>      
        </ul>
      </li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
 		
      <li><a href="logout.jsp"><span class="glyphicon glyphicon-user"></span> Sair</a></li>
    </ul>
  </div>
</nav>

