<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name = "description" content="Progetto di TSW">
<meta name = "author" content="Gerardo Benevento & Vincenzo Veniero"> 
<meta name = "viewport" content = "width=device-width, initial-scale=1.0"> 
<link rel="stylesheet" type="text/css" href="CssFolder/login.css">
<title>LoginPage|RISIko</title>
</head>
<body>
<%  if(session.getAttribute("IdRISIko")!=null){
		response.sendRedirect("Home.jsp");
	}
	else{%>
<script src="loginValidation.js" type="text/javascript"></script>
	<div id="logWindow">
		<p>Login</p>
		<form action="UserServlet" method="post">
		<input type="hidden"  name = "action" value ="login">
			<div class="inputContainer">
				<input id = "idTXT" class="inputType" type="text" name="username" placeholder="Id RISIko" required="required"><br>
			</div>
			<div class="inputContainer">
				<input id = "pswTXT" class="inputType" type="password" name="password" placeholder="Password" required="required"><br>
			</div>
			<input class="logButton" type="submit" value="Login" onclick = "return logValidation(this.form)">
			<input class="logButton" type="reset" value="Reset">
			<a class="logButton" href="Home.jsp">Home</a>
		</form>
	</div>
	<% } %>
</body>
<footer>
</footer>
</html>