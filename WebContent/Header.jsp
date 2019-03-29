<%@page import="web.model.Utente"%>
<%@page import="com.sun.org.glassfish.gmbal.IncludeSubclass"%>
<%@page import="org.apache.tomcat.util.buf.UDecoder"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Gaegu|Roboto" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Coda+Caption:800" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Permanent+Marker" rel="stylesheet">  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name = "viewport" content = "width=device-width, initial-scale=1.0"> 
<!-- <link rel="stylesheet" type="text/css" href="CssFolder/Standard.css">-->
<title>Insert title here</title>
</head>
<body>
<script src="jquery.js"></script>
	<header>
		<span id = "logo">
			<a href="Home.jsp"><img alt="" src="Image/logo.png"></a>
		</span>
		<span id = "risiko">
			RISIko
		</span>
		<div id = "registration">
			<div id = "logOrNot">
				<%
				if(session.getAttribute("IdRISIko") != null){%>
						<%@ include file="Logged.jsp"%>	<%
				}else{%>
						<%@ include file="Unlogged.jsp" %><%
				}
				%>
			</div>
			<ul id = "sociallinks">
				<li><a href="https://it-it.facebook.com/" target="_blank"><img alt="Facebook" src="Image/facebook.png"></a></li>
				<li><a href="https://www.instagram.com/?hl=it" target="_blank"><img alt="Instagram" src="Image/instagram.png"></a></li>
				<li><a href="https://web.whatsapp.com/" target="_blank"><img alt="Twitter" src="Image/whatsapp.png"></a></li>
				<li><a href="https://twitter.com/?lang=it" target="_blank" ><img alt="YouTube" src="Image/twitter.png"></a></li>
			</ul>
		</div>
		
		<nav id = "bar">
			<input id="openMenu" type="button" value="MENU" onclick="menufunction1()"> 
			<input id="closeMenu" type="button" value="MENU" onclick="menufunction2()"> 
			<ul id = "navigationBar">
				<li></li>
				<li><a href="Home.jsp">HOME</a></li>
				<li><a href="ChiSiamo.jsp">CHI SIAMO</a></li>
				<li><a href="News.jsp">NEWS</a></li>
				<li><form action="ProductServlet" method="post">
						<input type="hidden" name="action" value="viewAllProducts">	
						<input class="Prodotti" type="submit" value="PRODOTTI"></form></li>
				<li><a href="Support.jsp">SUPPORT</a></li>
				<li id = "srch">
					<form action="">
					<input type="text" name="searchbar" placeholder="Search...">
					<input type="submit" name = "search" value="Go!">
					</form>
				</li>
			</ul>
		</nav>
	</header>
	<script>
	function menufunction1() {
		document.getElementById("navigationBar").style.display = "inherit";
		document.getElementById("closeMenu").style.display = "inherit";
		document.getElementById("openMenu").style.display = "none";
	}
	
	function menufunction2() {
		document.getElementById("navigationBar").style.display = "none";
		document.getElementById("openMenu").style.display = "inherit";
		document.getElementById("closeMenu").style.display = "none";

		
	}
	</script>
</body>
</html>