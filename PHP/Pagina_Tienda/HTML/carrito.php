<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="icon" href="../Img/laptop.png">
    <title>Carrito</title>
    <link href="https://fonts.googleapis.com/css?family=Modern+Antiqua|Open+Sans+Condensed:300,700" rel="stylesheet">
    <link rel="stylesheet" href="../CSS/carrito.css">
    <script src="../JS/jquery-3.3.1.js"></script>
    <script src="../JS/Carrito.js"></script>
</head>
<body>
    <?php
        session_start();
        if((!isset($_SESSION['Cliente'])) and (!isset($_SESSION['Empleado']))){
            header("Location:index.php");
        }
    ?>
    <div class="contenedor">
        <header>
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
                                <a href="index.php">Inicio</a>
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

            <!--LINEA DE SEPARACION ALTA-->

            <div class="linea"></div>

            <!--CARRITO-->

            <div class="t">
                <h1>CARRITO</h1>
            </div>

            <!--CONTENIDO-->

            <div id="content" class="contenido">
                
                <?php
                    include '../PHP/Buscar_Carrito.php';
                ?>

            </div>

            <!--LINEA DE SEPARACION BAJA-->

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