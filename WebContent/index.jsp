 <%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<head>
  <title>Agendamento</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
 <%
Connection con= null;
PreparedStatement ps = null;
ResultSet rs = null;

String driverName = "com.mysql.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/sus_agendamento";
String user = "root";
String password = "jeje2021";

String sql = "select ds_Usuario from usuario";

try {
Class.forName(driverName);
con = DriverManager.getConnection(url, user, password);
ps = con.prepareStatement(sql);
rs = ps.executeQuery(); 
%>
<form method="post" action="login.jsp">
	<div class="container"><br>
		<div class="row no-gutters">
			<div class="col-6 col-md-4">
				<img src="imagens/red-cross.jpg" style="width:50%; height:50%;margin-left:200px">
			</div>	
				<div class="col-10 col-sm-5 col-md-5"><br><br>
				 <h1 class="text-center" style="color:gray;">Agendamento de consultas médicas</h1>
				 </div>
			</div>
		</div><br><br>
	<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
           <!--  <h1 class="text-center login-title">Login</h1>-->
            <div class="account-wall">
                <form class="form-signin">
                <input type="text" name="nm_Usuario" class="form-control" placeholder="Digite seu Login" required autofocus>
                <input type="password" name="ds_Senha" class="form-control" placeholder="Senha" required>
                <div class="form-group">
					<select class="form-control" name="ds_Usuario">
								<option value="Selecione">selecione:</option>
						  	<%
								while(rs.next())
								{
								String ds_Usuario = rs.getString("ds_Usuario");
							%>
								<option value=<%=ds_Usuario%>><%=ds_Usuario%></option>
							<% 
								}
							}
								catch(SQLException sqe)
								{
									out.println("index"+sqe);
								}
							%>
					</select><br/>
                <button class="btn btn-lg btn btn-block" type="submit">
                    Entrar</button>             
                </form>
           
    </div>
</div>
</body>
</html>