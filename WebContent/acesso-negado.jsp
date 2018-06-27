<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta HTTP-EQUIV="REFRESH" content="1; url=${pageContext.request.contextPath}/index.jsp">
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Acesso Negado</title>
</head>
<body>
<% session.invalidate(); %>
<div class="alert alert-danger">
  <strong>Acesso Negado</strong>
</div>
</body>
</html>