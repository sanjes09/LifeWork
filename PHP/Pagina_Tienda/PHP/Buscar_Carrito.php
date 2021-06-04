<?php
	require("conexion.php");
	if(isset($_SESSION["Cliente"])){
		$buscar=$_SESSION["Cliente"];

		$conexion=mysqli_connect($db_host,$db_usuario,$db_pass,$db_nombre);
		mysqli_set_charset($conexion,"utf8");
		if(mysqli_connect_errno()){
			echo "Error en conectar a BD.";
			exit();
		}

		$consulta1="select * from Carrito where NIF = $buscar;";
		$resultado1=mysqli_query($conexion,$consulta1);
		$enviar="";
		$total=0;
		if(mysqli_num_rows($resultado1)!=0){
			while($fila=mysqli_fetch_array($resultado1,MYSQLI_ASSOC)){
				
				$consulta2="select * from Articulo where Codigo = '" . $fila["CodArt"] . "';";
				$resultado2=mysqli_query($conexion,$consulta2);
				
				$fila2=mysqli_fetch_array($resultado2,MYSQLI_ASSOC);
				$enviar=$enviar . "<div class='articulo'><img src='" . $fila2["Imagen"] . "' alt=''><div class='info'><h4 class='titulo'>" . $fila2["Nombre"] . "</h4><p class='descripcion'>" . $fila2["Descripcion"] . "</p><br><p class='precio'>Precio: " . $fila2["Costo"] . "$</p><p class='cantidad'>Cantidad: " . $fila["Cantidad"] . " unidades</p><p style='color:red;'class='total'> Total: " . $fila2["Costo"]*$fila["Cantidad"] . "$</p><!--BOTON--><a href='javascript:;' onclick='Eliminar(" . $fila2["Codigo"] . ");' value='Eliminar'>Eliminar</a></div></div>";
				$total=$total+$fila2["Costo"]*$fila["Cantidad"];
				
				
			}
		}else{	
			echo "Carrito Vacio";
			exit();
		}
		$enviar=$enviar . "<div>Total es: $total$</div>" . "<div class='comprar'><input type='button' onclick='Comprar()' value='Comprar'></div>";
		echo $enviar;
		mysqli_close($conexion);
	}else{
		echo "Usted no dispone de este servicio. Ingrese como Cliente.";
	}
?>