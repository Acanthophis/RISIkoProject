	
	
	
	function logValidation(logform){
		
		var username = logform.username.value;
		var psw = logform.password.value;
		
		var usernameOK = /^\w{4,}$/;
		var passwordOK = /^\w{4,}$/;
		
		
		var valueMatchName = username.match(usernameOK);
		var valueMatchPSW = psw.match(passwordOK);
		
		if(!valueMatchName){
			alert("Id non valido");
			document.getElementById("nameTXT").focus();
			console.log(valueMatchName);
			return false;
		}
		
		if(!valueMatchPSW){
			alert("Password non valido");
			document.getElementById("pswTXT").focus();
			console.log(valueMatchPSW);
			return false;
		}
		
		console.log(valueMatchName);
		console.log(valueMatchPSW);
		
		return true;
		
	}
	
	
	