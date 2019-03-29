



	function registerValidation(registerform){
		
		var nameform = registerform.name.value;
		var surnameform = registerform.surname.value;
		var emailform = registerform.email.value;
		var idform = registerform.id.value;
		var pswform = registerform.password.value;
		
		var nameOK = /^[A-Za-z]+$/;
		var surnameOK = /^[A-Za-z]+$/;
		var emailOK = /^[a-zA-Z0-9_\.\-]+\@[a-zA-Z]+\.[a-zA-Z]+$/;
		//var emailOK = /^\w+([\._\-]*\w+)*@\w+([\.\-]?)*(\.\w+)+ $/;
		var idOK = /^\w{4,}$/;
		var pswOK = /^[0-9]*\w{6,}$/;
		
		var nameMatchOK = nameform.match(nameOK);
		var surnameMatchOK = surnameform.match(surnameOK);
		var emailMatchOK = emailform.match(emailOK);
		var idMatchOK = idform.match(idOK);
		var pswMatchOK = pswform.match(pswOK);
		
		if(!nameMatchOK){
			alert("Nome non valido");
			document.getElementById("nameTXT").focus();
			console.log(nameMatchOK);
			return false;
		}
		
		if(!surnameMatchOK){
			alert("Cognome non valido");
			document.getElementById("surnameTXT").focus();
			console.log(surnameMatchOK);
			return false;
		}
		
		
		if(!emailMatchOK){
			alert("Email non valido");
			document.getElementById("emailTXT").focus();
			console.log(emailMatchOK);
			return false;
		}
		
		if(!idMatchOK){
			alert("Id non valido");
			document.getElementById("idTXT").focus();
			console.log(idMatchOK);
			return false;
		}
		
		if(!pswMatchOK){
			alert("Password non valida");
			document.getElementById("pswTXT").focus();
			console.log(pswMatchOK);
			return false;
		}
		
		console.log(nameMatchOK);
		console.log(surnameMatchOK);
		console.log(emailMatchOK);
		console.log(idMatchOK);
		console.log(pswMatchOK);
		
		
		return true;
		
	}