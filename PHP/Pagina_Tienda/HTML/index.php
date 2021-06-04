<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="icon" href="../Img/laptop.png">
	<title>Tienda Virtual</title>
	<link href="https://fonts.googleapis.com/css?family=Modern+Antiqua|Open+Sans+Condensed:300,700" rel="stylesheet">
	<link rel="stylesheet" href="../CSS/estilos.css">
	<link rel="stylesheet" href="../CSS/font-awesome.css">
	<script src="../JS/jquery-3.3.1.js"></script>
	<script src="../JS/funciones.js"></script>
	<script src="../JS/Cambiar.js"></script>
	<script src="../JS/AJAX.js"></script>
	<script src="../JS/Intranet.js"></script>
</head>
<body>
	
	<div class="contenedor">
		<header id="header">
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
    					        <a id="Inicio">Inicio</a>
    					    </li>
    					    <li>
    					        <a href="">Conocenos</a>
    					        <ul class="sub">
    					            <li><a id="AcercaDe">Acerca de</a></li>
    					            <li><a id="Contactanos">Contactanos</a></li>
    					        </ul>
    					    </li>
    					    <?php
    					   		session_start();
	    					    if(isset($_SESSION['Cliente'])){
		    					    echo '<li><a href="carrito.php">Carrito</a></li>';
			        			}
	        				?>
	    				</ul>
					</nav>

					<!--BUSCADOR-->
				
					
				</div>

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

			<br><br><br>

			<div class="slideshow">
				<ul class="Slider">
					<li>
						<img src="../img/1.jpg" alt="">
					</li>
					
					<li>
						<img src="../img/2.jpg" alt="">
					</li>
					
					<li>
						<img src="../img/3.jpg" alt="">
					</li>
					
					<li>
						<img src="../img/4.jpg" alt="">
					</li>

					<li>
						<img src="../img/5.jpg" alt="">
					</li>

					<li>
						<img src="../img/6.jpg" alt="">
					</li>
				</ul>
				
				<ol class="pagination">
					
				</ol>

				<div class="left">
					<span class="fa fa-chevron-left"></span>
				</div>

				<div class="right">
					<span class="fa fa-chevron-right"></span>
				</div>

			</div>
		</header>

		<div id="content_inicio" style="display:block" class="main">
		            
		    <!--LINEA DE SEPARACION ALTA-->

		    <div class='linea'></div>
		            
		    <!--CONTENIDO-->

		    <div class='contenido'>

		        <!--CATEGORIAS-->

		        <div id='titulo' class='titulo'>
		            
		            <a id='atras' href='javascript:;' onclick='CambiarAtras();'></a>
		            <h1 id='categoria'>CATEGORIAS</h1>
		        
		        </div>
		                
		        <div id='tira' style='display:none' class='tira'>
		            
		            <?php
		                include '../PHP/Categorias.php';
		            ?>

		        </div>

		    </div>

		    <aside class="noticias">

		        <div class="tit">
		            
		            <h1>NOTICIAS</h1>
		        
		        </div>

		            <div class="not">
		                
		                <img src="../Img/carro.jpg" alt="">
		                <h3>Titulo de la Noticia</h3>
		                <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Sed dolore vitae totam hic esse possimus enim voluptates in ipsam officia?</p>
		            
		            </div>
		                
		            <div class="not">
		                
		                <img src="../Img/carro2.jpg" alt="">
		                <h3>Titulo de la Noticia</h3>
		                <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Sed dolore vitae totam hic esse possimus enim voluptates in ipsam officia?</p>
		            
		            </div>
		            
		    </aside>

		</div>

		<!-- FIN CONTENIDO DEL INICIO -->

		<!-- CONTENIDO DE ACERCA DE -->

		<section id="content_acercade" style="display:none" class="main">
		        
		    <!--LINEA DE SEPARACION-->

		    <div class="linea"></div>

		    <!--CONTENIDO-->

		    <div class="cont-acer">
		                
		        <!--ACERCA DE-->

		        <div class="t-acer">
		                
		            <h1>ACERCA DE</h1>
		            
		        </div>

		        <div class="caja-acer">
		                    
		            <!--DESCRIPCION-->

		            <div class="text-acer">
		                    
		                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. A repudiandae explicabo quasi deserunt esse voluptatem quos non dicta sint illum provident adipisci laudantium et amet, doloribus officia porro fugit aut tenetur aliquam soluta quidem eaque consequatur dolore. Sint id vero quod voluptas, nulla assumenda quia, enim, facere ullam atque, mollitia? Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique rem minima, quod nobis, eaque id quae! Optio officia sed, iure voluptate error iste corrupti nemo nesciunt labore voluptates illo dolorum cupiditate dolor, deleniti vitae natus, dignissimos odit perspiciatis consequatur delectus a, debitis inventore! Ex quam, fugit tenetur, maiores perferendis tempora?</p> 

		                <br/>

		                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Praesentium illo, impedit est animi deleniti iusto omnis, quibusdam neque eum, voluptas ea fugit magni adipisci. Ipsum, incidunt obcaecati quas autem labore harum molestias reiciendis laboriosam facere molestiae? Quod vero possimus soluta iure, reiciendis eaque blanditiis? Repudiandae dolores praesentium eius provident eos sit reiciendis, dolorum quas possimus cum magni molestiae, sunt aliquid quo distinctio in maxime porro. Perspiciatis ipsum molestias impedit aspernatur, veritatis earum ullam animi corporis, magni labore alias totam necessitatibus. Mollitia nihil tenetur, vel accusamus nisi incidunt aspernatur odio sequi neque modi alias nulla, maiores ipsa maxime expedita. Veritatis, totam.</p>

		                <br/>
		                        
		                <h4>Dirección</h4>

		                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nemo, optio.</p>

		                <br/>

		                <h4>Telefonos</h4>

		                <p>0212-123.45.67 - 0400-987.65.43</p>

		            </div>

		            <!--IMAGENEN-->

		            <div class="img-acer">
		                    
		                <img src="../Img/acercade.jpg" alt="">
		                
		            </div>

		        </div>

		    </div>

		</section>

		<!-- FIN CONTENIDO DE ACERCA DE -->

		<!-- CONTENIDO DEL CONTACTANOS -->

		<div id="content_contactanos" style="display:none" class="main">

		    <!--LINEA DE SEPARACION-->

		    <div class="linea"></div>

		    <!--CONTENIDO-->

		    <div class="cont-acto">
		                
		        <!--CONTACTO-->

		        <div class="t-acto">
		            
		            <h1>CONTACTANOS</h1>
		        
		        </div>

		        <div class="caja-acto">

		            <!--DATOS DE USUARIO-->
		                    
		            <input type="text" placeholder="Nombre">
		            <input type="email" placeholder="Correo">

		            <!--MENSAJE-->

		            <textarea name="Mensaje" id="Mensaje" placeholder="Mensaje"></textarea>

		            <!--BOTON-->

		            <input type="submit" value="Enviar">

		        </div>

		    </div>

		</div>

		<!--LINEA DE SEPARACION BAJA-->

		<div class='linea2'></div>

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