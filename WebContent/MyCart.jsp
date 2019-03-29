<%@page import="web.model.ProdottoInTheCart"%>
<%@page import="web.model.Carrello"%>
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
<link rel="stylesheet" type="text/css" href="CssFolder/Standard.css">
<link rel="stylesheet" type="text/css" href="CssFolder/cart.css">
<title>MyCart|RISIko</title>
</head>
<body>

<%@ include file="Header.jsp" %>

<div id="MyCart">

<%  float saldoUserFloat = 0;
	float saldo = 0;
	if(session.getAttribute("IdRISIko")==null){
		response.sendRedirect("UMustLogIn.jsp");
	}
	else{
		Carrello carrello = (Carrello)session.getAttribute("cart");
		if(carrello!=null){%>
		<%
		if(carrello.getSize() == 0){%>
		<p id="voidCart">Il tuo carrello è vuoto</p>
		<%}else{ %>
	<table>
		<tr>
			<th>Nome</th> 
			<th class="nomobile">Id</th> 
			<th class="nomobile">DataRilascio</th> 
			<th class="nomobile">Categoria</th> 
			<th class="nomobile">Descrizione</th> 
			<th class="nomobile">Giocatori</th> 
			<th>Prezzo</th> 
			<th>Quantita</th>
			<th>TotPrice</th>
		</tr>
		
		<% 
		for(int i = 0; i < carrello.getSize(); i++){%>
		<tr>
			<td><%=carrello.getEl(i).getNome() %></td>
			<td class="nomobile"><%=carrello.getEl(i).getId() %></td>
			<td class="nomobile"><%=carrello.getEl(i).getDateRel() %></td>
			<td class="nomobile"><%=carrello.getEl(i).getCategoria() %></td>
			<td class="nomobile"><%=carrello.getEl(i).getDescrp() %></td>
			<td class="nomobile"><%=carrello.getEl(i).getNgiocatori() %></td>
			<td><%=carrello.getEl(i).getPrezzo() %>$</td>
			<td><%=carrello.getEl(i).getQuantity() %></td>
			<td><%=carrello.getEl(i).getQuantity()*carrello.getEl(i).getPrezzo() %></td>
			<td><a href="CartServlet?action=deleteFromCart&&idProdotto=<%=carrello.getEl(i).getId() %>">Delete</a></td>
		</tr>
		<%}} }%>
</table>
	
</div>
<div id= "Fattura">
	<%saldo = 0;
	Carrello cart = (Carrello)session.getAttribute("cart");
	if(cart!=null){
		if(cart.getSize()> 0)
				for(int i = 0; i < cart.getSize();i++){
					saldo += cart.getEl(i).getPrezzo()*cart.getEl(i).getQuantity();
				}
	}
			
	%>
	<span>Totale Fattura: <%=saldo %> $</span>
	<% 
	Cookie[] cks = request.getCookies();
	String saldoUser = null;
	for (int i = 0; i < cks.length; i++){
		if(cks[i].getName().equals("Saldo"))
			 saldoUser = cks[i].getValue();
	}
	saldoUserFloat = Float.parseFloat(saldoUser);
	}%>
	
	
	<% if(saldo != 0){ %>
	<form action="CartServlet" method="post">
		<input type="hidden" name="action" value="salda">
		<input class="payButton" type="submit" value="Effettua Il Pagamento NOW!" onclick="alertMoney()">
		
	<%} %>
	</form>
</div>


<%@ include file="Footer.jsp" %>
<script type="text/javascript">
	function alertMoney(){
		<%if(saldoUserFloat < saldo){ %>
		window.alert('soldi insufficienti; effettua una ricarica');	
	<%} %>
	}
</script>

</body>
</html>