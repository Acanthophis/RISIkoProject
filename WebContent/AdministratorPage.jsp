<%@page import="java.util.ArrayList"%>
<%@page import = "web.model.Utente" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name = "description" content="Progetto di TSW">
<meta name = "author" content="Gerardo Benevento & Vincenzo Veniero"> 
<meta name = "viewport" content = "width=device-width, initial-scale=1.0"> 
<link rel="stylesheet" type="text/css" href="CssFolder/Standard.css">
<link rel="stylesheet" type="text/css" href="CssFolder/adminpage.css">


<title>AdministratorPage|RISIko</title>
</head>
<body>

<script>
function myFunction1(){
	document.getElementById("AddProduct").style.display = "inherit";
	document.getElementById("DeletePrd").style.display = "none";
	document.getElementById("ViewUsers").style.display = "none";
}

function myFunction2(){
	document.getElementById("AddProduct").style.display = "none";
	document.getElementById("DeletePrd").style.display = "inherit";
	document.getElementById("ViewUsers").style.display = "none";
}

function myFunction3(){
	document.getElementById("AddProduct").style.display = "none";
	document.getElementById("DeletePrd").style.display = "none";
	document.getElementById("ViewUsers").style.display = "inherit";
}

</script>
<%if(session.getAttribute("IdRISIko") != null && session.getAttribute("role").equals("administrator")) {%>
	<%ArrayList<Utente> users = (ArrayList<Utente>)session.getAttribute("users");
		if(users.size() == 0)
			response.sendRedirect("UserServlet?action=viewUsers");
	%>
<h1>Benvenuto Amministratore <%=session.getAttribute("IdRISIko") %></h1>
<h2>Select your operation<br>
<input class="PrButton" type="button" value="Aggiungi Prodotto" onclick="myFunction1()"><br>
<input class="PrButton" type="button" value="Cancella Prodotto" onclick="myFunction2()"><br>
<input class="PrButton" type="button" value="Gestione Utenti" onclick="myFunction3()"></h2>
	<div id ="AddProduct">
	<h3>Insert your NEW Product</h3>
	<script src="addProductValidation.js" type="text/javascript"></script>
		<form action="ProductServlet" method="post">
			<input type="hidden"  name = "action" value ="insert">
			<input class="inputType" type="text" name="idProdotto" placeholder="idProdotto" required="required" maxlength="8"><br>
			<input class="inputType" type="text" name="nomeProdotto" placeholder="nomeProdotto" required="required" maxlength="20"><br>
			<input class="inputType" type="number" name="prezzoProdotto" placeholder="prezzoProdotto" required="required"><br>
			<input class="inputType" type="text" name="descrizioneProdotto" placeholder="descrizioneProdotto" required="required"><br>
			<input class="inputType" type="date" name="dataUscitaProdotto" placeholder="dataUscitaProdotto" required="required"><br>
			<input class="inputType" type="text" name="categoriaProdotto" placeholder="categoriaProdotto" required="required" maxlength="20"><br>
			<input class="inputType" type="number" name="nGiocatoriProdotto" placeholder="nGiocatoriProdotto" required="required"><br>
			<input class="PrButton" type="submit" value="Insert" onclick="return addProductValidation(this.form)">
			<input class="PrButton" type="reset" value="Reset">
		</form>
	</div>		
	<div id = "DeletePrd">	
		<h3>Cancella Prodotto</h3>
		<form action="ProductServlet" method="post">
			<input type="hidden"  name = "action" value ="delete">
			<input id = "nameTXT" class="inputType" type="text" name="idProdotto" placeholder="idProdotto" required="required"><br>
			<input class="PrButton" type="submit" value="Delete">
			<input  class="PrButton" type="reset" value="Reset">
		</form>	
	</div>
<div id ="ViewUsers">
	<h3>Gestione Utenti</h3>
		<%int i = 0; %>
		<%for(i = 0; i < users.size();i++){
		%>
		
		<table>
			<tr>
				<td><%=users.get(i).toString() %></td>
				<td class = "userButtons">
				<form action="UserServlet" method = post>
					<%if(users.get(i).getRole().equals("user")){%>
					<input type="hidden" name = "action" value = "LVUP">
					<input type="hidden" name = "user" value="<%=users.get(i).getId()%>">
					<input class="inputType" type="submit" value="LVUP">
					<%}else{ %>
					<input type = "hidden" name = "action" value="LVDOWN">
					<input type="hidden" name = "user" value="<%=users.get(i).getId()%>">
					<input class="inputType" type="submit" value="LVDOWN">
					<%} %>
				</form>
				</td>
			</tr>
		</table>
		<%} %>
</div>
<div id ="back">
	Torna a<br>
	<a href="Home.jsp">Home Page</a><br>
	<a href="ChiSiamo.jsp">Chi Siamo</a><br>
	<a href="News.jsp">News</a><br>
	<a href="Prodotti.jsp">Prodotti</a><br>
	<a href="Support.jsp">Support</a>
</div>

<% }else{
	response.sendRedirect("CredentialError.jsp");
	}%>
</body>
</html>