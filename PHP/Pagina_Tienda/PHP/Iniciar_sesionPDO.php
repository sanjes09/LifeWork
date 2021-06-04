<?php
	require("conexion.php");
	$NIF=$_POST["NIF"];
	$Pass=$_POST["Pass"];
	try{
		$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
		$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$conexion->exec("Set character set utf8");

		$usuario="select * from Usuario where NIF = :nif and Contrasena = :pass";
		$resultado=$conexion->prepare($usuario);

		$user=htmlentities(addslashes($NIF));
		$password=htmlentities(addslashes($Pass));

		$resultado->bindValue(":nif",$user);
		$resultado->bindValue(":pass",$password);
		$resultado->execute();

		if($resultado->rowCount()!=0){
			$tipo="select * from Usuario where NIF = ?";
			$resultado2=$conexion->prepare($tipo);
			$resultado2->execute(array($user));
			$fila=$resultado2->fetch(PDO::FETCH_ASSOC);
			
			if($fila['Tipo']=="Cliente"){
				session_start();
				$_SESSION["Cliente"]=$NIF;
				$_SESSION["ClienteN"]=$fila["Nombre"]." ".$fila["Apellido"];
				$_SESSION["Temporal"]=null;
			}
			if($fila['Tipo']=="Empleado"){
				session_start();
				$_SESSION["Empleado"]=$NIF;
				$_SESSION["EmpleadoN"]=$fila["Nombre"]." ".$fila["Apellido"];
				$_SESSION["Temporal"]=null;
			}
			echo "Sesion iniciada";
		}else{
			echo "Datos erroneos";
		}
		
		$resultado->closeCursor();
		
	}catch (Exception $e){
		die('Error: ' . $e->GetMessage());
	}
?>