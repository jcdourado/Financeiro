function carregarContas(){
	var http = new XMLHttpRequest();
	http.onreadystatechange = function (){
		if(http.readyState == 4 && http.status == 200){
			 return (http.responseText.toString());
		}
	};
	http.open("POST", "Data", true);
	http.send();
}

function carregarRecebimentos(){
	var http = new XMLHttpRequest();
	http.onreadystatechange = function (){
		if(http.readyState == 4 && http.status == 200){
			return (http.responseText.toString());
		}
	};
	http.open("POST", "Data", true);
	http.send();
}