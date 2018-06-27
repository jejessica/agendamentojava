<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,java.util.*"%>
<meta HTTP-EQUIV="REFRESH" content="1; url=${pageContext.request.contextPath}/index2.jsp">
<%
String id=request.getParameter("id");
try
{
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sus_agendamento", "sus", "sus");
Statement st=conn.createStatement();
int i=st.executeUpdate("DELETE FROM agendamento WHERE cd_Agendamento=" + id);
}
catch(Exception e)
{
System.out.print(e);
e.printStackTrace();
}
%>