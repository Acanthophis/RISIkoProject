<%@page import="web.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name = "description" content="Progetto di TSW">
<meta name = "author" content="Gerardo Benevento & Vincenzo Veniero"> 
<meta name = "viewport" content = "width=device-width, initial-scale=1.0"> 
<link rel="stylesheet" type="text/css" href="CssFolder/myAccount.css">
<title>MyAccountPage</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<%
			
			Cookie[] cks = request.getCookies();
			String name = null;
			String surname = null;
			String IdRISIko = null;
			String email = null;
			String saldo = null;
			for(int i = 0; i< cks.length;i++){
				if(cks[i].getName().equals("Name"))
					name = cks[i].getValue();
				if(cks[i].getName().equals("Surname"))
					surname = cks[i].getValue();
				if(cks[i].getName().equals("IdRISIko"))
					IdRISIko = cks[i].getValue();
				if(cks[i].getName().equals("EMail"))
					email = cks[i].getValue();
				if(cks[i].getName().equals("Saldo"))
					saldo = cks[i].getValue();
			}
			
		%>
<div>
	<h2>Benvenuto <%=IdRISIko %></h2>

</div>

<table>
	<tr>
		<th>Name</th>
		<td><%=name %></td>
	</tr>
	<tr>
		<th>Surname</th>
		<td><%=surname %></td>
	</tr>
	<tr>
		<th>IdRISIko</th>
		<td><%=IdRISIko %></td>
	</tr>
	<tr>
		<th>E-Mail</th>
		<td><%=email %></td>
	</tr>
	<tr>	
		<th>Saldo</th>
		<td><%=saldo %></td>
	</tr>
</table>

	<div id = "RicaricaSection">
		<p>
			Ricarica il tuo Conto
		</p>
		<form action="UserServlet" method = "get">
		<input type="hidden" name="action" value="ricarica">
		
			<label>Importo: <input class="howmuch" type="number" name="box" min="5" placeholder="minimo 5 Box"></label></li>
			<input class="howmuch" type="submit" value="Ricarica">
		</form>
	</div>

<%@ include file="Footer.jsp" %>
</body>
</html>