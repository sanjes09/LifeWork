<?php
	require("conexion.php");
	$cantidad=$_REQUEST["Cantidad"];
	$codigo=$_REQUEST["Codigo"];
	try{
		$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
		$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$conexion->exec("Set character set utf8");

		$consulta="select * from Carrito where NIF = '?' ;";
		$resultado=$conexion->prepare($consulta);
		$resultado->execute(array($buscar));
		$enviar = "";
		if($resultado->rowCount()!=0){
			while ($fila=$resultado->fetch(PDO::FETCH_ASSOC)){
				$consulta="select * from Articulo where Codigo = '?';";
				$resultado=$conexion->prepare($fila["CodArt"]);
				$resultado->execute(array($fila["CodArt"]));
				while ($fila2=$resultado->fetch(PDO::FETCH_ASSOC)){
					$enviar = $enviar . "<div class='articulo'><img src='" . $fila2["Imagen"] . "' alt=''><div class='info'><h4 class='titulo'>" . $fila2["Nombre"] . "</h4><p class='descripcion'>" . $fila2["Descripcion"] . "</p><p class='precio'>" . $fila2["Costo"] . "$</p><p class='cantidad'>" . $fila["Cantidad"] . " unidades</p><p class='total'>" . $fila2["Costo"]*$fila["Cantidad"] . "$</p><!--BOTON--><input type='submit' value='Eliminar'></div></div>";
				}
			}
			echo $enviar;
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