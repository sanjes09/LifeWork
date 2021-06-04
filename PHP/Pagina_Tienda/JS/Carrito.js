function Actualizar(srt,cantidad){
	var disponibilidad=document.getElementById("dispnibilidad"+str).value;
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	        document.getElementById("dispnibilidad"+str).innerHTML = this.responseText;
	    }
	};
	xmlhttp.open("GET", "../PHP/Actualizar_Articulo.php?Codigo="+str+"&Cantidad="+cantidad, true);
	xmlhttp.send();
}
function Guardar(str){
	var cantidad=document.getElementById("Cant"+str).value;
	if(str==" "){
		document.getElementById("notif"+str).innerHTML = "Ingrese cantidad a añadir.";
	}else{
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		        document.getElementById("notif"+str).innerHTML = this.responseText;
		    }
		};
		xmlhttp.open("GET", "../PHP/Agregar_Carrito.php?Codigo="+str+"&Cantidad="+cantidad, true);
		xmlhttp.send();
	}
}
function Eliminar(str){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	        alert("Eliminacion Exitosa");
	        window.location.replace("../HTML/Carrito.php");
	    }
	};
	xmlhttp.open("GET", "../PHP/Eliminar_Carrito.php?Codigo="+str, true);
	xmlhttp.send();
}

function Comprar(){
	alert("Compra exitosa!");
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	        window.location.replace("../HTML/Carrito.php");
	    }
	};
	xmlhttp.open("GET", "../PHP/Vaciar_Carrito.php", true);
	xmlhttp.send();
}
