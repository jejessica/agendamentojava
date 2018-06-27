<%@ page import="java.sql.*"  %> 
<%@include file="includes/menu.jsp" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<%! 
public int Converter(String str) 
{    
    int convrtr=0; 
    if(str==null){ 
            str="0"; 
    } 
    else if((str.trim()).equals("null")){ 
            str="0"; 
    } 
    else if(str.equals("")){ 
            str="0"; 
    } 
    try{ 
            convrtr=Integer.parseInt(str); 
    } 
    catch(Exception e){ 
    } 
    return convrtr; 
} 
%> 
<% 
    Connection con = null; 
    Class.forName("com.mysql.jdbc.Driver").newInstance(); 
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sus_agendamento","root", "jeje2021"); 
   
    ResultSet rsPgin = null; 
    ResultSet rsRwCn = null; 
    PreparedStatement psPgintn=null; 
    PreparedStatement psRwCn=null; 
       
    int iSwRws=8;    
    int iTotSrhRcrds=10;  
       
    int iTotRslts=Converter(request.getParameter("iTotRslts")); 
    int iTotPags=Converter(request.getParameter("iTotPags")); 
    int iPagNo=Converter(request.getParameter("iPagNo")); 
    int cPagNo=Converter(request.getParameter("cPagNo")); 
   
    int iStRsNo=0; 
    int iEnRsNo=0; 
   
    if(iPagNo==0) 
        { 
            iPagNo=0; 
        } 
    else{ 
            iPagNo=Math.abs((iPagNo-1)*iSwRws); 
        } 
       
    String sqlPgintn="SELECT SQL_CALC_FOUND_ROWS * FROM paciente limit "+iPagNo+","+iSwRws+""; 
    psPgintn=con.prepareStatement(sqlPgintn); 
    rsPgin=psPgintn.executeQuery(); 
     
    String sqlRwCnt="SELECT FOUND_ROWS() as cnt"; 
    psRwCn=con.prepareStatement(sqlRwCnt); 
    rsRwCn=psRwCn.executeQuery(); 
       
    if(rsRwCn.next()) 
      { 
        iTotRslts=rsRwCn.getInt("cnt"); 
      } 
%> 
<div class="container">
  <h3>Lista de Paciente</h3> 
</div>		
	<div class="container">
 		<table class="table">					
				<div class="row">
			        <div class="col-sm-6 col-sm-offset-3">
			            <div id="imaginary_container"> 
			                <div class="input-group stylish-input-group">
			                    <input id="paciente" type="text" class="form-control"  placeholder="Pesquisar..." >
			                    <span class="input-group-addon">
			                    <button id="paciente" style="border:none; background-color: transparent;">			                       
			                            <span class="glyphicon glyphicon-search"></span>
			                    </button>        			                       
			                    </span>
			                </div>
			            </div>
			        </div>
				</div> 
				<form action="PacienteControllerServlet" method="GET">										
		        <input type="hidden" name="iPagNo" value="<%=iPagNo%>"> 
		        <input type="hidden" name="cPagNo" value="<%=cPagNo%>"> 
		        <input type="hidden" name="iSwRws" value="<%=iSwRws%>"> 
	        <div class="row">	
			<table class="table">
				<thead>
				<c:forEach var="tempPaciente" items="${PACIENTE_LIST}">			
					<c:url var="tempLink" value="PacienteControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="pacienteId" value="${tempPaciente.codPaciente}" />
					</c:url>
					<c:url var="deleteLink" value="PacienteControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="pacienteId" value="${tempPaciente.codPaciente}" />
					</c:url>	
				</c:forEach>	
					<tr>
	      				<th>Nome</th>
						<th>CPF</th>
						<th >Cidade</th>
						<th>E-Mail</th>
						<th>Telefone</th>
						<th>Nascimento</th>	
						<th>Sexo</th>
						<th>Nr. Sus</th>	
	        		</tr> 
	<% 
   		 while(rsPgin.next()) 
      { 
	%> 
		<tbody id="myTable">
		<c:forEach var="tempPaciente" items="${PACIENTE_LIST}">			
					<c:url var="tempLink" value="PacienteControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="pacienteId" value="${tempPaciente.codPaciente}" />
					</c:url>
					<c:url var="deleteLink" value="PacienteControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="pacienteId" value="${tempPaciente.codPaciente}" />
					</c:url>													
		</c:forEach>	
        <tr> 
            <td><%=rsPgin.getString("nm_Paciente")%></td> 
            <td><%=rsPgin.getString("cd_CPF")%></td> 
            <td><%=rsPgin.getInt("nm_Cidade")%></td>
            <td><%=rsPgin.getString("ds_Email")%></td> 
            <td><%=rsPgin.getString("cd_Telefone")%></td> 
            <td><%=rsPgin.getString("dt_Nascimento")%></td> 
            <td><%=rsPgin.getString("ds_Sexo")%></td> 
            <td><%=rsPgin.getString("nm_Cartao")%></td>
            
            <td> 
				<a class="btn btn-warning" href="${tempLink}">
				<span class="glyphicon btn-glyphicon glyphicon-pencil img-circle text-warning"></span>Atualizar</a> 
			</td>
			<td>
				<c:choose>
   					<c:when test="${ds_Usuario==('Admin')}">								 
						<a class="btn btn-danger" href="${deleteLink}">
						<span class="glyphicon btn-glyphicon glyphicon-trash img-circle text-danger"></span>Apagar</a>	
					</c:when>
				</c:choose>	
			</td>  
        </tr> 
<%   } %> 

