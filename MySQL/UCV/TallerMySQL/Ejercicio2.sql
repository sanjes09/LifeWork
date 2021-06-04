/*2.3.1. Listar los nombres de los jugadores que anotaron al menos un gol en el primer tiempo.*/
select distinct(nombre)
from jugador
where codigo in(
	select codigo_jugador
	from gol
	where minuto between 1 and 45
	);

/*2.3.2. Información completa de los equipos cuyo equipo técnico haya nacido antes de 1980*/
select *
from equipo
where dni_tecnico in(
	select dni
	from tecnico
	where fecha_nac < '1980-01-01'
	);

/*2.3.3. Listar el nombre de los jugadores y los nombres de sus equipos los cuales hayan jugado partidos los primeros dos meses del año 2012.*/
select j.nombre, e.nombre
from jugador j, equipo e
where j.codigo_equipo=e.codigo and e.codigo in(
	select codigo_equipo
	from juega
	where codigo_partido in(
		select codigo
		from partido
		where fecha between '2012-01-01' and '2012-02-28'
		)
	);

/*2.3.4. Muestre cronológicamente la información de los partidos de los equipos que hayan ganado 2 partidos. Debe mostrar el nombre del equipo.*/
select e.nombre, p.*
from equipo e, partido p, juega j,
	(select e.codigo, p.codigo as codigoP
	from juega j,partido p, equipo e
	where j.codigo_partido=p.codigo and e.codigo=j.codigo_equipo and ((j.condicion='H' and p.goles_casa>p.goles_fuera) or (j.condicion='V' and p.goles_fuera>p.goles_casa))
	group by e.codigo
	having count(e.codigo)=2
	) as x
where p.codigo=j.codigo_partido and j.codigo_equipo=e.codigo and e.codigo=x.codigo
order by p.fecha;

/*2.3.5. Mostrar el jugador que ha anotado más goles y el equipo al que pertenece.*/
select j.*, e.nombre 
from (select codigo_jugador, count(codigo) as cod from gol group by codigo_jugador) as goles, jugador as j, equipo as e
where j.codigo = goles.codigo_jugador and j.codigo_equipo = e.codigo and goles.cod = (select max(cod) from (select codigo_jugador, count(codigo) as cod from gol group by codigo_jugador) as x);  