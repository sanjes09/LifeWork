<?php 
	require("conexion.php");
	$Nombre=$_POST["TituloMSC"];
	if($Nombre==""){
		echo "No hay nada que cambiar";
	}else{
		$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
		$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$conexion->exec("Set character set utf8");

		$NombreSubCat=$_POST["NuevoNombreMSC"];
		$NombreCat=$_POST["NombreCatMSC"];	
		$Cate="";
		$SubCate="";
		$cont=1;
		$porsia=1;
		//BUSCA LOS DATOS DE LA SUBCATEGORIA
		$consulta="select * from Subcategoria where Nombre = ? ;";
		$resultado=$conexion->prepare($consulta);
		$resultado->execute(array($Nombre));
		$fila=$resultado->fetch(PDO::FETCH_ASSOC);
		$Cate=$fila["Categoria"];
		$SubCate=$fila["Nombre"];
		//---------
		if($_FILES["ImagenMSC"]["name"]!=""){
			$porsia=0;
			//BORRA IMAdGEN
			$consulta="select * from Subcategoria where Nombre = ? ;";
			$resultado=$conexion->prepare($consulta);
			$resultado->execute(array($Nombre));
			$fila=$resultado->fetch(PDO::FETCH_ASSOC);
			$Cate=$fila["Categoria"];
			//---------
			$NombreImg=$_FILES["ImagenMSC"]["name"];
			$TamanoImg=$_FILES["ImagenMSC"]["size"];
			$TipoImg=$_FILES["ImagenMSC"]["type"];
			//$Carpeta=$_SERVER["DOCUMENT_ROOT"] . "/Pagina/Img/".$Cate."/";
			$Carpeta=getcwd()."/../Img/";
			if($TamanoImg<1000000000){
				if(($TipoImg=="image/jpg")||($TipoImg=="image/png")||($TipoImg=="image/jpeg")){
					try{
						$consulta1="update Subcategoria SET img = ? where Nombre = ? ;";
						$resultado1=$conexion->prepare($consulta1);
						$resultado1->execute(array('../Img/'.$NombreImg,$Nombre));
						if($resultado1->rowCount()!=0){
							unlink($fila["img"]);
							move_uploaded_file($_FILES["ImagenMSC"]["tmp_name"], $Carpeta.$NombreImg);
							$porsia=2;
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
		//CAMBIA CATEGORIA
		if($Cate!=$NombreCat){
			if($porsia!=0){
				$consulta10="select * from Categoria where Nombre = ? ;";
				$resultado10=$conexion->prepare($consulta10);
				$resultado10->execute(array($NombreCat));
				if($resultado10->rowCount()==0){
					echo "Categoria no existe.";
					$cont=0;
				}else{
					try{
						$consulta4="update Subcategoria SET Categoria = ? where Nombre = ? ;";
						$resultado4=$conexion->prepare($consulta4);
						$resultado4->execute(array($NombreCat,$Nombre));
						try{
							$consulta5="update Articulo SET Categoria = ? where Subcategoria = ? ;";
							$resultado5=$conexion->prepare($consulta5);
							$resultado5->execute(array($NombreCat,$Nombre));
						}catch (Exception $e){
							die('Error: ' . $e->GetMessage());
						}
						$cont=2;
					}catch (Exception $e){
						die('Error: ' . $e->GetMessage());
					}
				}
			}
		}
		//CAMBIA SUBCATEGORIA
		if($SubCate!=$NombreSubCat){
			if($porsia!=0){
				$consulta23="select * from Subcategoria where Nombre = ? ;";
				$resultado23=$conexion->prepare($consulta23);
				$resultado23->execute(array($NombreSubCat));
				if($resultado23->rowCount()!=0){
					echo "Subategoria ya existe.";
					$cont=0;
				}else{
					try{
						$consulta2="update Subcategoria SET Nombre = ? where Nombre = ? ;";
						$resultado2=$conexion->prepare($consulta2);
						$resultado2->execute(array($NombreSubCat,$Nombre));
						try{
							$consulta3="update Articulo SET Subcategoria = ? where Subcategoria = ? ;";
							$resultado3=$conexion->prepare($consulta3);
							$resultado3->execute(array($NombreSubCat,$Nombre));
						}catch (Exception $e){
							die('Error: ' . $e->GetMessage());
						}
						$cont=2;
					}catch (Exception $e){
						die('Error: ' . $e->GetMessage());
					}
				}
			}
		}
		if($cont==2||$porsia==2){
			echo "Cambio Exitoso!";
		}
		if($cont==1&&$porsia==1){
			echo "No se realizaron cambios.";
		}
	}
?>