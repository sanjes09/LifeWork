<?php
	require("conexion.php");

	$NIF=$_POST["NIF"];
 	$Contrasena=$_POST["Pass"];

	try{
		$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
		$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$conexion->exec("Set character set utf8");

		$Guardar="insert into Administrador values(?,?)";
		$resultado=$conexion->prepare($Guardar);

		$resultado->execute(array($NIF,$Contrasena));

		if($resultado->rowCount()!=0){
			echo "Insercion exitosa";
		}else{
			echo "Error al insertar";
		}
		
	}catch (Exception $e){
		if($e->GetCode()=="23000"){
			die('Cedula ya existe');
		}else echo 'Error: ' . $e->GetMessage();
	}
?>