<!-- CONTENIDO CREAR PRODUCTO -->

<div id="content_cp" style="display:none" class="main">

    <div class="t-int">
        <h1>CREAR PRODUCTO</h1>
    </div>

    <br><br>

    <!--BOTONES-->

    <div class="botones-int">

        <form action="../PHP/Crear_Producto.php" onsubmit="" method="post" id="CrearP" enctype="multipart/form-data">
            <h1>Registro</h1>
                <br>
                Codigo: <input type="text" id="CodigoPro" name="CodigoPro" placeholder="Codigo" required>
                <br><br>
                Categoria: <input type="text" id="CategoriaPro" name="CategoriaPro" placeholder="Categoria" required>
                Subcategoria: <input type="text" id="SubcategoriaPro" name="SubcategoriaPro" placeholder="Subcategoria" required>
                <br> <br>
                Nombre: <input type="text" id="NombrePro" name="NombrePro" placeholder="Nombre" required>
                Imagen: <input type="file" id="Imagen" name="Imagen" size="20" required>
                <br/> <br>
                Costo: <input type="number" step="any" id="CostoPro" name="CostoPro" placeholder="Costo" required>
                Disponibilidad: <input type="number" id="DisponiblePro" name="DisponiblePro" placeholder="Disponible" required>
                <br/> <br>
                <textarea rows="10" cols="100" class="caja" id="DescripcionPro" name="DescripcionPro" placeholder="Descripcion" required></textarea>
                <br/>
                <p style="color: red" id="Notif"></p>
                <br/> <br/>
                <button type="submit" value="Crear">Crear</button>
                <br/>
        </form>

        <br><br><br>

        <button id="Atras" href="#" value="Atras">Atrás</button>

    </div>

</div>

<!-- FIN CONTENIDO CREAR PRODUCTO -->

<!-- CONTENIDO MODIFICAR PRODUCTO -->
			
<div id="content_mp" style="display:none" class="contenido">

    <div class="t-int">
        <h1>MODIFICAR PRODUCTO</h1>
    </div>

    <br><br>

    <!--BOTONES-->
    
    <div class="botones-int">

        <form action="../PHP/Buscar_Producto.php" method="post" id="BuscarP">
            <h1>Busqueda</h1>
            <br>
            <input type="text" id="CodigoPro2" name="CodigoPro2" placeholder="Codigo" required>
            <button type="submit" value="Buscar">Buscar</button>
            <br/>
            <p style="color: red" id="Notif2"></p>
            <br>
        </form>

        <form action="../PHP/Modificar_Producto.php" method="post" id="ModificarP" enctype="multipart/form-data">

            <p>Datos a modificar:</p>
            <input style="visibility: hidden;" type="text" id="CodigoPro" name="CodigoPro" placeholder="Codigo" readonly>
            <br><br>
            <img style="width: 200px; height: 200px;" id="Foto" src="">
            <br/> <br>
            Categoria:<input type="text" id="CategoriaPro" name="CategoriaPro" placeholder="Categoria Nueva" required>
            Subcategoria:<input type="text" id="SubcategoriaPro" name="SubcategoriaPro" placeholder="Subcategoria Nueva" required>
            <br> <br>
            Nombre:<input type="text" id="NombrePro" name="NombrePro" placeholder="Nombre" required>
            Imagen:<input type="file" id="Imagen" name="Imagen" size="20">
            <br>
            Costo:<input type="number" step="any" id="CostoPro" name="CostoPro" placeholder="Costo" required>
            Disponibilidad:<input type="number" id="DisponiblePro" name="DisponiblePro" placeholder="Disponible" required>
            <br/> <br>
            Descripcion:<br>
            <textarea rows="10" cols="100" class="caja" id="DescripcionPro" name="DescripcionPro" placeholder="Descripcion" required></textarea>
            <br/>
            <p style="color: red" id="Notif"></p>
            <br/> <br/>
            <button type="submit" value="Modificar">Modificar</button>
            <br/>

        </form>
            
        <br><br><br>
            
        <button id="Atras" href="#" value="Atras">Atrás</button>

    </div>

</div>

<!-- FIN CONTENIDO-->

<!-- CONTENIDO CREAR CATEGORIA -->

<div id="content_cc" style="display:none" class="contenido">

    <div class="t-int">
        <h1>CREAR CATEGORIA</h1>
    </div>

    <br><br>

    <!--BOTONES-->

    <div class="botones-int">

        <h1>Registro</h1>
        <br>

        <form action="../PHP/Crear_Categoria.php" method="post" id="CrearC" enctype="multipart/form-data">
           
            Nombre: <input type="text" id="Nombre" name="Nombre" placeholder="Nombre de Categoria" required>
            <br><br>
            Imagen: <input type="file" id="Imagen" name="Imagen" size="20" required>
            <br/>
            <p style="color: red" id="Notif"></p>
            <br/> <br/>
            <button type="submit" value="Crear">Crear</button>
            <br/>

        </form>
            
        <br><br>
            
        <button id="Atras" value="Atras">Atrás</button>

    </div>

