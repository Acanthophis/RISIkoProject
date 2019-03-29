 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name = "viewport" content = "width=device-width, initial-scale=1.0"> 
<link rel="stylesheet" type="text/css" href="CssFolder/Standard.css">

<title>Insert title here</title>
</head>
<body>
<script>
	
</script>
<div id="loginContainer">
	<span id="logout">
				Benvenuto <%=session.getAttribute("IdRISIko") %>		
	</span>
	<span id="carrello">
				<a href="MyCart.jsp"><img alt="carrello" src="Image/shopping-cart.png"></a>		
	</span>
	<% if(session.getAttribute("role")!= null){
		if(session.getAttribute("role").equals("administrator")){%>
	<span id="impostazioni">
				<a href="AdministratorPage.jsp"><img alt="impostazioni" src="Image/settings-work-tool.png"></a>		
	</span>
	<%}} %>
	<span id="openprofile" onclick="myfunction1()">
		<img alt="mostra/nascondi" src="Image/repeat.png">
	</span>
	<div id="profile">
		<div id="yourProfile">
			<a href="MyAccount.jsp">Il Mio Profilo</a>
		</div>
		<form action="UserServlet" method="post">
			<input type="hidden" name="action" value="viewEmail">
			<input class="buttons" type="submit" value="Posta">
		</form>
		<form action="UserServlet" method="post">
			<input type="hidden" name ="action" value="logout">
			<input class="buttons" type="submit" value="Logout!">
		</form>
	</div>
</div>
<script>
function myfunction1() {
	document.getElementById("profile").style.display = "inherit";
}
</script>
</body>
</html>

