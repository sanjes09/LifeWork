DROP DATABASE IF EXISTS Futbol;

CREATE DATABASE Futbol;
USE Futbol;

CREATE TABLE tecnico (
	dni VARCHAR(20),
	nombre VARCHAR(15),
	apellido VARCHAR(20),
	fecha_nac DATE,
	CONSTRAINT PK_tecnico PRIMARY KEY (dni)
);

CREATE TABLE partido (
	codigo VARCHAR(20),
	goles_fuera INT,
	goles_casa INT,
	fecha DATE,
	CONSTRAINT PK_partido PRIMARY KEY (codigo)
);

CREATE TABLE equipo (
	codigo VARCHAR(20),
	nombre VARCHAR(30),
	estadio VARCHAR(30),
	ano DATE,
	ciudad VARCHAR(20),
	dni_tecnico VARCHAR(20),
	CONSTRAINT PK_equipo PRIMARY KEY (codigo),
	CONSTRAINT FK_equipo_tecnico FOREIGN KEY (dni_tecnico) REFERENCES tecnico (dni)
);

CREATE TABLE juega (
	codigo_partido VARCHAR(20),
	codigo_equipo VARCHAR(20),
	condicion VARCHAR(1),
	CONSTRAINT PK_juega PRIMARY KEY (codigo_equipo,codigo_partido),
	CONSTRAINT FK_juega_equipo FOREIGN KEY (codigo_equipo) REFERENCES equipo (codigo),
	CONSTRAINT FK_juega_partido FOREIGN KEY (codigo_partido) REFERENCES partido (codigo)
);

CREATE TABLE jugador (
	codigo VARCHAR(20),
	nombre VARCHAR(30),
	posicion VARCHAR(20),
	codigo_equipo VARCHAR(20),
	CONSTRAINT PK_jugador PRIMARY KEY (codigo),
	CONSTRAINT FK_jugador_equipo FOREIGN KEY (codigo_equipo) REFERENCES equipo (codigo)
);

CREATE TABLE gol (
	codigo VARCHAR(20),
	minuto INT,
	descripcion VARCHAR(50),
	codigo_jugador VARCHAR(20),
	codigo_partido VARCHAR(20),
	codigo_equipo VARCHAR(20),
	CONSTRAINT PK_gol PRIMARY KEY (codigo),
	CONSTRAINT FK_gol_jugador FOREIGN KEY (codigo_jugador) REFERENCES jugador (codigo),
	CONSTRAINT FK_gol_partido FOREIGN KEY (codigo_partido) REFERENCES partido (codigo),
	CONSTRAINT FK_gol_equipo FOREIGN KEY (codigo_equipo) REFERENCES equipo (codigo)
);
