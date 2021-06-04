<?php
	require("conexion.php");
	session_start();
	if(isset($_SESSION["Cliente"])){
		$buscar=$_SESSION["Cliente"];

		$conexion=mysqli_connect($db_host,$db_usuario,$db_pass,$db_nombre);
		mysqli_set_charset($conexion,"utf8");
		if(mysqli_connect_errno()){
			echo "Error en conectar a BD.";
			exit();
		}

		$consulta1="delete from Carrito where NIF = $buscar;";
		$resultado1=mysqli_query($conexion,$consulta1);
		$enviar="";
		$total=0;
		if(mysqli_num_rows($resultado1)!=0){
			echo "si hay";
		}else{	
			echo "Carrito Vacio";
			exit();
		}
		mysqli_close($conexion);
	}else{
		echo "Usted no dispone de este servicio. Ingrese como Cliente.";
	}
?>