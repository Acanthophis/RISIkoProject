<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name = "description" content="Progetto di TSW">
<meta name = "author" content="Gerardo Benevento & Vincenzo Veniero"> 
<meta name = "viewport" content = "width=device-width, initial-scale=1.0"> 
<link rel="stylesheet" type="text/css" href="CssFolder/support.css">
<link rel="stylesheet" type="text/css" href="CssFolder/Standard.css">
<title>News|RISIko</title>
</head>

<body>
	<script>
	function suppFunction() {
		document.getElementById("SupporterIconHP").style.display = "inherit";
		document.getElementById("SupporterIconM").style.display = "inherit";
	}
	
	</script>
	<%@ include file="Header.jsp" %>
	<h1>You Need Support?!?</h1>
	<span id = "SupporterIconHP" class="headphone">
		<img alt="" src="Image/headphonesgreen.png">
		<br>
		<br>
		Chiamaci
		<br>
		<a href="https://t.me/Ventrilo">@Ventrilo</a>
		<br>
		<a href="https://t.me/SHPDoog">@SHPDoog</a>
	</span>
	<span id = "Support">
			<img alt="" src="Image/supporter2.png" height="250px" width="250px">
	</span>
	<span id = "SupporterIconM" class="email">
		<img alt="" src="Image/emailgreen.png">
		<br>
		<br>
		<a href="EmailZone.jsp">Mandaci Una E-mail</a>
	</span>
	<br>
	<button onclick="suppFunction()" class="suppButton">Contact Us!</button>
		<%@ include file="Footer.jsp" %>
</body>

</html>