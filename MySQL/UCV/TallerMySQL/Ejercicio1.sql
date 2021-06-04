DROP DATABASE IF EXISTS Tour;

CREATE DATABASE Tour;
USE Tour;

CREATE TABLE Boleto(
	Codigo VARCHAR(50) PRIMARY KEY
);

CREATE TABLE Linea(
	Nombre VARCHAR(50) NOT NULL PRIMARY KEY
);

CREATE TABLE Cliente(
	CI_RIF VARCHAR(50),
	Nombre VARCHAR(50) NOT NULL,
	Direccion VARCHAR(50) NOT NULL,
	Telefono VARCHAR(50),
	CONSTRAINT PK_ci1 PRIMARY KEY (CI_RIF)
);

CREATE TABLE Persona_Natural(
	CI_RIF VARCHAR(50),
	Nombre VARCHAR(50) NOT NULL,
	Direccion VARCHAR(50) NOT NULL,
	Telefono VARCHAR(50),
		Email VARCHAR(50),
		Sexo VARCHAR(1),
		Fecha_Nacimiento DATE,
	CONSTRAINT FK_ci5 FOREIGN KEY (CI_RIF) REFERENCES Cliente(CI_RIF)
);

CREATE TABLE Empresa(
	CI_RIF VARCHAR(50),
	Nombre VARCHAR(50) NOT NULL,
	Direccion VARCHAR(50) NOT NULL,
	Telefono VARCHAR(50),
		Nro_Fax VARCHAR(50),
		Fecha_Creacion DATE,
		Director VARCHAR(50),
	CONSTRAINT FK_ci7 FOREIGN KEY (CI_RIF) REFERENCES Cliente(CI_RIF)
);

CREATE TABLE Ciudad(
	ID INT,
	Nombre VARCHAR(50) NOT NULL,
	Estado VARCHAR(50) NOT NULL,
	Pais VARCHAR(50) NOT NULL,
	CONSTRAINT PK_id1 PRIMARY KEY (ID)
);

CREATE TABLE Tour(
	Nombre VARCHAR(50) NOT NULL,
	Duracion VARCHAR(50),
	CONSTRAINT PK_nom1 PRIMARY KEY (Nombre),
	check (Duracion>0)
);

CREATE TABLE Avion(
	Nombre VARCHAR(50) NOT NULL,
	Duracion VARCHAR(50),
		Nro_Paracaidas INT,
	CONSTRAINT FK_nom5 FOREIGN KEY (Nombre) REFERENCES Tour(Nombre),
	check (Duracion>0 and Nro_Paracaidas>0)
);

CREATE TABLE Autobus(
	Nombre VARCHAR(50) NOT NULL,
	Duracion VARCHAR(50),
		Nro_asientos INT,
	CONSTRAINT FK_nom7 FOREIGN KEY (Nombre) REFERENCES Tour(Nombre),
	check (Duracion>0 and Nro_asientos>0)
);

CREATE TABLE Barco(
	Nombre VARCHAR(50) NOT NULL,
	Duracion VARCHAR(50),
		Capacidad INT,
	CONSTRAINT FK_nom8 FOREIGN KEY (Nombre) REFERENCES Tour(Nombre),
	check (Duracion>0 and Capacidad>0)
);

CREATE TABLE Hotel(
	Nombre VARCHAR(50) NOT NULL,
	Num_Estrellas INT,
	Costo_Habitacion INT,
	Descripcion VARCHAR(50),
	Num_Habitaciones INT,
	Num_Pisos INT,
	ID INT,
	CONSTRAINT PK_nom2 PRIMARY KEY (Nombre,ID),
	CONSTRAINT FK_ci4 FOREIGN KEY (ID) REFERENCES Ciudad(ID),
	check (Num_Pisos>0 and Num_Habitaciones>0 and Num_Pisos>0 and Costo_Habitacion>0 and Num_Estrellas>0)
);

CREATE TABLE Tiene(
	ID INT,
	Nombre_Hotel VARCHAR(50) NOT NULL,
	CONSTRAINT PK_nom4 PRIMARY KEY (ID,Nombre_Hotel),
	CONSTRAINT FK_ci3 FOREIGN KEY (ID) REFERENCES Ciudad(ID),
	CONSTRAINT FK_nom3 FOREIGN KEY (Nombre_Hotel) REFERENCES Hotel(Nombre)
);

CREATE TABLE Solicita(
	Fecha_Reserva DATE,
	Fecha_Llegada DATE,
	CI_RIF VARCHAR(50),
	Nombre_Hotel VARCHAR(50) NOT NULL,
	Monto INT,
	Cantidad_Habitaciones INT,
	CONSTRAINT PK_fecha5 PRIMARY KEY (Fecha_Reserva,Fecha_Llegada),
	CONSTRAINT FK_ci6 FOREIGN KEY (CI_RIF) REFERENCES Cliente(CI_RIF),
	CONSTRAINT FK_nom6 FOREIGN KEY (Nombre_Hotel) REFERENCES Hotel(Nombre),
	check (Monto>0 and Cantidad_Habitaciones>0)
);

CREATE TABLE Reserva(
	Fecha DATE,
	NumAcompanantes INT,
	CI_RIF VARCHAR(50),
	Nombre VARCHAR(50) NOT NULL,
	CONSTRAINT PK_fecha1 PRIMARY KEY (Fecha),
	CONSTRAINT FK_ci1 FOREIGN KEY (CI_RIF) REFERENCES Cliente(CI_RIF),
	CONSTRAINT FK_nom1 FOREIGN KEY (Nombre) REFERENCES Tour(Nombre),
	check (NumAcompanantes>0)
);

CREATE TABLE Viaje(
	Costo INT,
	NombreLinea VARCHAR(50) NOT NULL,
	NombreTour VARCHAR(50) NOT NULL,
	ID INT,
	CONSTRAINT FK_nomT FOREIGN KEY (NombreTour) REFERENCES Tour(Nombre),
	CONSTRAINT FK_nomL FOREIGN KEY (NombreLinea) REFERENCES Linea(Nombre),
	CONSTRAINT FK_id FOREIGN KEY (ID) REFERENCES Ciudad(ID),
	check (Costo>0)
);

CREATE TABLE Venta (
	Fecha DATE,
	Codigo VARCHAR(50),
	Nombre VARCHAR(50) NOT NULL,
	CI_RIF VARCHAR(50),
	CONSTRAINT PK_fecha2 PRIMARY KEY (Fecha),
	CONSTRAINT FK_cod FOREIGN KEY (Codigo) REFERENCES Boleto(Codigo),
	CONSTRAINT FK_ci2 FOREIGN KEY (CI_RIF) REFERENCES Cliente(CI_RIF),
	CONSTRAINT FK_nom2 FOREIGN KEY (Nombre) REFERENCES Linea(Nombre)
);