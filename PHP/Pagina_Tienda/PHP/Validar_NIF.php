<?php
	require("conexion.php");
	$NIF=$_REQUEST['NIF'];
	try{
		$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
		$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$conexion->exec("Set character set utf8");

		$consulta="select * from Usuario where NIF = ?";
		$resultado=$conexion->prepare($consulta);
		$resultado->execute(array($NIF));
		if($resultado->rowCount()!=0){
			echo "error2";
		}
		$resultado->closeCursor();
		
	}catch (Exception $e){
		die('Error: ' . $e->GetMessage());
	}
?>