<%@ page import="java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="includes/menu.jsp" %> 

 <div class="container">
  <h3>Agendamentos</h3> 
</div>

<%
	String keyword = "";
	if(request.getParameter("txtKeyword") != null) {
		keyword = request.getParameter("txtKeyword");
	}
%>
<div class="container">
	<form name="frmSearch" method="get" action="index2.jsp">
	  <table class="table">
	    <div class="row">
			<div class="col-sm-6 col-sm-offset-3">
			  <div id="imaginary_container"> 
			 	 <div class="input-group stylish-input-group">
	      			<input name="txtKeyword" type="text" id="txtKeyword" class="form-control" placeholder="Pesquisar paciente..." value="<%=keyword%>">	      			
	      			        <span class="input-group-addon">
			                    <button style="border:none; background: transparent;">			                       
			                            <span class="glyphicon glyphicon-search"></span>
			                    </button>      			                       
			                    </span>
			                </div>
			            </div>
			        </div>
				</div>	   
			</form>

	<%
	Connection connect = null;
	Statement s = null;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		
		connect =  DriverManager.getConnection("jdbc:mysql://localhost/sus_agendamento" +
				"?user=sus&password=sus");
		
		s = connect.createStatement();
		
		//String sql = "SELECT * FROM  agendamento WHERE cd_Paciente like '%" +  keyword + "%' " +
		//" ORDER BY cd_Paciente ASC";
		
		String sql = "select a.cd_Agendamento, a.dt_Agendamento, a.dt_Hora,"+
				" p.nm_Paciente, m.nm_Medico, e.nm_Especialidade, a.ds_status"+
				" from agendamento as a " +
				" left join  paciente as p on a.cd_Paciente = p.cd_Paciente" +
				" left join medico as m on a.cd_Medico = m.cd_Medico" +
				" left join especialidade as e on a.cd_Especialidade = e.cd_Especialidade" +
				" WHERE p.nm_Paciente like '%" + keyword + "%'" +
				" order by p.nm_Paciente";		;
		
		System.out.println(sql);
		
		ResultSet rec = s.executeQuery(sql);
		%>
		<table class="table">
		  <tr>
		  			<th>Cód.</th>
					<th>Data</th>
					<th>Hora</th>
					<th>Paciente</th>
					<th>Médico</th>	
					<th>Especialidade</th>
					<th>Status</th>													
					<th>Ação</th>
		  </tr>	
			<%while((rec!=null) && (rec.next())) { %>
				  <tr>
				  	<td><%=rec.getString("cd_Agendamento")%></td>
				    <td><%=rec.getString("dt_Agendamento")%></td>
				    <td><%=rec.getInt("dt_Hora")%></td>
				    <td><%=rec.getString("nm_Paciente")%></td>
				    <td><%=rec.getString("nm_Medico")%></td>
				    <td><%=rec.getString("nm_Especialidade")%></td>
				    <td><%=rec.getString("ds_Status")%></td>				    
				    <td> 				    
						<a class="btn btn-warning" href="#">Atualizar</a> 							 
						<a class="btn btn-danger" href="deletaAgenda.jsp?id=<%=rec.getString("cd_Agendamento") %>">Apagar</a></td>
					</td>
				</tr>
	       	<%}%>
	  	</table>      
	    <%	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
			e.printStackTrace();
		}
	
		try {
			if(s!=null){
				s.close();
				connect.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
			e.printStackTrace();
		}
	%>
	</div>
</body>
</html>