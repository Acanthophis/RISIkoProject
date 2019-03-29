<%@page import="web.model.ProductModelDM"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="web.model.Prodotto"%>
<%@page import="com.sun.beans.util.Cache"%>
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
<link rel="stylesheet" type="text/css" href="CssFolder/Prodotti.css">
<title>Prodotti|RISIko</title>
</head>
<body>

<script>
function myfunction(){
		alert("Hai Aggiunto Questo Prodotto Al Carrello");
}
</script>
<%@ include file="Header.jsp" %>
<div id="Prodotti">
	<%
	ArrayList<Prodotto> products = (ArrayList<Prodotto>)session.getAttribute("products");
	if(products!=null){%>
		
		<table>
			<tr>
				<th>Nome</th> 
				<th class="nomobile">Id</th> 
				<th class="nomobile">DataRilascio</th> 
				<th class="nomobile">Categoria</th> 
				<th class="nomobile">Descrizione</th> 
				<th class="nomobile">Giocatori</th> 
				<th>Prezzo</th> 
				<th>Acquista</th>
			</tr>
		<%for(int i = 0; i < products.size(); i++){		
		%>	<tr>
				<td><%=products.get(i).getNome() %></td>
				<td class="nomobile"><%=products.get(i).getId() %></td>
				<td class="nomobile"><%=products.get(i).getDateRel() %></td>
				<td class="nomobile"><%=products.get(i).getCategoria() %></td>
				<td class="nomobile"><%=products.get(i).getDescrp() %></td>
				<td class="nomobile"><%=products.get(i).getNgiocatori() %></td>
				<td><%=products.get(i).getPrezzo() %>$</td>
				<td>	
				<%if(session.getAttribute("IdRISIko")!=null){ %>
				<form action="CartServlet" method="post">
				<input type="hidden" name="action" value="AddToCart">
				<input type="hidden" name="idUtente" value="<%=session.getAttribute("IdRISIko") %>">
				<input type="hidden" name="idProdotto" value="<%=products.get(i).getId() %>">
				<input class="prodHead" type="number" name="quantita" value="1" required="required" min="1" style="width: 50px;">
				<input class="prodHead" type="submit" value="addToCart" onclick="myfunction()">
				</form>
				<%}
				else{%>
					<a href="LoginPage.jsp">Effettua Il Login</a>
				<%} %>
				</td>
			</tr>
		
	
			
				
			
				<%}} %>
					</table>
</div>


<%@ include file="Footer.jsp" %>
</body>
</html>