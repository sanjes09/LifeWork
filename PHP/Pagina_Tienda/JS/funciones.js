function Ocultar(){
	var texto;
	texto = "<a id='sali' href='../PHP/Cerrar_Sesion.php'>Cerrar Sesion</a>";
	document.getElementById('Inicio').innerHTML = texto;
}

function Admin(){
	var texto;
	texto = "<input type='password' id='PassAdm' name='Admin' placeholder='Contraseña de Administrador' required>";
	document.getElementById('Admin').innerHTML = texto;
}

function NoAdmin(){
	var texto;
	texto = "<p id='PassAdm'> </p>";
	document.getElementById('Admin').innerHTML = texto;
}

$(function(){

	var imgItems = $(".Slider li").length;
	var imgPos = 1;

	for (var i = 0; i < imgItems; i++) {
		
		$(".pagination").append('<li><span class="fa fa-circle"></span></li>');
	}

	$(".Slider li").hide();
	$(".Slider li:first").show();
	$(".pagination li:first").css({"color":"#5e2129"});

	$(".pagination li").click(pagination);
	$(".right span").click(nextSlider);
	$(".left span").click(prevSlider);

	setInterval(function(){

		nextSlider();
	},5000);
	
	function pagination(){

		var paginationPos = $(this).index()+1;

		if(imgPos != paginationPos){

			$(".Slider li").hide();
			$(".Slider li:nth-child("+ paginationPos +")").fadeIn(1000);
			$(".pagination li").css({"color":"#858585"});
			$(this).css({"color":"#532129"});
			imgPos = paginationPos;
		}
	}
	
	function nextSlider(){

		if(imgPos >= imgItems){

			imgPos = 1;
		}else{

			imgPos++;
		}
		

		$(".Slider li").hide();
		$(".Slider li:nth-child("+ imgPos +")").fadeIn(1000);
		$(".pagination li").css({"color":"#858585"});
		$(".pagination li:nth-child("+ imgPos +")").css({"color":"#532129"});
		
	}
	
	function prevSlider(){

		if(imgPos == 1){

			imgPos = imgItems;
		}else{

			imgPos--;
		}
		

		$(".Slider li").hide();
		$(".Slider li:nth-child("+ imgPos +")").fadeIn(1000);
		$(".pagination li").css({"color":"#858585"});
		$(".pagination li:nth-child("+ imgPos +")").css({"color":"#532129"});
	}
});