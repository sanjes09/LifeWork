<?php 
	require("conexion.php");
	$Codigo=$_POST["CodigoPro2MP"];
	$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
	$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	$conexion->exec("Set character set utf8");
	
	try{
		$consulta="select * from Articulo where Codigo = ? ;";
		$resultado=$conexion->prepare($consulta);
		$resultado->execute(array($Codigo));
		if($resultado->rowCount()!=0){
			$fila=$resultado->fetch(PDO::FETCH_ASSOC);
			echo $fila["Codigo"]."|".$fila["Categoria"]."|".$fila["Subcategoria"]."|".$fila["Nombre"]."|".$fila["Costo"]."|".$fila["Disponible"]."|".$fila["Descripcion"]."|".$fila["Imagen"];
		}else{
			echo "No se consigue el producto.";
		}
	}catch (Exception $e){
		die('Error: ' . $e->GetMessage());
	}

?>