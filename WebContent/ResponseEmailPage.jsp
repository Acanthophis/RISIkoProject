<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name = "viewport" content = "width=device-width, initial-scale=1.0"> 
<link rel="stylesheet" type="text/css" href="CssFolder/mail.css">
<title>MailZone</title>
</head>
<body>
	<%@ include file="Header.jsp" %>
	<%if(session.getAttribute("IdRISIko")==null){
		response.sendRedirect("UMustLogIn.jsp");
		}
		else{%>
	<div id="emailZone">
		<form action="UserServlet" method="post">
		<input type="hidden" name="action" value="responseEmail">
		<input class="inputText" type="text" name="destinatario" placeholder="Destinatario" required="required"><br>
		<textarea class="inputText" rows="5" name="emailzone" placeholder="Inserisci il tuo testo qui!" required="required"></textarea>
		<br>
		<input class="inputType" type="submit" value="Invio">
		</form>
	</div>
	<%} %>
</body>
</html>