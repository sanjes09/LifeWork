<?php 
	$Codigo=$_POST["CodigoCPro"];
	$Categoria=$_POST["CategoriaCPro"];
	$Subcategoria=$_POST["SubcategoriaCPro"];
	$Nombre=$_POST["NombreCPro"];
	$NombreImg=$_FILES["ImagenCP"]["name"];
	$TamanoImg=$_FILES["ImagenCP"]["size"];
	$TipoImg=$_FILES["ImagenCP"]["type"];
	$Costo=$_POST["CostoCPro"];
	$Disponible=$_POST["DisponibleCPro"];
	$Descripcion=$_POST["DescripcionCPro"];
	//if(!file_exists($_SERVER["DOCUMENT_ROOT"]."/Pagina/Articulos/".$Categoria."/".$Subcategoria)){
	//	mkdir($_SERVER["DOCUMENT_ROOT"]."/Pagina/Articulos/".$Categoria."/".$Subcategoria,0777,true);
	//}
	//if(!file_exists(getcwd()."/../Articulos/".$Categoria)){
	//	mkdir(getcwd()."/../Articulos/".$Categoria,0777,true);
	//}
	//$Carpeta=$_SERVER["DOCUMENT_ROOT"]."/Pagina/Articulos/".$Categoria."/".$Subcategoria;
	$Carpeta=getcwd()."/../Articulos";
	move_uploaded_file($_FILES["ImagenCP"]["tmp_name"], $Carpeta."/".$NombreImg);
	if($TamanoImg<1000000000){
		if(($TipoImg=="image/jpg")||($TipoImg=="image/png")||($TipoImg=="image/jpeg")){
			require("conexion.php");
			try{
				$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
				$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
				$conexion->exec("Set character set utf8");

				$consulta="insert into Articulo values(?,?,?,?,?,?,?,?);";
				$resultado=$conexion->prepare($consulta);
				$resultado->execute(array($Codigo,$Categoria,$Subcategoria,$Nombre,$Descripcion,"../Articulos/".$NombreImg,$Costo,$Disponible));
				if($resultado->rowCount()!=0){
					echo "Producto añadido.";
				}else{
					echo "No se pudo añadir.";
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