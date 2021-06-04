<?php
	require("conexion.php");
	$buscar=$_REQUEST["q"];
	try{
		$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
		$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$conexion->exec("Set character set utf8");

		$consulta="select * from Articulo where Categoria like ? or Subcategoria like ? or Nombre like ? ;";
		$resultado=$conexion->prepare($consulta);
		$resultado->execute(array("%" . $buscar . "%","%" . $buscar . "%","%" . $buscar . "%"));

		echo "<div id='titulo' class='titulo'><h1 id='categoria'>" . $buscar . "</h1></div>";
		if($resultado->rowCount()!=0){
			while ($fila=$resultado->fetch(PDO::FETCH_ASSOC)){
				echo "<div class='articulo'><img src='" . $fila["Imagen"] . "' alt=''><form class='info'></p><h4 class='titulo'>" . $fila["Nombre"] . "</h4><p class='descripcion'>" . $fila["Descripcion"] . "</p><br><p class='precio'>Precio: " . $fila["Costo"] . "$</p><p class='disponibilidad' id='disponibilidad" . $fila["Codigo"] . "'>Disponibilidad: " . $fila["Disponible"] . " unidades.</p><br><input type='number' id='Cant" . $fila["Codigo"] . "' min='1' max='" . $fila["Disponible"] . "' placeholder='Cantidad' required><!--BOTON--><a href='javascript:;' onclick='Guardar(" . $fila["Codigo"] . ");' value='Añadir al carrito'>Añadir al carrito</a><p style='color:MediumSeaGreen' id='notif" . $fila["Codigo"] . "'></p></form></div>";
			}
		}else{
			echo "No hay Resultados";
		}

		$resultado->closeCursor();
		
	}catch (Exception $e){
		die('Error: ' . $e->GetMessage());
	}/*finally{
		$conexion=null;
	}*/
?>