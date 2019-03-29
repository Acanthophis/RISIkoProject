 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name = "viewport" content = "width=device-width, initial-scale=1.0"> 
<!-- <link rel="stylesheet" type="text/css" href="CssFolder/Standard.css"> -->
<title>Insert title here</title>
</head>
<body>
<footer id="test">RISIko Unofficial Web Site</footer>
<div id="runCar">
<img id="carro" alt="" src="Image/weapon.png" style="position: absolute;">
</div>

</body>
<script type="text/javascript">
$(document).ready(function(){
$("#carro").animate({"right":'5%'},20000);
});
</script>

</html>