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
<title>Register|RISIko</title>
</head>
<script src="registerValidation.js" type="text/javascript"></script>
<body>
<%  if(session.getAttribute("IdRISIko")!=null){
		response.sendRedirect("Home.jsp");
	}
	else{%>
	<div id="logWindow">
		<p>Register</p>
		<form action="UserServlet" method="post">
		<input type="hidden"  name = "action" value ="regUser">
			<div class="inputContainer">
				<input id = "nameTXT" class="inputType" type="text" name="name" placeholder="Name" required="required"><br>
			</div>
			<div class="inputContainer">
				<input id = "surnameTXT "class="inputType" type="text" name="surname" placeholder="Surname" required="required"><br>
			</div>
			<div class="inputContainer">
				<input id = "emailTXT" class="inputType" type="email" name="email" placeholder="Email" required="required"><br>
			</div>
			<div class="inputContainer">
				<input id = "idTXT" class="inputType" type="text" name="id" placeholder="ID RISIko" required="required"><br>
			</div>
			<div class="inputContainer">
				<input id = "pswTXT" class="inputType" type="password" name="password" placeholder="Password" required="required"><br>
			</div>
			<input class="logButton" type="submit" value="SignIn" onclick = "return registerValidation(this.form)">
			<input class="logButton" type="reset" value="Reset">
			<a id="homeAnchor" href="Home.jsp">
			<input type="button" class="logButton" id="homeButton" value="Home">
			</a>
			
		</form>
	</div>
	<%} %>
</body>
<footer>
</footer>
</html>