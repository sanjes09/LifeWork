document.getElementById("nombreP").innerHTML=perfil.nombre;
document.getElementById("ciP").innerHTML=perfil.ci;
document.getElementById("generoP").innerHTML=perfil.genero;
document.getElementById("fechaP").innerHTML=perfil.fecha_nacimiento;
document.getElementById("descripcionP").innerHTML=perfil.descripcion;
document.getElementById("colorP").innerHTML=perfil.color;
document.getElementById("libroP").innerHTML=perfil.libro;
document.getElementById("musicaP").innerHTML=perfil.musica;
var str = " ";
for(var i=0; i<perfil.video_juego.length; i++){
	str+=perfil.video_juego[i];
	if(i<perfil.video_juego.length-1){
		str+=", ";
	}
}
document.getElementById("juegoP").innerHTML=str;
str = " ";
for(var i=0; i<perfil.lenguajes.length; i++){
	str+=perfil.lenguajes[i];
	if(i<perfil.lenguajes.length-1){
		str+=", ";
	}
}
document.getElementById("lenguajesP").innerHTML=str;
document.getElementById("emailP").innerHTML=perfil.email;
document.getElementById("emailP").href="mailto:"+perfil.email;


document.getElementById("ciC").innerHTML=config.ci;
document.getElementById("generoC").innerHTML=config.genero;
document.getElementById("fechaC").innerHTML=config.fecha_nacimiento;
document.getElementById("colorC").innerHTML=config.color;
document.getElementById("libroC").innerHTML=config.libro;
document.getElementById("musicaC").innerHTML=config.musica;
document.getElementById("juegosC").innerHTML=config.video_juego;
document.getElementById("lenguajesC").innerHTML=config.lenguajes;
document.getElementById("comunicarseC").innerHTML=config.como_comunicarse;