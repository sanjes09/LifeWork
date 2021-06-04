<?php 
	$Nombre=$_POST["NombreCC"];
	$NombreImg=$_FILES["ImagenCC"]["name"];
	$TamanoImg=$_FILES["ImagenCC"]["size"];
	$TipoImg=$_FILES["ImagenCC"]["type"];
	//$Carpeta=$_SERVER["DOCUMENT_ROOT"] . "/Pagina/Img/";
	$Carpeta=getcwd()."/../Img/";
	move_uploaded_file($_FILES["ImagenCC"]["tmp_name"], $Carpeta.$NombreImg);
	if($TamanoImg<1000000000){
		if(($TipoImg=="image/jpg")||($TipoImg=="image/png")||($TipoImg=="image/jpeg")){
			require("conexion.php");
			try{
				$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
				$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
				$conexion->exec("Set character set utf8");

				$consulta="insert into Categoria values(?,?);";
				$resultado=$conexion->prepare($consulta);
				$resultado->execute(array($Nombre,"../Img/".$NombreImg));
				if($resultado->rowCount()!=0){
					echo "Categoria creada";
				}else{
					echo "No se pudo añadir.";
				}
				
			}catch (Exception $e){
				if($e->GetCode()=="23000"){
					die('Categoria ya existe');
				}
				die('Error: ' . $e->GetMessage());
			}finally{
				$conexion=null;
			}
		}else{
			echo "No es una imagen";
		}
	}else{
		echo "Imagen muy pesada";
	}
?>