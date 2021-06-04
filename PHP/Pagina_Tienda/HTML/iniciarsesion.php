<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="icon" href="../Img/laptop.png">
    <title>Iniciar Sesion</title>
    <link href="https://fonts.googleapis.com/css?family=Modern+Antiqua|Open+Sans+Condensed:300,700" rel="stylesheet">
    <link rel="stylesheet" href="../CSS/iniciarsesion.css">
    <script src="../JS/jquery-3.3.1.js"></script>
    <script>
        $(function(){
            $("#content").slideDown();
            $("#IniciarS").submit(function(event){
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
                        $("#Notif").html(data);
                        if(data=="Sesion iniciada"){
                            setTimeout(function(){
                                $("#content").slideUp();
                                setTimeout(function(){
                                    window.location.replace("../HTML/index.php");
                                }, 500);
                            }, 1000);
                        }
                        
                        }
                });
            });
        });
    </script>
</head>
<body>
    <?php
        session_start();
        if(isset($_SESSION['Cliente']) or isset($_SESSION['Empleado'])) {
            header("Location:index.php");
        }
    ?>
    
    <div id="content" style="display: none;" class="contenedor">

        <!--TITULO-->

        <h1 style="color:white">Inicio de Sesión</h1>

        <form action="../PHP/Iniciar_sesionPDO.php" metod="post" id="IniciarS" class="caja">

            <!--DATOS-->

            <input type="text" name="NIF" id="NIF" placeholder="Cedula" maxlength="8" required>
            <input type="password" name="Pass" id="Pass" placeholder="Contraseña" required>

            <!--BOTON-->
            <p style="color: red" id="Notif"></p>
            <br/> <br/>

            <input type="submit" value="Enviar">
        </form>

        <!--REGISTRO-->

        <p>No tienes cuenta?  </p><a href="reg.php">Registrate</a>
        <br/>

        <!--INICIO-->

        <a class="inicio" href="index.php">Inicio</a>

    </div>
</body>
</html>