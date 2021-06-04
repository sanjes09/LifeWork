<?php 
	require("conexion.php");
	$Codigo=$_POST["CodigoProMP"];
	$Categoria=$_POST["CategoriaProMP"];
	$Subcategoria=$_POST["SubcategoriaProMP"];
	$Nombre=$_POST["NombreProMP"];
	$Costo=$_POST["CostoProMP"];
	$Disponible=$_POST["DisponibleProMP"];
	$Descripcion=$_POST["DescripcionProMP"];

	$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
	$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	$conexion->exec("Set character set utf8");
	$cont=0;
	$BImagen=1;
	$BCategoria=1;
	$BSubcategoria=1;
	$BNombre=1;
	$BCosto=1;
	$BDisponible=1;
	$BDescripcion=1;

	//AGARRA LOS DATOS DEL PRODUCTO
	$consulta123="select * from Articulo where  Codigo= ? ;";
	$resultado123=$conexion->prepare($consulta123);
	$resultado123->execute(array($Codigo));
	$fila=$resultado123->fetch(PDO::FETCH_ASSOC);
	//---------

	//VALIDA LA CATEGORIA Y SUBCATEGORIA
	$consulta="select * from Categoria where Nombre = ?;";
	$resultado=$conexion->prepare($consulta);
	$resultado->execute(array($Categoria));
	if($resultado->rowCount()!=0){
		$consulta9="select * from Subcategoria where Nombre = ? and Categoria = ? ;";
		$resultado9=$conexion->prepare($consulta9);
		$resultado9->execute(array($Subcategoria,$Categoria));
		if($resultado9->rowCount()!=0){
			$cont=1;
		}else{
			echo "Subcategoria no existe.";
		}
	}else{
		echo "Categoria no existe.";
	}

	if($cont==1){
		if($_FILES["ImagenMP"]["name"]!=""){
			$BImagen=0;
			$NombreImg=$_FILES["ImagenMP"]["name"];
			$TamanoImg=$_FILES["ImagenMP"]["size"];
			$TipoImg=$_FILES["ImagenMP"]["type"];
			//$Carpeta=$_SERVER["DOCUMENT_ROOT"] . "/Pagina/Articulos/".$Cate."/".$SubCate."/";
			$Carpeta=getcwd()."/../Articulos/".$Categoria."/".$Subcategoria."/";
			if($TamanoImg<1000000000){
				if(($TipoImg=="image/jpg")||($TipoImg=="image/png")||($TipoImg=="image/jpeg")){
					try{
						$consulta2="update Articulo SET Imagen = ? where Codigo = ? ;";
						$resultado2=$conexion->prepare($consulta2);
						$resultado2->execute(array('../Articulos/'.$Cate.'/'.$SubCate.'/'.$NombreImg,$Codigo));
						if($resultado2->rowCount()!=0){
							echo "Imagen cambiada";
							move_uploaded_file($_FILES["ImagenMP"]["tmp_name"], $Carpeta.$NombreImg);
							unlink($fila["img"]);
							$BImagen=2;
						}else{
							echo "No se pudo añadir la imagen.";
						}
					}catch (Exception $e){
						die('Error: ' . $e->GetMessage());
					}
				}else{
					echo "No es una imagen";
				}
			}else{
				echo "Imagen muy pesada";
			}
		}

		//CATEGORIA
		if($Categoria!=$fila["Categoria"]){
			$BCategoria=0;
			try{
				$consulta3="update Articulo SET Categoria = ? where Codigo = ? ;";
				$resultado3=$conexion->prepare($consulta3);
				$resultado3->execute(array($Categoria,$Codigo));
				$BCategoria=2;
			}catch (Exception $e){
				die('Error: ' . $e->GetMessage());
			}
		}

		//SUBCATEGORIA
		if($Subcategoria!=$fila["Subcategoria"]){
			$BSubcategoria=0;
			try{
				$consulta4="update Articulo SET Subcategoria = ? where Codigo = ? ;";
				$resultado4=$conexion->prepare($consulta4);
				$resultado4->execute(array($Subcategoria,$Codigo));
				$BSubcategoria=2;
			}catch (Exception $e){
				die('Error: ' . $e->GetMessage());
			}
		}

		//NOMBRE
		if($Nombre!=$fila["Nombre"]){
			$BNombre=0;
			try{
				$consulta5="update Articulo SET Nombre = ? where Codigo = ? ;";
				$resultado5=$conexion->prepare($consulta5);
				$resultado5->execute(array($Nombre,$Codigo));
				$BNombre=2;
			}catch (Exception $e){
				die('Error: ' . $e->GetMessage());
			}
		}

		//COSTO
		if($Costo!=$fila["Costo"]){
			$BCosto=0;
			try{
				$consulta6="update Articulo SET Costo = ? where Codigo = ? ;";
				$resultado6=$conexion->prepare($consulta6);
				$resultado6->execute(array($Costo,$Codigo));
				$BCosto=2;
			}catch (Exception $e){
				die('Error: ' . $e->GetMessage());
			}
		}

		//DISPONIBILIDAD
		if($Disponible!=$fila["Disponible"]){
			$BDisponible=0;
			try{
				$consulta7="update Articulo SET Disponible = ? where Codigo = ? ;";
				$resultado7=$conexion->prepare($consulta7);
				$resultado7->execute(array($Disponible,$Codigo));
				$BDisponible=2;
			}catch (Exception $e){
				die('Error: ' . $e->GetMessage());
			}
		}

		//DESCRIPCION
		if($Descripcion!=$fila["Descripcion"]){
			$BDescripcion=0;
			try{
				$consulta8="update Articulo SET Descripcion = ? where Codigo = ? ;";
				$resultado8=$conexion->prepare($consulta8);
				$resultado8->execute(array($Descripcion,$Codigo));
				$BDescripcion=2;
			}catch (Exception $e){
				die('Error: ' . $e->GetMessage());
			}
		}
		if($BImagen==0||$BCategoria==0||$BSubcategoria==0||$BNombre==0||$BCosto==0||$BDisponible==0||$BDescripcion==0){
			echo "hubo error.";
		}else{
			if($BImagen==1&&$BCategoria==1&&$BSubcategoria==1&&$BNombre==1&&$BCosto==1&&$BDisponible==1&&$BDescripcion==1){
				echo "No hubo modificaciones.";
			}else{
				if($BImagen==2||$BCategoria==2||$BSubcategoria==2||$BNombre==2||$BCosto==2||$BDisponible==2||$BDescripcion==2){
					echo "Modificacion exitosa";
				}
			}
		}
	}
?>