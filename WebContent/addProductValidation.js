
	function addProductValidation(addPrLog){
		
		var idProdotto = addPrLog.idProdotto.value;
		var nomeProdotto = addPrLog.nomeProdotto.value;
		var prezzoProdotto = addPrLog.prezzoProdotto.value;
		var nGiocatoriProdotto = addPrLog.nGiocatoriProdotto.value;
		
		var idProdottoOK = /^\w+$/;
		var nomeProdottoOK = /^\w{4,20}$/;
		var prezzoProdottoOK = /^[0-9]{1,4}$/;
		var nGiocatoriProdottoOK = /^[0-9]+$/;
		
		var idMatchOK = idProdotto.match(idProdottoOK);
		var nomeMatchPrOK = nomeProdotto.match(nomeProdottoOK);
		var prezzoMatchOK = prezzoProdotto.match(prezzoProdottoOK);
		var nGMatchOK = nGiocatoriProdotto.match(nGiocatoriProdottoOK);
		
		if(!idMatchOK){
			alert("id non valido");
			console.log(idMatchOK);
			return false;
		}
		
		if(!nomeMatchPrOK){
			alert("Nome non valido");
			console.log(nomeMatchOK);
			return false;
		}
		
		if(!prezzoMatchOK){
			alert("prezzo non valido");
			console.log(prezzoMatchOK);
			return false;
		}
		
		if(!nGMatchOK){
			alert("NGiocatori non valido");
			console.log(nGMatchOK);
			return false;
		}
		console.log(idMatchOK);
		console.log(nomeMatchPrOK);
		console.log(prezzoMatchOK);
		console.log(nGMatchOK);
		return true;
		
	}