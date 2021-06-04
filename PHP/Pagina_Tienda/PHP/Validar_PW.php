<?php
	require("conexion.php");
	$pass=$_REQUEST['Codigo'];
	try{
		$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
		$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$conexion->exec("Set character set utf8");

		$consulta="select * from Administrador where  PASSWORD = ?";
		$resultado=$conexion->prepare($consulta);
		$resultado->execute(array($pass));
		if($resultado->rowCount()==0){
			echo "error1";
		}
		$resultado->closeCursor();
		
	}catch (Exception $e){
		die('Error: ' . $e->GetMessage());
	}
?>