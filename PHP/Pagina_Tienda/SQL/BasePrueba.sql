CREATE TABLE Administrador(
	NIF VARCHAR(8) PRIMARY KEY NOT NULL,
	PASSWORD VARCHAR(20) NOT NULL
);

CREATE TABLE Usuario(
	NIF VARCHAR(8) PRIMARY KEY NOT NULL,
	Correo VARCHAR(30) NOT NULL,
	Contrasena VARCHAR(20),
	Nombre VARCHAR(50),
	Apellido VARCHAR(50),
	Edad INT,
	Sexo VARCHAR(1),
	Direccion VARCHAR(100),
	Telefono VARCHAR(50),
	Tipo VARCHAR(10)
);

CREATE TABLE Categoria(
	Nombre VARCHAR(20) PRIMARY KEY NOT NULL,
	img VARCHAR(50)
);

CREATE TABLE Subcategoria(
	Nombre VARCHAR(20) PRIMARY KEY NOT NULL,
	Categoria VARCHAR(20),
	img VARCHAR(50),
	CONSTRAINT FK_Categoria1 FOREIGN KEY (Categoria) REFERENCES Categoria(Nombre)
);

CREATE TABLE Articulo(
	Codigo VARCHAR(10) PRIMARY KEY NOT NULL,
	Categoria VARCHAR(20) NOT NULL,
	Subcategoria VARCHAR(20) NOT NULL,
	Nombre VARCHAR(100),
	Descripcion VARCHAR(5000),
	Imagen VARCHAR(100),
	Costo REAL,
	Disponible INT,
	CONSTRAINT FK_Categoria2 FOREIGN KEY (Categoria) REFERENCES Categoria(Nombre),
	CONSTRAINT FK_Subcategoria2 FOREIGN KEY (Subcategoria) REFERENCES Subcategoria(Nombre)
);

CREATE TABLE Carrito(
	CodArt VARCHAR(10) NOT NULL,
	NIF VARCHAR(8) NOT NULL,
	Cantidad INT,
	CONSTRAINT PK_Carrito PRIMARY KEY(CodArt,NIF)
);