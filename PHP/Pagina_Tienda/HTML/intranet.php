<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="icon" href="../Img/laptop.png">
	<title>Intranet</title>
	<link href="https://fonts.googleapis.com/css?family=Modern+Antiqua|Open+Sans+Condensed:300,700" rel="stylesheet">
	<link rel="stylesheet" href="../CSS/intranet.css">
    <script src="../JS/jquery-3.3.1.js"></script>
    <script src="../JS/Intranet.js"></script>
</head>
<body>
    <?php
        session_start();
        if(!isset($_SESSION['Empleado'])) {
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

            <div id="linea1" class="linea"></div>

            <!--CONTENIDO-->

            <div id="content" style="display:block" class="cont-int">
                
                <!--INTRANET-->

                <div class="t-int">
                    <h1>INTRANET</h1>
                </div>

				<!--BOTONES-->
                <br><br>
                <div class="botones-int">
                    <p>Categorias:</p>
                    <button id="CrearCat" value="Crear Categoria">Crear Categoria</button>
                    <button id="ModificarCat" href="#" value="Modificar Categoria">Modificar Categoria</button>
                    <br><br>
                    <p>Subcategorias:</p>
                    <button id="CrearSubCat" href="#" value="Crear Subcategoria">Crear Subcategoria</button>
                    <button id="ModificarSubCat" href="#" value="Modificar Subcategoria">Modificar Subcategoria</button>
                    <br><br>
                    <p>Productos:</p>
                    <button id="CrearPro" href="#" value="Crear Categoria">Crear Producto</button>
                    <button id="ModificarPro" href="#" value="Modificar Categoria">Modificar Producto</button>
                    <br>
                </div>

                <div class="caja-int"></div>

			</div>

             <!-- CONTENIDO CREAR PRODUCTO -->

            <div id="content_cp" style="display:none" class="cont-int">

                <div class="t-int">
                    <h1>CREAR PRODUCTO</h1>
                </div>

                <br><br>

                <!--BOTONES-->

                <div class="botones-int">

                    <form action="../PHP/Crear_Producto.php" onsubmit="" method="post" id="CrearP" enctype="multipart/form-data">
                        <h1>Registro</h1>
                            <br>
                            Codigo: <input type="text" id="CodigoCPro" name="CodigoCPro" placeholder="Codigo" required>
                            <br><br>
                            Categoria: <input type="text" id="CategoriaCPro" name="CategoriaCPro" placeholder="Categoria" required>
                            Subcategoria: <input type="text" id="SubcategoriaCPro" name="SubcategoriaCPro" placeholder="Subcategoria" required>
                            <br> <br>
                            Nombre: <input type="text" id="NombreCPro" name="NombreCPro" placeholder="Nombre" required>
                            Imagen: <input type="file" id="ImagenCP" name="ImagenCP" size="20" required>
                            <br/> <br>
                            Costo: <input type="number" step="any" id="CostoCPro" name="CostoCPro" placeholder="Costo" required>
                            Disponibilidad: <input type="number" id="DisponibleCPro" name="DisponibleCPro" placeholder="Disponible" required>
                            <br/> <br>
                            <textarea rows="10" cols="100" class="caja" id="DescripcionCPro" name="DescripcionCPro" placeholder="Descripcion" required></textarea>
                            <br/>
                            <p style="color: red" id="NotifCP"></p>
                            <br/> <br/>
                            <button type="submit" value="Crear">Crear</button>
                            <br/>
                    </form>

                    <br><br><br>

                    <button id="AtrasCP" href="#" value="Atras">Atrás</button>

                </div>

            </div>

            <!-- FIN CONTENIDO CREAR PRODUCTO -->

            <!-- CONTENIDO MODIFICAR PRODUCTO -->
                        
            <div id="content_mp" style="display:none" class="cont-int">

                <div class="t-int">
                    <h1>MODIFICAR PRODUCTO</h1>
                </div>

                <br><br>

                <!--BOTONES-->
                
                <div class="botones-int">

                    <form action="../PHP/Buscar_Producto.php" method="post" id="BuscarMP">
                        <h1>Busqueda</h1>
                        <br>
                        <input type="text" id="CodigoPro2MP" name="CodigoPro2MP" placeholder="Codigo" required>
                        <button type="submit" value="Buscar">Buscar</button>
                        <br/>
                        <p style="color: red" id="Notif2MP"></p>
                        <br>
                    </form>

                    <form action="../PHP/Modificar_Producto.php" method="post" id="ModificarMP" enctype="multipart/form-data">

                        <p>Datos a modificar:</p>
                        <input style="visibility: hidden;" type="text" id="CodigoProMP" name="CodigoProMP" placeholder="Codigo" readonly>
                        <br><br>
                        <img style="width: 200px; height: 200px;" id="FotoMP" src="">
                        <br/> <br>
                        Categoria:<input type="text" id="CategoriaProMP" name="CategoriaProMP" placeholder="Categoria Nueva" required>
                        Subcategoria:<input type="text" id="SubcategoriaProMP" name="SubcategoriaProMP" placeholder="Subcategoria Nueva" required>
                        <br> <br>
                        Nombre:<input type="text" id="NombreProMP" name="NombreProMP" placeholder="Nombre" required>
                        Imagen:<input type="file" id="ImagenMP" name="ImagenMP" size="20">
                        <br><br>
                        Costo:<input type="number" step="any" id="CostoProMP" name="CostoProMP" placeholder="Costo" required>
                        Disponibilidad:<input type="number" id="DisponibleProMP" name="DisponibleProMP" placeholder="Disponible" required>
                        <br/> <br>
                        Descripcion:<br>
                        <textarea rows="10" cols="100" class="caja" id="DescripcionProMP" name="DescripcionProMP" placeholder="Descripcion" required></textarea>
                        <br/>
                        <p style="color: red" id="NotifMP"></p>
                        <br/> <br/>
                        <button type="submit" value="Modificar">Modificar</button>
                        <br/>

                    </form>
                        
                    <br><br><br>
                        
                    <button id="AtrasMP" href="#" value="Atras">Atrás</button>

                </div>

            </div>

            <!-- FIN CONTENIDO-->

            <!-- CONTENIDO CREAR CATEGORIA -->

            <div id="content_cc" style="display:none" class="cont-int">

                <div class="t-int">
                    <h1>CREAR CATEGORIA</h1>
                </div>

                <br><br>

                <!--BOTONES-->

                <div class="botones-int">

                    <h1>Registro</h1>
                    <br>

                    <form action="../PHP/Crear_Categoria.php" method="post" id="CrearCC" enctype="multipart/form-data">
                       
                        Nombre: <input type="text" id="NombreCC" name="NombreCC" placeholder="Nombre de Categoria" required>
                        <br><br>
                        Imagen: <input type="file" id="ImagenCC" name="ImagenCC" size="20" required>
                        <br/>
                        <p style="color: red" id="NotifCC"></p>
                        <br/> <br/>
                        <button type="submit" value="Crear">Crear</button>
                        <br/>

                    </form>
                        
                    <br><br>
                        
                    <button id="AtrasCC" value="Atras">Atrás</button>

                </div>

            </div>

            <!-- FIN CONTENIDO CREAR CATEGORIA -->

            <!-- CONTENIDO MODIFICAR CATEGORIA -->

            <div id="content_mc" style="display:none" class="cont-int">

                <div class="t-int">
                    <h1>MODIFICAR CATEGORIA</h1>
                </div>

                <br><br>

                <!--BOTONES-->
                
                <div class="botones-int">

                    <form action="../PHP/Buscar_Categoria.php" method="post" id="BuscarMC">
                        <h1>Busqueda</h1>
                        <br>
                        <input type="text" name="NombreMC" id="NombreMC" placeholder="Nombre de la Categoria" required>
                        <button type="submit" value="Buscar">Buscar</button>
                        <br/>
                        <p style="color: red" id="Notif2MC"></p>
                        <br><br>
                    </form> 
                
                    <form action="../PHP/Modificar_Categoria.php" id="ModificarMC" method="post" enctype="multipart/form-data">
                        <input style="visibility: hidden;" type="text" id="TituloMC" name="TituloMC" placeholder="Codigo" readonly>
                        <br>
                        <img style="width: 200px; height: 200px;" id="FotoMC" src="">
                        <br/> <br>
                        Categoria:<input type="text" name="NombreCatMC" id="NombreCatMC" placeholder="Nuevo nombre" required>
                        <br><br>
                        Imagen:<input type="file" id="ImagenMC" name="ImagenMC" size="20">
                        <br>
                        <p style="color: red" id="NotifMC"></p>
                        <br/> <br/>
                        <button  type="submit" value="Cambiar">Cambiar</button>
                        <br/>

                    </form>
                        
                    <br><br><br>
                        
                    <button id="AtrasMC" value="Atras">Atrás</button>
                </div>

            </div>

            <!-- FIN CONTENIDO MODIFICAR CATEGORIA -->

            <!-- CONTENIDO CREAR SUBCATEGORIA -->

            <div id="content_csc" style="display:none" class="cont-int">

                <div class="t-int">
                    <h1>CREAR SUBCATEGORIA</h1>
                </div>

                <br><br>

                <!--BOTONES-->
                
                <div class="botones-int">

                    <form action="../PHP/Crear_Subcategoria.php" method="post" id="CrearCSC" enctype="multipart/form-data">

                        <h1>Registro</h1>
                        <br>

                        Categoria: <input type="text" id="NombreCSC" name="NombreCSC" placeholder="Nombre de Categoria" required>
                        Subcategoria: <input type="text" id="NombreSubCSC" name="NombreSubCSC" placeholder="Nombre de Subcategoria" required>
                        <br><br>
                        Imagen: <input type="file" id="ImagenCSC" name="ImagenCSC" size="20" required>
                        <br/>
                        <p style="color: red" id="NotifCSC"></p>
                        <br/> <br/>
                        <button type="submit" value="Crear">Crear</button>
                        <br/>

                    </form>
                        
                    <br><br><br>
                        
                    <button id="AtrasCSC" href="#" value="Atras">Atrás</button>

                </div>

            </div>

            <!-- FIN CONTENIDO CREAR SUBCATEGORIA -->

            <!-- CONTENIDO MODIFICAR SUBCATEGORIA -->

            <div id="content_msc" style="display:none" class="cont-int">

                <div class="t-int">
                    <h1>MODIFICAR SUBCATEGORIA</h1>
                </div>

                <br><br>

                <!--BOTONES-->

                <div class="botones-int">

                    <form action="../PHP/Buscar_Subcategoria.php" method="post" id="BuscarMSC">
                        <h1>Busqueda</h1>
                        <br>
                        <input type="text" name="NombreMSC" id="NombreMSC" placeholder="Nombre de la Subcategoria" required>
                        <button type="submit" value="Buscar">Buscar</button>
                        <br/>
                        <p style="color: red" id="Notif2MSC"></p>
                        <br><br>
                    </form> 
                
                    <form action="../PHP/Modificar_Subcategoria.php" id="ModificarMSC" method="post" enctype="multipart/form-data">
                        <input style="visibility: hidden;" type="text" id="TituloMSC" name="TituloMSC" placeholder="Codigo" readonly>
                        <br>
                        <img style="width: 200px; height: 200px;" id="FotoMSC" src="">
                        <br/> <br>
                        Categoria:<input type="text" name="NombreCatMSC" id="NombreCatMSC" placeholder="Nuevo nombre" required>
                        Subcategoria:<input type="text" name="NuevoNombreMSC" id="NuevoNombreMSC" placeholder="Nuevo nombre" required>
                        <br><br>
                        Imagen:<input type="file" id="ImagenMSC" name="ImagenMSC" size="20">
                        <br>
                        <p style="color: red" id="NotifMSC"></p>
                        <br/> <br/>
                        <button  type="submit" value="Cambiar">Cambiar</button>
                        <br/>

                    </form>
                        
                    <br><br><br>
                        
                    <button id="AtrasMSC" href="#" value="Atras">Atrás</button>

                </div>

            </div>

            <!-- FIN CONTENIDO MODIFICAR SUBCATEGORIA-->
			 
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