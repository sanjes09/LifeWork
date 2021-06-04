<?php
	require("conexion.php");
	session_start();
	$codigo=$_REQUEST["Codigo"];
	$NIF=$_SESSION["Cliente"];
	try{
		$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
		$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$conexion->exec("Set character set utf8");
		$consulta="delete from Carrito where CodArt = ? and NIF = ?;";
		$resultado=$conexion->prepare($consulta);
		$resultado->execute(array($codigo,$NIF));
		if($resultado->rowCount()!=0){
			echo "Eliminacion Exitosa";
		}else{
			echo "No se ha eliminado";
		}

		$resultado->closeCursor();
	}catch (Exception $e){
		die('Error: ' . $e->GetMessage());
	}/*finally{
		$conexion=null;
	}*/
?>