<% 
    try{ 
        if(iTotRslts<(iPagNo+iSwRws)){ 
                iEnRsNo=iTotRslts;
        } 
        else{ 
            iEnRsNo=(iPagNo+iSwRws); 
        }
            iStRsNo=(iPagNo+1); 
            iTotPags=((int)(Math.ceil((double)iTotRslts/iSwRws))); 
        } 
    	catch(Exception e){ 
            e.printStackTrace(); 
        } 
%> 
	<tr> 
		<td> 
	<div> 
	<ul class="pagination">
<% 
    int i=0; 
    int cPge=0; 
    if(iTotRslts!=0){ 
        cPge=((int)(Math.ceil((double)iEnRsNo/(iTotSrhRcrds*iSwRws)))); 
        int prePageNo=(cPge*iTotSrhRcrds)-((iTotSrhRcrds-1)+iTotSrhRcrds); 
        if((cPge*iTotSrhRcrds)-(iTotSrhRcrds)>0){ 
%> 
        <a href="teste.jsp?iPagNo=<%=prePageNo%>&cPagNo=<%=prePageNo%>"><< Próximo</a> </ul>
        <% 
        }         
        for(i=((cPge*iTotSrhRcrds)-(iTotSrhRcrds-1));i<=(cPge*iTotSrhRcrds);i++){ 
            if(i==((iPagNo/iSwRws)+1)){ 
         %> 
        <a href="teste.jsp?iPagNo=<%=i%>" style="cursor:pointer;color:red"><b><%=i%></b></a> 
         <% 
          } 
         else if(i<=iTotPags) 
         { 
         %> 
        <a href="teste.jsp?iPagNo=<%=i%>"><%=i%></a> 
         <%  
            } 
        } 
       
        if(iTotPags>iTotSrhRcrds&& i<iTotPags){ 
         %> 
        <a href="teste.jsp?iPagNo=<%=i%>&cPagNo=<%=i%>">>> Next</a> 
        <% 
        } 
      } 
      %> 
    
    <b> <%=iStRsNo%> - <%=iEnRsNo%>   Total:  <%=iTotRslts%></b> 
    </ul>
</div> 
</td> 
</tr>
</tbody> 
</table> 
</form> 
</div>
<%@include file="includes/footer.jsp" %>
</body> 
</html> 
<% 
try{ 
    if(psPgintn!=null){ 
        psPgintn.close(); 
    } 
    if(rsPgin!=null){ 
        rsPgin.close(); 
    }    
    if(psRwCn!=null){ 
        psRwCn.close(); 
    } 
    if(rsRwCn!=null){ 
        rsRwCn.close(); 
    } 
    if(con!=null){ 
        con.close(); 
    } 
  } 
catch(Exception e) 
  { 
    e.printStackTrace(); 
  } 
%> 