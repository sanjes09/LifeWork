function CambiarSub(str){
	setTimeout(function(){
		setTimeout(function(){
	    	$("#tira").fadeIn();
	    }, 400);
    	var categoria;
		categoria = "<h1 id='categoria'>"+str+"</h1><a id='atras' href='javascript:;' onclick='CambiarAtras();'>Atrás</a>";
		document.getElementById("titulo").innerHTML = categoria;
		
		
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		        document.getElementById("tira").innerHTML = this.responseText;
		        

		    }
		};
		xmlhttp.open("GET", "../PHP/Subcategorias.php?q=" + str, true);
		xmlhttp.send();
    }, 400);
	$("#tira").fadeOut();
}

function CambiarAtras(){
	setTimeout(function(){
		setTimeout(function(){
	    	$("#tira").fadeIn();
	    }, 400);
		var categoria;
		categoria = "<a id='atras' href='javascript:;' onclick='CambiarAtras();'></a><h1 id='categoria'>CATEGORIAS</h1>";
		document.getElementById("titulo").innerHTML = categoria;
		
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		        document.getElementById("tira").innerHTML = this.responseText;
		    }
		};
		xmlhttp.open("GET", "../PHP/Categorias.php", true);
		xmlhttp.send();
	}, 400);
	$("#tira").fadeOut();
}