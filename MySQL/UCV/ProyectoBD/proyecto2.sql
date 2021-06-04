--ALEJANDRA VENTO, 26.648.099
--JESUS MARTINEZ, 21.194.440

--A.
/*
create user proyecto identified by 123;

grant all privileges to proyecto;

connect proyecto/123;*/

create table Personaje(
    id varchar2(100) primary key,
    nombre varchar2(100) not null,
    genero varchar2(1),
    color_pelo varchar2(100),
    color_ojos varchar2(100),
    ocupacion varchar2(100),
    nacionalidad varchar2(100),
    estado_marital varchar2(100),
    creador varchar2(100),
    primera_aparicion varchar2(100),
    frase_celebre varchar2(100)
);
create table Heroe(
    id varchar2(100) primary key,
    nombre_heroe varchar2(100) not null,
    color_traje varchar2(100),
    logotipo varchar2(100),
    nemesis varchar2(100),
    foreign key (id) references Personaje(id)
);
create table Villano(
    id varchar2(100) primary key,
    nombre_villano varchar2(100) not null,
    objetivo varchar2(100),
    archienemigo varchar2(100),
    foreign key (id) references Personaje(id)
);

alter table Heroe add constraint FK_He foreign key (nemesis) references Villano(id);
alter table Villano add constraint FK_Vi foreign key (archienemigo) references Heroe(id);

create table Organizacion(
    id_organizacion varchar2(100) primary key,
    nombre varchar2(100) not null,
    eslogan varchar2(100),
    lider varchar2(100),
    tipo varchar2(100),
    lugar_creacion varchar2(100),
    fundador varchar2(100),
    objetivo varchar2(100),
    primera_aparicion varchar2(100)
);

create table Sede(
    id_organizacion varchar2(100),
    id_sede varchar2(20),
    nombre varchar2(100) not null,
    ubicacion varchar2(100),
    tipo_edificio varchar2(100),
    foreign key (id_organizacion) references Organizacion(id_organizacion),
    primary key (id_sede,id_organizacion)
);
create table Poder(
    id_poder varchar2(100) primary key,
    nombre varchar2(100) not null,
    descripcion varchar2(100)
);
create table Objeto(
    id_objeto varchar2(100) primary key,
    nombre varchar2(100) not null,
    creador varchar2(100),
    material varchar2(100),
    tipo varchar2(100),
    descripcion varchar2(100)
);
create table Medio(
    id_medio varchar2(100) primary key,
    titulo varchar2(100),
    fecha_estreno varchar2(100),
    compania varchar2(100),
    calificacion float,
    sinopsis varchar2(100)
);
create table Pelicula(
    id_medio varchar2(100) primary key,
    director varchar2(100),
    duracion varchar2(100),
    distribuidor varchar2(100),
    tipo_pelicula varchar2(100),
    coste_produccion varchar2(100),
    ganancia varchar2(100),
    foreign key (id_medio) references Medio(id_medio)
);
create table Serie(
    id_medio varchar2(100) primary key,
    creador varchar2(100),
    total_episodios varchar2(100),
    canal varchar2(100),
    tipo_serie varchar2(100),
    foreign key (id_medio) references Medio(id_medio)
);
create table Videojuego(
    id_medio varchar2(100) primary key,
    plataforma varchar2(100),
    tipo_juego varchar2(100),
    compania_publicacion varchar2(100),
    foreign key (id_medio) references Medio(id_medio)
);
create table Actor(
    nombre varchar2(100) primary key not null,
    genero varchar2(1),
    fecha_nacimiento varchar2(100),
    lugar_nacimiento varchar2(100),
    primera_pelicula varchar2(100)
);
create table Pertenece(
    id_personaje varchar2(100),
    id_organizacion varchar2(100),
    cargo varchar2(100),
    foreign key (id_personaje) references Personaje(id),
    foreign key (id_organizacion) references Organizacion(id_organizacion),
    primary key (id_personaje,id_organizacion,cargo)
);
create table Utiliza(
    id_personaje varchar2(100),
    id_poder varchar2(100),
    forma_obtencion varchar2(100),
    foreign key (id_personaje) references Personaje(id),
    foreign key (id_poder) references Poder(id_poder),
    primary key (id_personaje,id_poder)
);
create table Tiene(
    id_personaje varchar2(100),
    id_objeto varchar2(100),
    foreign key (id_personaje) references Personaje(id),
    foreign key (id_objeto) references Objeto(id_objeto),
    primary key (id_personaje,id_objeto)
);
create table Combate(
    personaje_1 varchar2(100),
    personaje_2 varchar2(100),
    id_poder varchar2(100),
    id_objeto varchar2(100),
    lugar varchar2(100),
    fecha varchar2(100),
    ganador varchar2(100),
    foreign key (personaje_1) references Personaje(id),
    foreign key (personaje_2) references Personaje(id),
    foreign key (id_objeto) references Objeto(id_objeto),
    foreign key (id_poder) references Poder(id_poder),
    primary key (personaje_1,personaje_2,id_poder,id_objeto,lugar,fecha)
);
create table Aparece(
    id_personaje varchar2(100),
    id_medio varchar2(100),
    id_actor varchar2(100),
    papel varchar2(10),
    forma_actuacion varchar2(100),
    foreign key (id_personaje) references Personaje(id),
    foreign key (id_medio) references Medio(id_medio),
    foreign key (id_actor) references Actor(nombre),
    primary key (id_personaje,id_actor,id_medio)
);
create table Participa(
    id_organizacion varchar2(100),
    id_medio varchar2(100),
    papel varchar2(100),
    estado_final varchar2(100),
    foreign key (id_organizacion) references Organizacion(id_organizacion),
    foreign key (id_medio) references Medio(id_medio),
    primary key (id_organizacion,id_medio)
);



