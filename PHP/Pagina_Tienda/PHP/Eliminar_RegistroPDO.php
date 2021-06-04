<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Documento sin título</title>
</head>
<body>
<?php
	require("conexion.php");
	$busqueda=$_POST["buscar"];
	try{
		$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
		$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$conexion->exec("Set character set utf8");

		$eliminar="delete from cliente where NIF = ?";

		$resultado=$conexion->prepare($eliminar);

		$resultado->execute(array($busqueda));

		if($resultado->rowCount()!=0){
			echo "Eliminacion exitosa";
		}else{
			echo "Error al eliminar";
		}
		
		$resultado->closeCursor();
		
	}catch (Exception $e){
		die('Error: ' . $e->GetMessage());
	}/*finally{
		$conexion=null;
	}*/


?>
</body>
</html>