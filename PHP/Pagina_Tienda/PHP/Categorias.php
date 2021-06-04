<?php
	require("conexion.php");
	try{
		$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
		$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$conexion->exec("Set character set utf8");
		$consulta="select * from Categoria;";
		$resultado=$conexion->prepare($consulta);
		$resultado->execute();
		$salida="";
		if($resultado->rowCount()!=0){
			while ($fila=$resultado->fetch(PDO::FETCH_ASSOC)){
				$salida=$salida . "<div class='caja'><a name='" . $fila['Nombre'] . "' href='javascript:;' onclick='CambiarSub(\"" . $fila['Nombre'] . "\");'><img src='" . $fila['img'] . "' alt=''></a><p>" . $fila['Nombre'] . "</p></div>";
			}
			echo $salida;
		}else{
			echo "No hay categorias";
		}

		$resultado->closeCursor();
		
	}catch (Exception $e){
		die('Error: ' . $e->GetMessage());
	}/*finally{
		$conexion=null;
	}*/
?>