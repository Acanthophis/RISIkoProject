<%@page import="web.model.Casella"%>
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
	<body>
	<%Casella casella = (Casella)session.getAttribute("casella"); %>
	
	<div id ="ViewEmail">
	<table>
			<tr>
				<th>Mittente</th>
				<th>Messaggio</th>
			</tr>
	<%int i = 0; %>
	<%for(i = 0; i < casella.getInbox().size();i++){
	%>
			<tr>
				<td><%=casella.getMessage(i).getMittente() %></td>
				<td><%=casella.getMessage(i).getMessage() %></td>
				<td><a href="ResponseEmailPage.jsp">Rispondi</a></td>
				<td><form action="UserServlet">
				<input type="hidden" name="action" value="deleteEmail">
				<input class="delete" type="submit" value="Delete">
				</form>
				</td>
			</tr>
			<%} %>
		</table>
		
	
			
</div>
	<%} %>
	<%@ include file="Footer.jsp" %>
</body>
</html>