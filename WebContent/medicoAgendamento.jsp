<%@page import="org.w3c.dom.CDATASection"%>
<%@page import="java.sql.*" %>

<%
if(request.getParameter("cd_Especialidade")!=null) 
{
    int id=Integer.parseInt(request.getParameter("cd_Especialidade")); 
        
    try
    {
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sus_agendamento","root","jeje2021"); //create connection
            
        PreparedStatement pstmt=null ; 
                
        pstmt=con.prepareStatement("select * from medico where cd_Especialidade=? "); 
        pstmt.setInt(1, id);
        ResultSet rs=pstmt.executeQuery();
        %>        
            <option selected="selected">Selecione o Médico</option>
        <%    
        while(rs.next())
        {
        %>        
            <option value="<%=rs.getInt("cd_Medico")%>">
                <%=rs.getString("nm_Medico")%>
            </option>
        <%
        }
  
        con.close();
    }
    catch(Exception e)
    {
        out.println(e);
    }
}
%>
