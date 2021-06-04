<?php 
	$Nombre=$_POST["NombreCSC"];
	$NombreSub=$_POST["NombreSubCSC"];
	$NombreImg=$_FILES["ImagenCSC"]["name"];
	$TamanoImg=$_FILES["ImagenCSC"]["size"];
	$TipoImg=$_FILES["ImagenCSC"]["type"];
	//if(!file_exists($_SERVER["DOCUMENT_ROOT"] . "/Pagina/Img/".$Nombre)){
	//	mkdir($_SERVER["DOCUMENT_ROOT"] . "/Pagina/Img/".$Nombre,0777,true);
	//}
	//if(!file_exists(getcwd()."/../Img/".$Nombre)){
	//	mkdir(getcwd()."/../Img/".$Nombre,0777,true);
	//}
	//$Carpeta=$_SERVER["DOCUMENT_ROOT"] . "/Pagina/Img/".$Nombre."/";
	$Carpeta=getcwd()."/../Img/";
	move_uploaded_file($_FILES["ImagenCSC"]["tmp_name"], $Carpeta.$NombreImg);
	if($TamanoImg<1000000000){
		if(($TipoImg=="image/jpg")||($TipoImg=="image/png")||($TipoImg=="image/jpeg")){
			require("conexion.php");
			try{
				$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
				$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
				$conexion->exec("Set character set utf8");

				$consulta="insert into Subcategoria values(?,?,?);";
				$resultado=$conexion->prepare($consulta);
				$resultado->execute(array($NombreSub,$Nombre,"../Img/".$NombreImg));
				if($resultado->rowCount()!=0){
					echo "Subcategoria creada";
				}else{
					echo "No se pudo crear.";
				}
				
			}catch (Exception $e){
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