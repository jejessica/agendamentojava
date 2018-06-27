<%@page import="java.sql.*"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@include file="includes/menu.jsp" %>
  
  <div class="container">
 
		<h3>Cadastro de Agendamento</h3>		
		<form action="AgendamentoControllerServlet" method="GET" id="form">		
			<input type="hidden" name="command" value="ADD" />			
			 <table class="table">					
					<tr>
						<td><label>Data:</label></td>
						<td><input class="form-control" style="width:30%" id="datefield" type="date" name="dtAgendamento" min="" required/></td>
					</tr>			
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
				
					<tr>
						<td><label>Hora:</label></td>
						<td><input class="form-control" style="width:30%" type="time" name="horaAgendamento" min="07:00" max="16:00" step="1800" required/></td>
					</tr>					
				  <tr>	
					<td><label>Paciente:</label></td>
						<td><select class="paciente form-control" style="width:50%" name="pacienteAgendamento" required>
 								<option selected="selected" value="">Paciente</option> 
					    <%
					    try
					    {
					        Class.forName("com.mysql.jdbc.Driver"); 
					        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sus_agendamento","root","jeje2021"); //create connection
					                
					        PreparedStatement pstmt=null;
					                
					        pstmt=con.prepareStatement("select * from paciente order by nm_Paciente");
					        ResultSet rs=pstmt.executeQuery(); 
					                
					        while(rs.next())
					        {
					        	%>
					            	<option value="<%=rs.getInt("cd_Paciente")%>">
                						 <%=rs.getString("nm_Paciente")%>
            						</option>
        						<%
        				}
           
        				con.close(); 
				    	}
				   		 catch(Exception e)
				    	{
				       		out.println(e);
				    	}
				    %>
				 		</select>
				 	</td>
				 </tr>	
			<tr>			 		 	
				<td><label>Especialidade:</label></td>
					<td><select class="especialidade form-control" style="width:30%" name="especialidadeAgendamento" onchange="esp_change()" required>
 						<option selected="selected" value="">Selecione a Especialidade </option>
 
					    <%
					    try
					    {
					        Class.forName("com.mysql.jdbc.Driver"); 
					        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sus_agendamento","root","jeje2021"); //create connection
					                
					        PreparedStatement pstmt=null;
					                
					        pstmt=con.prepareStatement("select * from especialidade");
					        ResultSet rs=pstmt.executeQuery(); 
					                
					        while(rs.next())
					        {
					        	%>
					            	<option value="<%=rs.getInt("cd_Especialidade")%>">
                						 <%=rs.getString("nm_Especialidade")%>
            						</option>
        						<%
        				}
           
        				con.close(); 
				    	}
				   		 catch(Exception e)
				    	{
				       		out.println(e);
				    	}
				    %>
				 		</select>
				 	</td>
				 </tr>	
			<tr>	
				<td><label>Médico:</label></td> 
			 		<td><select class="medico form-control" style="width:30%" name="medicoAgendamento" required>
			 			<option selected="selected" value=""> Selecione o  Médico </option>
			 		</select>
				</td>
			</tr>
			<tr>
				<td><label></label></td>
					<td><button type="submit" class=" btn btn-success save"/>
				 <span class="glyphicon btn-glyphicon glyphicon-ok img-circle text-success"></span>Salvar</button>
				</td>
			</tr>					
			</table>			
		</form>
		<p>
			<a  class="btn btn-info" href="AgendamentoControllerServlet">
			<span class="glyphicon btn-glyphicon  glyphicon-chevron-left img-circle text-info"></span>Voltar à Lista</a>
		</p>
	</div>
</body>

</html>











