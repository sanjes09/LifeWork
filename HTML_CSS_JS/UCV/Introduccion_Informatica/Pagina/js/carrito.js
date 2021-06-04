var img = [0, "pictures/chiabe.jpg", "pictures/frida.jpg", "pictures/jobs.jpg", "pictures/milucha.jpg", "pictures/eatpraylove.jpg", "pictures/kurt.jpg", "pictures/davinci.jpg", "pictures/goodliar.jpg", "pictures/gosetaw.jpg","pictures/hunger.jpg","pictures/tokilla.jpg","pictures/jumper.jpg","pictures/carrie.jpg","pictures/cthulhu.jpg","pictures/dracula.jpg","pictures/eso.jpg","pictures/wwz.jpg","pictures/thestrain.jpg","pictures/elgatodelsombrero.jpg","pictures/elqueso.jpg","pictures/portada-miguel-luna-contra-bestia-bosque.jpg","pictures/mecomiunpie.jpg","pictures/principito.jpg","pictures/reymocho.jpg","pictures/lachicadeltren.jpg","pictures/esfera.jpg","pictures/fortalezadigital.jpg","pictures/shining.jpg","pictures/tormentadearena.jpg","pictures/zombieroom.jpg"];
var precios = [0, 150, 200, 300, 200, 250, 250, 200, 250, 250, 450, 300, 250, 200, 150, 150, 250, 200, 470, 220, 150, 200, 125, 200, 150, 300, 300, 250, 250, 300, 300];
var IVA = 0.12;

var sesion= false;   


function alerta(){
	alert("¡Compra Exitosa!");
	document.getElementById("tablaTotal").innerHTML ='<tr><td></td><td></td></tr>';
	document.getElementById("Subtotal").innerHTML= 0;
	document.getElementById("IVA").innerHTML= 0;
	document.getElementById("Total").innerHTML= 0;
	document.getElementById("descu").innerHTML=0;
}

function cambiaApass(caja) {
    var nuevo=document.createElement("input");
    nuevo.setAttribute("type","password");
    nuevo.setAttribute("name",caja.name);
    nuevo.setAttribute("value","");
    document.formulario.replaceChild(nuevo,caja);
    nuevo.focus();
}

function divLogin(){ 
    sesion = true; 
    document.getElementById("redirect").innerHTML='<a href="compras.html" class="botondes" onclick="validar()"><img src="pictures/commerce.png" alt=home style="width: 24px; height: 24px;"/> Comprar</a>'  
    alert("Sesión iniciada exitosamente");
    document.getElementById("sesion").style.display="none";
}

function validar() {
    if(sesion==false){
    	alert("Debe iniciar sesión para poder comprar");
    }
}

function addToCart(x) {
	
	var tablaTotal = document.getElementById("tablaTotal").innerHTML;
	var subtotal= parseFloat(document.getElementById("Subtotal").innerHTML);
	subtotal=subtotal+parseFloat(precios[x]);
	var iva= parseFloat(document.getElementById("IVA").innerHTML);
	iva= subtotal*0.12;
	var total= parseFloat(document.getElementById("Total").innerHTML);
	total=subtotal+iva;
	total=total.toFixed(2);
	if(total>500) {total=total-total*0.1; total=total.toFixed(2); document.getElementById("descu").innerHTML=10;}
	if(total>3000) {total=total-total*0.25; total=total.toFixed(2); document.getElementById("descu").innerHTML=25;}
	document.getElementById("tablaTotal").innerHTML = tablaTotal + '<tr><td><img id="imglista" style="margin-left:20px;" src="' +img[x]+ '"></td><td>' +precios[x]+ '</td></tr>';
	document.getElementById("Subtotal").innerHTML= subtotal;
	document.getElementById("IVA").innerHTML= iva;
	document.getElementById("Total").innerHTML= total;
}