</div>

<!-- FIN CONTENIDO CREAR CATEGORIA -->

<!-- CONTENIDO MODIFICAR CATEGORIA -->

<div id="content_mc" style="display:none" class="contenido">

    <div class="t-int">
        <h1>MODIFICAR CATEGORIA</h1>
    </div>

    <br><br>

    <!--BOTONES-->
    
    <div class="botones-int">

        <form action="../PHP/Buscar_Categoria.php" method="post" id="BuscarC">
            <h1>Busqueda</h1>
            <br>
            <input type="text" name="Nombre" id="Nombre" placeholder="Nombre de la Categoria" required>
            <button type="submit" value="Buscar">Buscar</button>
            <br/>
            <p style="color: red" id="Notif2"></p>
            <br><br>
        </form> 
    
        <form action="../PHP/Modificar_Categoria.php" id="ModificarC" method="post" enctype="multipart/form-data">
            <input style="visibility: hidden;" type="text" id="Titulo" name="Titulo" placeholder="Codigo" readonly>
            <br>
            <img style="width: 200px; height: 200px;" id="Foto" src="">
            <br/> <br>
            Categoria:<input type="text" name="NombreCat" id="NombreCat" placeholder="Nuevo nombre" required>
            <br><br>
            Imagen:<input type="file" id="Imagen" name="Imagen" size="20">
            <br>
            <p style="color: red" id="Notif"></p>
            <br/> <br/>
            <button  type="submit" value="Cambiar">Cambiar</button>
            <br/>

        </form>
            
        <br><br><br>
            
        <button id="Atras" value="Atras">Atrás</button>
    </div>

</div>

<!-- FIN CONTENIDO MODIFICAR CATEGORIA -->

<!-- CONTENIDO CREAR SUBCATEGORIA -->

<div id="content_csc" style="display:none" class="contenido">

    <div class="t-int">
        <h1>CREAR SUBCATEGORIA</h1>
    </div>

    <br><br>

    <!--BOTONES-->
    
    <div class="botones-int">

        <form action="../PHP/Crear_Subcategoria.php" method="post" id="CrearSC" enctype="multipart/form-data">

            <h1>Registro</h1>
            <br>

            Categoria: <input type="text" id="Nombre" name="Nombre" placeholder="Nombre de Categoria" required>
            Subcategoria: <input type="text" id="NombreSub" name="NombreSub" placeholder="Nombre de Subcategoria" required>
            <br><br>
            Imagen: <input type="file" id="Imagen" name="Imagen" size="20" required>
            <br/>
            <p style="color: red" id="Notif"></p>
            <br/> <br/>
            <button type="submit" value="Crear">Crear</button>
            <br/>

        </form>
            
        <br><br><br>
            
        <button id="Atras" href="#" value="Atras">Atrás</button>

    </div>

</div>

<!-- FIN CONTENIDO CREAR SUBCATEGORIA -->

<!-- CONTENIDO MODIFICAR SUBCATEGORIA -->

<div id="content_msc" style="display:none" class="contenido">

    <div class="t-int">
        <h1>MODIFICAR SUBCATEGORIA</h1>
    </div>

    <br><br>

    <!--BOTONES-->

    <div class="botones-int">

        <form action="../PHP/Buscar_Subcategoria.php" method="post" id="BuscarSC">
            <h1>Busqueda</h1>
            <br>
            <input type="text" name="Nombre" id="Nombre" placeholder="Nombre de la Subcategoria" required>
            <button type="submit" value="Buscar">Buscar</button>
            <br/>
            <p style="color: red" id="Notif2"></p>
            <br><br>
        </form> 
    
        <form action="../PHP/Modificar_Subcategoria.php" id="ModificarSC" method="post" enctype="multipart/form-data">
            <input style="visibility: hidden;" type="text" id="Titulo" name="Titulo" placeholder="Codigo" readonly>
            <br>
            <img style="width: 200px; height: 200px;" id="Foto" src="">
            <br/> <br>
            Categoria:<input type="text" name="NombreCat" id="NombreCat" placeholder="Nuevo nombre" required>
            Subcategoria:<input type="text" name="NuevoNombre" id="NuevoNombre" placeholder="Nuevo nombre" required>
            <br><br>
            Imagen:<input type="file" id="Imagen" name="Imagen" size="20">
            <br>
            <p style="color: red" id="Notif"></p>
            <br/> <br/>
            <button  type="submit" value="Cambiar">Cambiar</button>
            <br/>

        </form>
            
        <br><br><br>
            
        <button id="Atras" href="#" value="Atras">Atrás</button>

    </div>

</div>

<!-- FIN CONTENIDO MODIFICAR SUBCATEGORIA-->