<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="icon" href="../Img/laptop.png">
    <title>Registro</title>
    <link href="https://fonts.googleapis.com/css?family=Modern+Antiqua|Open+Sans+Condensed:300,700" rel="stylesheet">
    <link rel="stylesheet" href="../CSS/reg.css">
	<script src="../JS/jquery-3.3.1.js"></script>
    <script src="../JS/funciones.js"></script>
    <script src="../JS/Validar.js"></script>
</head>
<body>
	<?php
        session_start();
        if(isset($_SESSION['Cliente']) or isset($_SESSION['Empleado'])) {
            header("Location:index.php");
        }
    ?>
	<div id="content" style="display: none;" class="contenedor">

		<!--TITULO-->

        <h1>Registro</h1>

		<!--NOMBRE Y APELLIDO-->

		<input type="text" name="Nombre" id="Nombre" placeholder="Nombres" required>
		<input type="text" name="Apellido" id="Apellido" placeholder="Apellidos" required>
		<br/>

		<!--EDAD-->

		<input type="number" name="Edad" id="Edad" min="18" max="90" placeholder="Edad" maxlength="2" required>

		<!--GENERO-->

		<input type="radio" name="Sexo" id="Sexo" value="Hombre" required>Hombre
		<input type="radio" name="Sexo" id="Sexo" value="Mujer" required>Mujer
		<br/>

		<!--CEDULA-->

		<input type="text" id="NIF" name="NIF" maxlength="8" placeholder="Cedula" required>

		<!--TELEFONO-->

		<input type="text" name="Telefono" id="Telefono" placeholder="Telefono" maxlength="16">
		<br/>

		<!--DIRECCION-->

		<input type="text" name="Direccion" id="Direccion" placeholder="Direccion" required>
		
		<!--CORREO-->

		<input type="email" name="Correo" id="Correo" placeholder="Correo" required>
		<br/>

		<!--CONTRASEÑA-->

		<input type="password" id="Pass1" name="Pass1" placeholder="Contraseña" required>
		<input type="password" id="Pass2" name="Pass2" placeholder="Repetir Contraseña" required>
		<br/>

		<!--TIPO-->

		<input type="radio" name="Usuario" id="Usuario" value="Cliente" onclick="NoAdmin()" required>Cliente
		<input type="radio" name="Usuario" id="Usuario" value="Empleado" onclick="Admin()" required>Empleado

		<br/>
		<div id="Admin">
			<p id="PassAdm"> </p>
		</div>
		<br/>

		<!--TERMINOS Y CONDICIONES-->

		<input type="checkbox" name="Terminos" required><p>Aceptar Terminos</p>
		<br/>
		<!--NOTIFICACION-->
		<br/>

		<p style="color: red" id="Notif"></p>
		<br/>
		<!--BOTON-->

		<input type="button" href='javascript:;' onclick='ValidarRegistro()' value="Registrar">
		<br/>

		<!--INICIO-->

        <a class="inicio" href="index.php">Inicio</a>

	</div>
</body>
</html>