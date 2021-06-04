$(function(){

	$("#content").slideDown();
	
});

function ValidarRegistro(){
	var nombre = document.getElementById("Nombre").value;
	var apellido = document.getElementById("Apellido").value;
	var edad = document.getElementById("Edad").value;
	var sexo = $('input[name=Sexo]:checked').val();
	var telefono = document.getElementById("Telefono").value;
	var direccion = document.getElementById("Direccion").value;
	var correo = document.getElementById("Correo").value;
	var usuario = $('input[name=Usuario]:checked').val();
	var pass1 = document.getElementById("Pass1").value;
	var pass2 = document.getElementById("Pass2").value;
	var pass3 = document.getElementById("PassAdm").value;
	var nif = document.getElementById("NIF").value;
	var error="";
	var error2="";

	if(pass1!=pass2){
		document.getElementById("Notif").innerHTML = "Las claves no coinciden";
		return false;
	}else{
		var xmlhttp2 = new XMLHttpRequest();
		xmlhttp2.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	document.getElementById("Notif").innerHTML = this.responseText;
		        if(this.responseText=="error2"){
		        	document.getElementById("Notif").innerHTML = "Cédula ya existente";
		        	return false;
		        }else{
		        	if(usuario=="Empleado"){
			        	var xmlhttp = new XMLHttpRequest();
						xmlhttp.onreadystatechange = function() {
						    if (this.readyState == 4 && this.status == 200) {
						        if(this.responseText=="error1"){
						        	document.getElementById("Notif").innerHTML = "Contraseña de administrador incorrecta";
						        	return false;
						        }else{
						        	var xmlhttp = new XMLHttpRequest();
									xmlhttp.onreadystatechange = function() {
									    if (this.readyState == 4 && this.status == 200) {
									        if(this.responseText=="Error al insertar"){
									        	document.getElementById("Notif").innerHTML = "Ocurrio un error";
									        	return false;
									        }else{
									        	document.getElementById("Notif").innerHTML = "Registro exitoso";
									        	setTimeout(function(){
									        	$("#content").slideUp();
									        		setTimeout(function(){
									        			window.location.replace("../HTML/index.php");
									        		}, 500);
												}, 1000);
									        }
									    }
									};

									xmlhttp.open("GET", "../PHP/Insertar_RegistroPDO.php?Nombre="+nombre+"&Apellido="+apellido+"&Edad="+edad+"&Sexo="+sexo+"&Telefono="+telefono+"&Direccion="+direccion+"&Correo="+correo+"&Usuario="+usuario+"&Pass="+pass1+"&NIF="+nif, true);
									xmlhttp.send();
						        }
						    }
						};

						xmlhttp.open("GET", "../PHP/Validar_PW.php?Codigo="+pass3, true);
						xmlhttp.send();
					}else{
						var xmlhttp = new XMLHttpRequest();
						xmlhttp.onreadystatechange = function() {
						    if (this.readyState == 4 && this.status == 200) {
						        if(this.responseText=="Error al insertar"){
						        	document.getElementById("Notif").innerHTML = "Ocurrio un error";
						        	return false;
						        }else{
						        	document.getElementById("Notif").innerHTML = "Registro exitoso";
						        	setTimeout(function(){
						        		$("#content").slideUp();
						        		setTimeout(function(){
						        			window.location.replace("../HTML/index.php");
						        		}, 500);
									}, 1000);
						        }
						    }
						};

						xmlhttp.open("GET", "../PHP/Insertar_RegistroPDO.php?Nombre="+nombre+"&Apellido="+apellido+"&Edad="+edad+"&Sexo="+sexo+"&Telefono="+telefono+"&Direccion="+direccion+"&Correo="+correo+"&Usuario="+usuario+"&Pass="+pass1+"&NIF="+nif, true);
						xmlhttp.send();
					}

		        }
		    }
		};

		xmlhttp2.open("GET", "../PHP/Validar_NIF.php?NIF="+nif, true);
		xmlhttp2.send();
	
	}
}