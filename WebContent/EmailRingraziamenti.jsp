<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name = "description" content="Progetto di TSW">
<meta name = "author" content="Gerardo Benevento & Vincenzo Veniero">
<meta name = "viewport" content = "width=device-width, initial-scale=1.0">  
<link rel="stylesheet" type="text/css" href="CssFolder/mail.css">
<link rel="stylesheet" type="text/css" href="CssFolder/Standard.css">
<title>MailZone</title>
</head>

<body>
	 <% if (session.getAttribute("IdRISIko")!= null){%>
	<div id ="ringraziamenti">
	<h1>Grazie per averci contattato <%= session.getAttribute("IdRISIko") %></h1>
	
	<span id = "okMan">
			<img alt="okman" src="Image/okMan.png" height="300px" width="300px">
	</span>
	
	
		<h2>Ti risponderermo non appena possibile</h2>
	<%} else {
		response.sendRedirect("Home.jsp");
	} %>
	<div id="links">
		<a href="Home.jsp">Home</a><br>	</div>
	</div>
	
	<%@ include file="Footer.jsp" %>
</body>

</html>