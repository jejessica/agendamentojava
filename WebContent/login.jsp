<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/bootstrap.min.js"></script>
<title>Login</title>
</head>
<body>
<%! String nm_Usuario;
String ds_Senha;
String ds_Usuario;
%>
<%
Connection con= null;
PreparedStatement ps = null;
ResultSet rs = null;

String driverName = "com.mysql.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/sus_agendamento";
String user = "root";
String password = "jeje2021";

String sql = "select * from usuario where nm_Usuario=? and ds_Senha=? and ds_Usuario=?";

String nm_Usuario = request.getParameter("nm_Usuario");
String ds_Senha = request.getParameter("ds_Senha");
String ds_Usuario = request.getParameter("ds_Usuario");

if((!(nm_Usuario.equals(null) || nm_Usuario.equals("")) && !(ds_Senha.equals(null) || ds_Senha.equals(""))) && !ds_Usuario.equals("select"))
{
try{
Class.forName(driverName);
con = DriverManager.getConnection(url, user, password);
ps = con.prepareStatement(sql);
ps.setString(1, nm_Usuario);
ps.setString(2, ds_Senha);
ps.setString(3, ds_Usuario);
rs = ps.executeQuery();
if(rs.next())
{ 
nm_Usuario = rs.getString("nm_Usuario");
ds_Senha = rs.getString("ds_Senha");
ds_Usuario = rs.getString("ds_Usuario");
if(nm_Usuario.equals(nm_Usuario) && ds_Senha.equals(ds_Senha) && ds_Usuario.equals(ds_Usuario))
{
session.setAttribute("nm_Usuario",nm_Usuario);
session.setAttribute("ds_Usuario", ds_Usuario); 
response.sendRedirect("AgendamentoControllerServlet"); 
} 
}
else
response.sendRedirect("error.jsp");
rs.close();
ps.close(); 
}
catch(SQLException sqe)
{
out.println(sqe);
} 
}
else
{
%>
<center><p style="color:red">Erro no Login</p></center>
<% 
getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
}
%>
</body>
</html>