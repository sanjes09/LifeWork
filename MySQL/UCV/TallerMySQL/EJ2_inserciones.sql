INSERT INTO Boleto VALUES('123');
INSERT INTO Boleto VALUES('456');
INSERT INTO Boleto VALUES('789');

INSERT INTO Linea VALUES('Linea1');
INSERT INTO Linea VALUES('Linea2');
INSERT INTO Linea VALUES('Linea3');

INSERT INTO Cliente VALUES('21194440','Santiago','Catia La Mar','04126275384');
INSERT INTO Cliente VALUES('26648099','Alejandra','Catia La Mar','04141204097');
INSERT INTO Cliente VALUES('11059260','Mercedes','Caracas','04122920384');
INSERT INTO Cliente VALUES('20192885','Raymer','Palermo','04126275384');
INSERT INTO Cliente VALUES('6965274','Lina','Catia La Mar','04141204097');
INSERT INTO Cliente VALUES('6366376','Vicente','Catia La Mar','04141204097');

INSERT INTO Persona_Natural VALUES('21194440','Santiago','Catia La Mar','04126275384','sanjes09@gmail.com','M','1994-09-19');
INSERT INTO Persona_Natural VALUES('26648099','Alejandra','Catia La Mar','04141204097','alejandra.vengon@gmail.com','F','1999-07-31');
INSERT INTO Persona_Natural VALUES('11059260','Mercedes','Catia La Mar','04122920384','mercedesbello@hotmail.com','F','1970-05-23');

INSERT INTO Empresa VALUES('20192885','Raymer','Buenos Aires','04126275384','029384','2000-01-01','YO');
INSERT INTO Empresa VALUES('6965274','Lina','Catia La Mar','04141204097','532251','2015-06-29','YO Tambien');
INSERT INTO Empresa VALUES('6366376','Vicente','Catia La Mar','04141204097','412937','2018-04-23','YO Otra vez');

INSERT INTO Ciudad VALUES('1','Catia La Mar','Vargas','Venezuela');
INSERT INTO Ciudad VALUES('2','Palermo','Buenos Aires','Argentina');
INSERT INTO Ciudad VALUES('3','Caracas','Distrito Capital','Venezuela');

INSERT INTO Tour VALUES('Benito Perez Galdos','14 minutos');
INSERT INTO Tour VALUES('Propatria','15 minutos');
INSERT INTO Tour VALUES('Bellas Artes','16 minutos');
INSERT INTO Tour VALUES('Agua Salud','17 minutos');
INSERT INTO Tour VALUES('Caño Amarillo','18 minutos');
INSERT INTO Tour VALUES('Capitolio','19 minutos');
INSERT INTO Tour VALUES('Plaza Venezuela','20 minutos');
INSERT INTO Tour VALUES('La Paz','21 minutos');
INSERT INTO Tour VALUES('Gato Negro','22 minutos');

INSERT INTO Avion Values('Benito Perez Galdos','14 minutos',2);
INSERT INTO Avion Values('Propatria','15 minutos',4);
INSERT INTO Avion Values('Bellas Artes','16 minutos',6);

INSERT INTO Autobus Values('Agua Salud','17 minutos',30);
INSERT INTO Autobus Values('Caño Amarillo','18 minutos',32);
INSERT INTO Autobus Values('Capitolio','19 minutos',34);

INSERT INTO Barco Values('Plaza Venezuela','20 minutos',60);
INSERT INTO Barco Values('La Paz','21 minutos',70);
INSERT INTO Barco Values('Gato Negro','22 minutos',80);

INSERT INTO Hotel Values('Trivago',5,20000,'Hotel ideal al mejor precio',20,2,'2');
INSERT INTO Hotel Values('Aladin',5,20000,'Fantasias',20,2,'3');
INSERT INTO Hotel Values('Posada',1,200,'Algo es algo',2,1,'1');

INSERT INTO Tiene Values('2','Trivago');
INSERT INTO Tiene Values('3','Aladin');
INSERT INTO Tiene Values('1','Posada');

INSERT INTO Solicita Values('2000-01-01','2000-01-05','21194440','Trivago',20000,2);
INSERT INTO Solicita Values('2001-02-01','2001-02-05','26648099','Aladin',2000,1);
INSERT INTO Solicita Values('2003-05-10','2003-05-15','11059260','Posada',4000,1);

INSERT INTO Reserva Values('2002-01-02',2,'21194440','Benito Perez Galdos');
INSERT INTO Reserva Values('2003-03-04',1,'26648099','Plaza Venezuela');
INSERT INTO Reserva Values('2005-04-25',1,'11059260','Gato Negro');

INSERT INTO Viaje Values(2000,'Linea1','Benito Perez Galdos','1');
INSERT INTO Viaje Values(20000,'Linea3','Plaza Venezuela','2');
INSERT INTO Viaje Values(30000,'Linea2','Gato Negro','3');

INSERT INTO Venta Values('2000-01-06','123','Linea3','21194440');
INSERT INTO Venta Values('2000-02-04','456','Linea1','26648099');
INSERT INTO Venta Values('2000-03-05','789','Linea2','11059260');