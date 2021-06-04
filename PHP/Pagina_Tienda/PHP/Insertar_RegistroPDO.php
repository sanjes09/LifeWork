<?php
	require("conexion.php");

	$NIF=$_REQUEST["NIF"];
 	$Contrasena1=$_REQUEST["Pass"];
 	$Nombre=$_REQUEST["Nombre"];
 	$Apellido=$_REQUEST["Apellido"];
 	$Edad=$_REQUEST["Edad"];
 	$Sexo=$_REQUEST["Sexo"];
 	$Correo=$_REQUEST["Correo"];
 	$Dir=$_REQUEST["Direccion"];
 	$Tlf=$_REQUEST["Telefono"];
 	$User=$_REQUEST["Usuario"];

	try{
		$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
		$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$conexion->exec("Set character set utf8");
		if($Sexo=="Hombre"){
			$Sexo="M";
		}else{
			$Sexo="F";
		}
		$Guardar="insert into Usuario values(?,?,?,?,?,?,?,?,?,?)";
		$resultado=$conexion->prepare($Guardar);

		$resultado->execute(array($NIF,$Correo,$Contrasena1,$Nombre,$Apellido,$Edad,$Sexo,$Dir,$Tlf,$User));

		if($resultado->rowCount()!=0){
			echo "Insercion exitosa";
		}else{
			echo "Error al insertar";
		}
		
	}catch (Exception $e){
		echo 'Error: ' . $e->GetMessage();
	}
?>