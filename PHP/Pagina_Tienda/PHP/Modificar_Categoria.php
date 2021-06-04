<?php 
	require("conexion.php");
	$Nombre=$_POST["TituloMC"];
	$NombreCat=$_POST["NombreCatMC"];

	if($Nombre==""){
		echo "No hay nada que cambiar";
	}else{
		$conexion=new PDO($db_hostPDO,$db_usuario,$db_pass);
		$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$conexion->exec("Set character set utf8");
		$Cate="";
		$cont1=1;
		$porsia=1;
				
		if($Nombre!=$NombreCat){
			//VALIDA SI LA NUEVA CATEGORIA EXISTE
			$consulta10="select * from Categoria where Nombre = ? ;";
			$resultado10=$conexion->prepare($consulta10);
			$resultado10->execute(array($NombreCat));
			if($resultado10->rowCount()!=0){
				echo "Categoria Ya Existe.";
				$cont1=0;
			}else{
				//CAMBIA CATEGORIA
				if($porsia==1){
					try{
						$consulta4="update Categoria SET Nombre = ? where Nombre = ? ;";
						$resultado4=$conexion->prepare($consulta4);
						$resultado4->execute(array($NombreCat,$Nombre));
						try{
							$consulta5="update Subcategoria SET Categoria = ? where Categoria = ? ;";
							$resultado5=$conexion->prepare($consulta5);
							$resultado5->execute(array($NombreCat,$Nombre));
						}catch (Exception $e){
							die('Error: ' . $e->GetMessage());
						}
						try{
							$consulta23="update Articulo SET Categoria = ? where Categoria = ? ;";
							$resultado23=$conexion->prepare($consulta23);
							$resultado23->execute(array($NombreCat,$Nombre));
						}catch (Exception $e){
							die('Error: ' . $e->GetMessage());
						}
						$cont1=2;
					}catch (Exception $e){
						die('Error: ' . $e->GetMessage());
					}
				}
			}
		}

		if($cont1!=0){
			if($_FILES["ImagenMC"]["name"]!=""){
				$porsia=0;
				//PARA BORRA IMAdGEN
				$consulta="select * from Categoria where Nombre = ? ;";
				$resultado=$conexion->prepare($consulta);
				$resultado->execute(array($NombreCat));
				$fila=$resultado->fetch(PDO::FETCH_ASSOC);
				//---------

				$NombreImg=$_FILES["ImagenMC"]["name"];
				$TamanoImg=$_FILES["ImagenMC"]["size"];
				$TipoImg=$_FILES["ImagenMC"]["type"];
				//$Carpeta=$_SERVER["DOCUMENT_ROOT"] . "/Pagina/Img/".$Cate."/";
				$Carpeta=getcwd()."/../Img/";
				if($TamanoImg<1000000000){
					if(($TipoImg=="image/jpg")||($TipoImg=="image/png")||($TipoImg=="image/jpeg")){
						try{
							$consulta1="update Categoria SET img = ? where Nombre = ? ;";
							$resultado1=$conexion->prepare($consulta1);
							$resultado1->execute(array('../Img/'.$NombreImg,$NombreCat));
							if($resultado1->rowCount()!=0){
								unlink($fila["img"]);
								move_uploaded_file($_FILES["ImagenMC"]["tmp_name"], $Carpeta.$NombreImg);
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
		}

		if($cont1==2||$porsia==2){
			echo "Cambio Exitoso!";
		}
		if($cont1==1&&$porsia==1){
			echo "No se realizaron cambios.";
		}
	}
?>