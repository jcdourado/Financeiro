function carregarContas(){
	var http = new XMLHttpRequest();
	http.onreadystatechange = function (){
		if(http.readyState == 4 && http.status == 200){
			 return (http.responseText.toString());
		}
	};
	http.open("POST", "Data", true);
	http.send();
	return http.onreadystatechange();
}

function carregarRecebimentos(){
	var http = new XMLHttpRequest();
	http.onreadystatechange = function (){
		if(http.readyState == 4 && http.status == 200){
			return (http.responseText.toString());
		}
	};
	http.open("GET", "Data", true);
	http.send();
	return http.onreadystatechange();
}