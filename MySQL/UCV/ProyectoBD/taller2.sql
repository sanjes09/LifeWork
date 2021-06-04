connect system

create user lablunes identified by 123;

grant all privileges to lablunes;

connect lablunes/123;

create table estudiante(
	nombre varchar2(20),
	sexo char(1)
);

alter table estudiante add (fecha date);

select sysdate
	from dual;

insert into estudiante values ('Ana', 'F', '20/03/17');

update estudiante set nombre = 'Susana' where sexo = 'F';

delete from estudiante where nombre = 'Susana';

create table coctel(
	nombre varchar2(20) primary key,
	cantidad number check(Cantidad>=0),
	costo number(8,3) not null,
	GradoA number(3,1)
);
/*(8,3) = De 8 numeros, 3 son decimales (5 enteros y 3 decimales);*/

create or replace procedure agregar_coctel(
	nomb in coctel.nombre%type,
	cant in coctel.cantidad%type,
	cost in coctel.costo%type,
	grado in coctel.gradoa%type)
is begin
	insert into coctel values(nomb, cant, cost, grado);
	dbms_output.put_line ('Se ha agregado un coctel');
end agregar_coctel;
/

execute agregar_coctel('Mojito', 5, 100.5, 33);

set serveroutput on;

execute agregar_coctel('Tinto de Verano', 1, 10, 15);



create or replace trigger verificar_fecha
before insert on estudiante
for each row
declare fecha_ac date;
begin
	select sysdate into fecha_ac
	from dual;
	if(:new.fecha > fecha_ac) then
		dbms_output.put_line('La FN debe ser menor a la fecha actual');
		raise_application_error(-20601, 'Dato Invalido');
	end if;
end;
/



insert into estudiante values ('Ana', 'F', '19/03/17');

insert into estudiante values ('Enzo', 'M', '21/03/17');

create view cocteles as 
	select nombre
	from coctel;

select nombre from cocteles;
select * from cocteles;

