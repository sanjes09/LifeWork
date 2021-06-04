<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="icon" href="../Img/laptop.png">
	<title>Articulos</title>
	<link href="https://fonts.googleapis.com/css?family=Modern+Antiqua|Open+Sans+Condensed:300,700" rel="stylesheet">
	<link rel="stylesheet" href="../CSS/articulos.css">
    <script src="../JS/funciones.js"></script>
    <script src="../JS/Cambiar.js"></script>
    <script src="../JS/Carrito.js"></script>
</head>
<body>
	<div class="contenedor">
		<header>

			<div class="encabezado">

                <div class="encabezado">

                <div class="izq">
                    <!--NOMBRE-->
                    
                    <div class="nom">
                        <h2>Tienda Virtual</h2>
                    </div>
                    
                    <!--MENU-->
                    
                    <nav>
                        <ul class="menu">
                            <li>
                                <a href="index.php" id="Inicio">Inicio</a>
                            </li>
                            <li>
                                <a href="carrito.php">Carrito</a>
                            </li>
                        </ul>
                    </nav>

                </div>

                <!--BUSCADOR-->
                
                <div class="buscar">
                     <form action="articulos.php?" method="get">
                        <input type="text" name="q" placeholder="Buscar producto">
                        <input type="submit" value="Buscar"/>
                    </form>
                </div>

                <!--OP USUARIO-->
                
                <div>
                    <?php
                        session_start();
                        if(isset($_SESSION['Cliente'])){
                            echo "<nav><ul class='menu'><li id='usuario' class='user'><a id='config' href='usuario.php'>".$_SESSION["ClienteN"]."</a><ul class='sub'><a id='salir' href='../PHP/Cerrar_Sesion.php'>Cerrar Sesion</a></ul></li></ul></nav>";
                        }else{
                            if(isset($_SESSION['Empleado'])){
                                echo "<nav><ul class='menu'><li id='usuario' class='user'><a id='config' href='usuario.php'>".$_SESSION["EmpleadoN"]."</a><ul class='sub'><a id='intra' href='intranet.php'>INTRANET</a><a id='salir' href='../PHP/Cerrar_Sesion.php'>Cerrar Sesion</a></ul></li></ul></nav>";
                            }else{                      
                                $_SESSION['Temporal']="Temporal";
                                echo "<div id='Inicio' class='user'><a href='iniciarsesion.php'>Iniciar Sesion</a><a href='reg.php'>Registrate</a></div>";
                            }
                        }
                    ?>
                </div>
            </div>
		</header>

		<section class="main">

            <!--LINEA DE SEPARACION-->

            <div class="linea"></div>

            <!--CONTENIDO-->

            <div class="contenido">

               <?php
                    include '../PHP/Mostrar_Articulos.php';
                ?>

            </div>

            <!--LINEA-->

            <div class="linea2"></div>
               
        </section>

        <footer>
            <div class="redes">
                <a href="#"><img src="../Icons/fb.png" alt=""></a>
                <a href="#"><img src="../Icons/ig.png" alt=""></a>
                <a href="#"><img src="../Icons/tw.png" alt=""></a>
            </div>
            <div class="direc">
                <p>Direccion de Tienda Fisica - Telefono</p>
            </div>
        </footer>

    </div>
</body>
</html>