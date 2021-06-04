<?php
	require("conexion.php");
		$buscar=$_REQUEST["q"];
	try{
		$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
		$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$conexion->exec("Set character set utf8");
		$consulta="select * from Subcategoria where Categoria = '" . $buscar . "';";
		$resultado=$conexion->prepare($consulta);
		$resultado->execute();
		$listo = "";
		if($resultado->rowCount()!=0){
			while ($fila=$resultado->fetch(PDO::FETCH_ASSOC)){
				$listo = $listo . "<div class='caja'><a name='" . $fila['Nombre'] . "' href='../HTML/articulos.php?q=" . $fila['Nombre'] . "'><img src='" . $fila['img'] . "' alt=''></a><p>" . $fila['Nombre'] . "</p></div>";
			}
			echo $listo;
		}else{
			echo "No hay categorias";
		}
		$resultado->closeCursor();
		
	}catch (Exception $e){
		die('Error: ' . $e->GetMessage());
	}
?>