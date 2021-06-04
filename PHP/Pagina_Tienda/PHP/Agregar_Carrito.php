<?php
	session_start();
	require("conexion.php");
	$cantidad=$_REQUEST["Cantidad"];
	$codigo=$_REQUEST["Codigo"];
	if(isset($_SESSION["Cliente"])){
		try{
			$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
			$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$conexion->exec("Set character set utf8");

			$consulta="select Disponible from Articulo where Codigo = ?;";
			$resultado=$conexion->prepare($consulta);
			$resultado->execute(array($codigo));
			$fila2=$resultado->fetch(PDO::FETCH_ASSOC);
			if($fila2["Disponible"]<$cantidad){
				echo "Inventario no suficiente.";
			}else{
				$consulta="insert into Carrito values(?,?,?) ;";
				$resultado=$conexion->prepare($consulta);
				$resultado->execute(array($codigo,$_SESSION['Cliente'],$cantidad));
				if($resultado->rowCount()!=0){
					echo "Añadido al carrito.";
				}else{
					echo "No se pudo añadir.";
				}
				$resultado->closeCursor();
			}
			
		}catch (Exception $e){
			if($e->GetCode()=="23000"){
				die('Usted ya agrego este articulo');
			}
			if($e->GetCode()=="HY000"){
				die('Ingrese una cantidad');
			}
			die('Error: ' . $e->GetMessage());
		}finally{
			$conexion=null;
		}
	}else{
		echo "Ingrese como cliente";
	}
?>