--B.

alter table Personaje add constraint CK_genero CHECK (genero IN ('M','F'));

--C.

alter table Personaje add constraint CK_estadoM CHECK (estado_marital IN ('Soltero','Casado','Viudo'));

--D.



--E.



--F.

alter table Medio add constraint CK_clasificacion CHECK (calificacion between 0.1 and 10);

--G.

alter table Utiliza add constraint CK_FormaObtencion CHECK (forma_obtencion IN ('Natural','Natural Heredado','Artificial'));

--H.

alter table Aparece add constraint CK_papel1 CHECK (papel IN ('Protagonista','Antagonista','Secundario'));
alter table Participa add constraint CK_papel2 CHECK (papel IN ('Protagonista','Antagonista','Secundario'));

--I.

alter table Aparece add constraint CK_FormaActuacion CHECK (forma_actuacion IN ('Actor Liveaction','Actor de Voz'));

--J.

alter table Participa add constraint CK_EstadoFinal CHECK (estado_final IN ('Activa','Desmantelada','Disuelta'));

--K.

alter table Medio add constraint CK_clasificacion2 CHECK (calificacion >= 0);

--L.
--Listo en tablas

Create or Replace Procedure Insertar_Personaje(
    id in Personaje.id%type,
    Nom in Personaje.nombre%type,
    gen in Personaje.genero%type,
    color_p in Personaje.color_pelo%type,
    color_o in Personaje.color_ojos%type,
    ocup in Personaje.ocupacion%type,
    nac in Personaje.nacionalidad%type,
    estado_m in Personaje.estado_marital%type,
    crea in Personaje.creador%type,
    primera_a in Personaje.primera_aparicion%type,
    frase in Personaje.frase_celebre%type)
As begin
    insert into Personaje values(id,Nom,gen,color_p,color_o,ocup,nac,estado_m,crea,primera_a,frase);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Personaje;
/

Create or Replace Procedure Insertar_Heroe(
    id in Heroe.id%type,
    Nom in Heroe.nombre_heroe%type,
    color_t in Heroe.color_traje%type,
    logo in Heroe.logotipo%type,
    nem in Heroe.nemesis%type)
As begin
    insert into Heroe values(id,Nom,color_t,logo,nem);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Heroe;
/

Create or Replace Procedure Insertar_Villano(
    id in Villano.id%type,
    Nom in Villano.nombre_villano%type,
    obj in Villano.objetivo%type,
    archi in Villano.archienemigo%type)
As begin
    insert into Villano values(id,Nom,obj,archi);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Villano;
/

create or Replace Procedure Insertar_Organizacion(
    id in Organizacion.id_organizacion%type,
    Nom in Organizacion.nombre%type,
    eslo in Organizacion.eslogan%type,
    lider in Organizacion.lider%type,
    tip in Organizacion.tipo%type,
    lugar in Organizacion.lugar_creacion%type,
    fun in Organizacion.fundador%type,
    obj in Organizacion.objetivo%type,
    prim in Organizacion.primera_aparicion%type)
As begin
    insert into Organizacion values(id,Nom,eslo,lider,tip,lugar,fun,obj,prim);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Organizacion;
/

create or Replace Procedure Insertar_Sede(
    ido in Sede.id_organizacion%type,
    ids in Sede.id_sede%type,
    nom in Sede.nombre%type,
    ubi in Sede.ubicacion%type,
    tipo in Sede.tipo_edificio%type)
