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
<link rel="stylesheet" type="text/css" href="CssFolder/home.css">

<title>Home|RISIko</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<%

%>
		
	<div id="description">
		<article id = "risikodescription">
			<h3>Benventuo nella pagina Ufficiale di RISIko UNofficial web site</h3>
			<p>
			Questo sito internet offre la possibiltà di poter esplorare il fantastico mondo del gioco RISIko, divertendosi con le nuovissime
			espensioni che ti intretteranno con i tuoi amici.
			</p>
			<article>
				Non perderti il video dimostrativo del gioco qui sotto e non aver paura di dover contattare i nostri sviluppatori per aiuto o solo per conoscerli.
																	   				 
			</article>
			<img alt="" src="Image/risiko.png">
			<br>
			<p id = "newsParagh">
			
			</p>
			
		</article>
	</div>	
<%@ include file="Footer.jsp" %>

<script type="text/javascript">
				$(document).ready(function activeTime(){
					setInterval(newsP,3000);
				});
				
					function newsP() {
						var xhttp;
						  if (window.XMLHttpRequest) {
						    xhttp = new XMLHttpRequest();
						    } else {
						    xhttp = new ActiveXObject("Microsoft.XMLHTTP");
						  }
						xhttp.onreadystatechange = function(){
							 if (this.readyState == 4 && this.status == 200){
								newsAlert(this);
							 } 
						};
						xhttp.open("GET","newsCatalog.xml",true);
						xhttp.send();
					}
					
					function newsAlert(xml){
						 var i = Math.floor((Math.random() * 6) + 0);
						 var xmlDoc = xml.responseXML;
						 var x = xmlDoc.getElementsByTagName("news");
							document.getElementById("newsParagh").innerHTML = x[i].getElementsByTagName("notizia")[0].childNodes[0].nodeValue + "<br>" +
							x[i].getElementsByTagName("source")[0].childNodes[0].nodeValue + "<br>";
						
					}
				
					</script>


</body>
</html>