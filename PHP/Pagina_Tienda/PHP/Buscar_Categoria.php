<?php 
	require("conexion.php");
	$Nombre=$_POST["NombreMC"];
	$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
	$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	$conexion->exec("Set character set utf8");
	
	try{
		$consulta="select * from Categoria where Nombre = ? ;";
		$resultado=$conexion->prepare($consulta);
		$resultado->execute(array($Nombre));
		if($resultado->rowCount()!=0){
			$fila=$resultado->fetch(PDO::FETCH_ASSOC);
			echo $fila["Nombre"]."|".$fila["img"];
		}else{
			echo "No se consigue la Categoria.";
		}
	}catch (Exception $e){
		die('Error: ' . $e->GetMessage());
	}

?>