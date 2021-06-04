$(function(){

	setTimeout(function(){
    	$("#tira").fadeIn();
    }, 300);

	$("#Inicio").click(function(){
		$("#content_acercade").fadeOut();
        $("#content_contactanos").fadeOut();
        setTimeout(function(){
        	$("#content_inicio").fadeIn();
        	setTimeout(function(){
	        	$("#tira").fadeIn();
	        }, 500);
        }, 300);
	});

	$("#AcercaDe").click(function(){
		$("#content_inicio").fadeOut();
		$("#tira").fadeOut();
        $("#content_contactanos").fadeOut();
        setTimeout(function(){
        	$("#content_acercade").fadeIn();
        }, 300);
	});

	$("#Contactanos").click(function(){
		$("#content_acercade").fadeOut();
		$("#tira").fadeOut();
        $("#content_inicio").fadeOut();
        setTimeout(function(){
        	$("#content_contactanos").fadeIn();
        }, 300);
	});

});