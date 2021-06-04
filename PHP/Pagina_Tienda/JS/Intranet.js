$(function(){

	$("#CrearCat").click(function(){
		setTimeout(function(){
	    	$("#content_cc").fadeIn();
	    }, 400);
		$("#content").fadeOut();
	});

	$("#CrearCC").submit(function(event){
		event.preventDefault();
        var urll = $(this).attr("action"); //get form action url
        
        $.ajax({
            url: urll, // Url to which the request is send
            type: "POST",             // Type of request to be send, called as method
            data: new FormData(this), // Data sent to server, a set of key/value pairs (i.e. form fields and values)
            contentType: false,       // The content type used when sending data to the server.
            cache: false,             // To unable request pages to be cached
            processData:false,        // To send DOMDocument or non processed data file it is set to false
            success: function(data)   // A function to be called if request succeeds
                {
		        $("#NotifCC").html(data);
                }
        });
    });

	$("#ModificarCat").click(function(){
		setTimeout(function(){
	    	$("#content_mc").fadeIn();
	    }, 400);
		$("#content").fadeOut();
	});

	$("#BuscarMC").submit(function(event){
		event.preventDefault();
        var urll = $(this).attr("action"); //get form action url
        $("#NotifMC").html(" ");
        $.ajax({
            url: urll, // Url to which the request is send
            type: "POST",             // Type of request to be send, called as method
            data: new FormData(this), // Data sent to server, a set of key/value pairs (i.e. form fields and values)
            contentType: false,       // The content type used when sending data to the server.
            cache: false,             // To unable request pages to be cached
            processData:false,        // To send DOMDocument or non processed data file it is set to false
            success: function(data)   // A function to be called if request succeeds
                {
	            if(data.split("|")[0]!="No se consigue la Categoria."){
	            	$("#Notif2MC").html("");
			        $("#ImagenMC").val("");
	            	$("#TituloMC").val(data.split("|")[0]);
			        $("#NombreCatMC").val(data.split("|")[0]);
			        $("#FotoMC").attr("src",data.split("|")[1]);
			        $("#NombreCatMC").attr("readonly",false);
			    }else{
			    	$("#Notif2MC").html(data.split("|")[0]);
			    	$("#TituloMC").val("");
			    	$("#ImagenMC").val("");
			        $("#NombreCatMC").val("");
			        $("#NombreCatMC").attr("readonly",true);
			        $("#FotoMC").attr("src","#");
			    }
                }
        });
    })
	$("#ModificarMC").submit(function(event){
		event.preventDefault();
        var urll = $(this).attr("action"); //get form action url
        
        $.ajax({
            url: urll, // Url to which the request is send
            type: "POST",             // Type of request to be send, called as method
            data: new FormData(this), // Data sent to server, a set of key/value pairs (i.e. form fields and values)
            contentType: false,       // The content type used when sending data to the server.
            cache: false,             // To unable request pages to be cached
            processData:false,        // To send DOMDocument or non processed data file it is set to false
            success: function(data)   // A function to be called if request succeeds
                {
		        $("#NotifMC").html(data);
		        if(data.split("|")[0]=="Cambio Exitoso!"){
			    	$("#TituloMC").val("");
			    	$("#ImagenMC").val("");
			        $("#NombreCatMC").val("");
			        $("#NombreCatMC").attr("readonly",true);
			        $("#FotoMC").attr("src","#");
			    }
                }
        });
    });

	$("#CrearSubCat").click(function(){
		setTimeout(function(){
	    	$("#content_csc").fadeIn();
	    }, 400);
		$("#content").fadeOut();
	});

	$("#CrearCSC").submit(function(event){
		event.preventDefault();
        var urll = $(this).attr("action"); //get form action url
        
        $.ajax({
            url: urll, // Url to which the request is send
            type: "POST",             // Type of request to be send, called as method
            data: new FormData(this), // Data sent to server, a set of key/value pairs (i.e. form fields and values)
            contentType: false,       // The content type used when sending data to the server.
            cache: false,             // To unable request pages to be cached
            processData:false,        // To send DOMDocument or non processed data file it is set to false
            success: function(data)   // A function to be called if request succeeds
                {
		        $("#NotifCSC").html(data);
                }
        });
    });

	$("#ModificarSubCat").click(function(){
		setTimeout(function(){
	    	$("#content_msc").fadeIn();
	    }, 400);
		$("#content").fadeOut();
	});

	$("#BuscarMSC").submit(function(event){
		event.preventDefault();
        var urll = $(this).attr("action"); //get form action url
        $("#NotifMSC").html(" ");
        $.ajax({
            url: urll, // Url to which the request is send
            type: "POST",             // Type of request to be send, called as method
            data: new FormData(this), // Data sent to server, a set of key/value pairs (i.e. form fields and values)
            contentType: false,       // The content type used when sending data to the server.
            cache: false,             // To unable request pages to be cached
            processData:false,        // To send DOMDocument or non processed data file it is set to false
            success: function(data)   // A function to be called if request succeeds
                {
	            if(data.split("|")[0]!="No se consigue la Subcategoria."){
	            	$("#Notif2MSC").html("");
			        $("#ImagenMSC").val("");
	            	$("#TituloMSC").val(data.split("|")[0]);
			        $("#NuevoNombreMSC").val(data.split("|")[0]);
			        $("#NombreCatMSC").val(data.split("|")[1]);
			        $("#FotoMSC").attr("src",data.split("|")[2]);
			        $("#NuevoNombreMSC").attr("readonly",false);
			        $("#NombreCatMSC").attr("readonly",false);
			    }else{
			    	$("#Notif2MSC").html(data.split("|")[0]);
			    	$("#TituloMSC").val("");
			    	$("#ImagenMSC").val("");
			    	$("#NuevoNombreMSC").val("");
			        $("#NombreCatMSC").val("");
			        $("#NuevoNombreMSC").attr("readonly",true);
			        $("#NombreCatMSC").attr("readonly",true);
			        $("#FotoMSC").attr("src","#");
			    }
                }
        });
    })

	$("#ModificarMSC").submit(function(event){
		event.preventDefault();
        var urll = $(this).attr("action"); //get form action url
        
        $.ajax({
            url: urll, // Url to which the request is send
            type: "POST",             // Type of request to be send, called as method
            data: new FormData(this), // Data sent to server, a set of key/value pairs (i.e. form fields and values)
            contentType: false,       // The content type used when sending data to the server.
            cache: false,             // To unable request pages to be cached
            processData:false,        // To send DOMDocument or non processed data file it is set to false
            success: function(data)   // A function to be called if request succeeds
                {
		        $("#NotifMSC").html(data);
		        if(data.split("|")[0]=="Cambio Exitoso!"){
			    	$("#TituloMSC").val("");
			    	$("#ImagenMSC").val("");
			    	$("#NuevoNombreMSC").val("");
			        $("#NombreCatMSC").val("");
			        $("#NuevoNombreMSC").attr("readonly",true);
			        $("#NombreCatMSC").attr("readonly",true);
			        $("#FotoMSC").attr("src","#");
			    }
                }
        });
    });

	$("#CrearPro").click(function(){
		setTimeout(function(){
	    	$("#content_cp").fadeIn();
	    }, 400);
		$("#content").fadeOut();
	});

	$("#CrearP").submit(function(event){
		event.preventDefault();
        var urll = $(this).attr("action"); //get form action url
        
        $.ajax({
            url: urll, // Url to which the request is send
            type: "POST",             // Type of request to be send, called as method
            data: new FormData(this), // Data sent to server, a set of key/value pairs (i.e. form fields and values)
            contentType: false,       // The content type used when sending data to the server.
            cache: false,             // To unable request pages to be cached
            processData:false,        // To send DOMDocument or non processed data file it is set to false
            success: function(data)   // A function to be called if request succeeds
                {
		        $("#NotifCP").html(data);
                }
        });
    });

	$("#ModificarPro").click(function(){
		setTimeout(function(){
	    	$("#content_mp").fadeIn();
	    }, 400);
		$("#content").fadeOut();
	});

	$("#BuscarMP").submit(function(event){
		event.preventDefault();
        var urll = $(this).attr("action"); //get form action url
        
        $.ajax({
            url: urll, // Url to which the request is send
            type: "POST",             // Type of request to be send, called as method
            data: new FormData(this), // Data sent to server, a set of key/value pairs (i.e. form fields and values)
            contentType: false,       // The content type used when sending data to the server.
            cache: false,             // To unable request pages to be cached
            processData:false,        // To send DOMDocument or non processed data file it is set to false
            success: function(data)   // A function to be called if request succeeds
                {
	                if(data.split("|")[0]!="No se consigue el producto."){
	                	$("#NotifMP").html("");
	                	$("#Notif2MP").html("");
				        $("#CodigoProMP").val(data.split("|")[0]);
				        $("#CategoriaProMP").val(data.split("|")[1]);
				        $("#SubcategoriaProMP").val(data.split("|")[2]);
				        $("#NombreProMP").val(data.split("|")[3]);
				        $("#CostoProMP").val(data.split("|")[4]);
				        $("#DisponibleProMP").val(data.split("|")[5]);
				        $("#DescripcionProMP").val(data.split("|")[6]);
				        $("#ImagenMP").val("");
				        $("#FotoMP").attr("src",data.split("|")[7]);
				        $("#CategoriaProMP").attr("readonly",false);
				        $("#SubcategoriaProMP").attr("readonly",false);
				        $("#NombreProMP").attr("readonly",false);
				        $("#CostoProMP").attr("readonly",false);
				        $("#DisponibleProMP").attr("readonly",false);
				        $("#DescripcionProMP").attr("readonly",false);
				    }else{
				    	$("#NotifMP").html("");
	                	$("#Notif2MP").html("No Existe el producto");
				    	$("#FotoMP").attr("src","#");
				    	$("#CodigoProMP").val("");
				        $("#CategoriaProMP").val("");
				        $("#SubcategoriaProMP").val("");
				        $("#NombreProMP").val("");
				        $("#CostoProMP").val("");
				        $("#ImagenMP").val("");
				        $("#DisponibleProMP").val("");
				        $("#DescripcionProMP").val("");
				        $("#CategoriaProMP").attr("readonly",true);
				        $("#SubcategoriaProMP").attr("readonly",true);
				        $("#NombreProMP").attr("readonly",true);
				        $("#CostoProMP").attr("readonly",true);
				        $("#DisponibleProMP").attr("readonly",true);
				        $("#DescripcionProMP").attr("readonly",true);
				    }
                }
        });
    });

    $("#ModificarMP").submit(function(event){
		event.preventDefault();
        var urll = $(this).attr("action"); //get form action url
        
        $.ajax({
            url: urll, // Url to which the request is send
            type: "POST",             // Type of request to be send, called as method
            data: new FormData(this), // Data sent to server, a set of key/value pairs (i.e. form fields and values)
            contentType: false,       // The content type used when sending data to the server.
            cache: false,             // To unable request pages to be cached
            processData:false,        // To send DOMDocument or non processed data file it is set to false
            success: function(data)   // A function to be called if request succeeds
                {
		        $("#NotifMP").html(data);
		        if(data=="Modificacion exitosa"){
			    	$("#FotoMP").attr("src","#");
			    	$("#CodigoProMP").val("");
			        $("#CategoriaProMP").val("");
			        $("#SubcategoriaProMP").val("");
			        $("#NombreProMP").val("");
			        $("#CostoProMP").val("");
			        $("#ImagenMP").val("");
			        $("#DisponibleProMP").val("");
			        $("#DescripcionProMP").val("");
			        $("#CategoriaProMP").attr("readonly",true);
			        $("#SubcategoriaProMP").attr("readonly",true);
			        $("#NombreProMP").attr("readonly",true);
			        $("#CostoProMP").attr("readonly",true);
			        $("#DisponibleProMP").attr("readonly",true);
			        $("#DescripcionProMP").attr("readonly",true);
			    }
                }
        });
    });

    $("#AtrasCP").click(function(){
		setTimeout(function(){
	    	$("#content").fadeIn();
	    }, 400);
		$("#content_cp").fadeOut();
	});
	$("#AtrasMP").click(function(){
		setTimeout(function(){
	    	$("#content").fadeIn();
	    }, 400);
		$("#content_mp").fadeOut();
	});
	$("#AtrasCC").click(function(){
		setTimeout(function(){
	    	$("#content").fadeIn();
	    }, 400);
		$("#content_cc").fadeOut();
	});
	$("#AtrasMC").click(function(){
		setTimeout(function(){
	    	$("#content").fadeIn();
	    }, 400);
		$("#content_mc").fadeOut();
	});
	$("#AtrasCSC").click(function(){
		setTimeout(function(){
	    	$("#content").fadeIn();
	    }, 400);
		$("#content_csc").fadeOut();
	});
	$("#AtrasMSC").click(function(){
		setTimeout(function(){
	    	$("#content").fadeIn();
	    }, 400);
		$("#content_msc").fadeOut();
	});
});