As begin
    insert into Sede values(ido,ids,nom,ubi,tipo);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Sede;
/

create or Replace Procedure Insertar_Poder(
    idp in Poder.id_poder%type,
    nom in Poder.nombre%type,
    des in Poder.descripcion%type)
As begin
    insert into Poder values(idp,nom,des);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Poder;
/

create or Replace Procedure Insertar_Objeto(
    ido in Objeto.id_objeto%type,
    nom in Objeto.nombre%type,
    crea in Objeto.creador%type,
    mate in Objeto.material%type,
    tip in Objeto.tipo%type,
    des in Objeto.descripcion%type)
As begin
    insert into Objeto values(ido,nom,crea,mate,tip,des);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Objeto;
/

create or Replace Procedure Insertar_Medio(
    idm in Medio.id_medio%type,
    tit in Medio.titulo%type,
    fecha in Medio.fecha_estreno%type,
    comp in Medio.compania%type,
    tip in Medio.calificacion%type,
    sino in Medio.sinopsis%type)
As begin
    insert into Medio values(idm,tit,fecha,comp,tip,sino);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Medio;
/

create or Replace Procedure Insertar_Pelicula(
    idp in Pelicula.id_medio%type,
    dir in Pelicula.director%type,
    dur in Pelicula.duracion%type,
    dis in Pelicula.distribuidor%type,
    tipo in Pelicula.tipo_pelicula%type,
    coste in Pelicula.coste_produccion%type,
    ganan in Pelicula.ganancia%type)
As begin
    insert into Pelicula values(idp,dir,dur,dis,tipo,coste,ganan);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Pelicula;
/

create or Replace Procedure Insertar_Serie(
    idm in Serie.id_medio%type,
    crea in Serie.creador%type,
    tot in Serie.total_episodios%type,
    can in Serie.canal%type,
    tipo in Serie.tipo_serie%type)
As begin
    insert into Serie values(idm,crea,tot,can,tipo);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Serie;
/

create or Replace Procedure Insertar_Videojuego(
    idm in Videojuego.id_medio%type,
    plat in Videojuego.plataforma%type,
    tip in Videojuego.tipo_juego%type,
    com in Videojuego.compania_publicacion%type)
As begin
    insert into Videojuego values(idm,plat,tip,com);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Videojuego;
/

create or Replace Procedure Insertar_Actor(
    nom in Actor.nombre%type,
    gen in Actor.genero%type,
    fecha in Actor.fecha_nacimiento%type,
    lugar in Actor.lugar_nacimiento%type,
    prime in Actor.primera_pelicula%type)
As begin
    insert into Actor values(nom,gen,fecha,lugar,prime);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Actor;
/

create or Replace Procedure Insertar_Pertenece(
    idp in Pertenece.id_personaje%type,
    ido in Pertenece.id_organizacion%type,
    carg in Pertenece.cargo%type)
As begin
    insert into Pertenece values(idp,ido,carg);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Pertenece;
/

create or Replace Procedure Insertar_Utiliza(
    idp in Utiliza.id_personaje%type,
    idp2 in Utiliza.id_poder%type,
    forma in Utiliza.forma_obtencion%type)
As begin
    insert into Utiliza values(idp,idp2,forma);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Utiliza;
/

create or Replace Procedure Insertar_Tiene(
    idp in Tiene.id_personaje%type,
    ido in Tiene.id_objeto%type)
As begin
    insert into Tiene values(idp,ido);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Tiene;
/

create or Replace Procedure Insertar_Combate(
    p1 in Combate.personaje_1%type,
    p2 in Combate.personaje_2%type,
    idp in Combate.id_poder%type,
    ido in Combate.id_objeto%type,
    lug in Combate.lugar%type,
    fech in Combate.fecha%type,
    gana in Combate.ganador%type)
As begin
    insert into Combate values(p1,p2,idp,ido,lug,fech,gana);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Combate;
/

create or Replace Procedure Insertar_Aparece(
    idp in Aparece.id_personaje%type,
    idm in Aparece.id_medio%type,
    ida in Aparece.id_actor%type,
    pap in Aparece.papel%type,
    form in Aparece.forma_actuacion%type)
As begin
    insert into Aparece values(idp,idm,ida,pap,form);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Aparece;
/

create or Replace Procedure Insertar_Participa(
    ido in Participa.id_organizacion%type,
    idm in Participa.id_medio%type,
    pap in Participa.papel%type,
    estado in Participa.estado_final%type)
As begin
    insert into Participa values(ido,idm,pap,estado);
    dbms_output.put_line('Insercion Exitosa');
end Insertar_Participa;
/