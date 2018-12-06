create database consultorio;
use consultorio;

create table Pacientes 
(
	idPaciente int primary key not null,
    nombrePaciente varchar(15),
    apellidosPaciente varchar(40),
    curpPaciente varchar(30),
    usuarioPaciente varchar(15),
    contrasenaPaciente varchar(15)
);

create table Consultas
(
	idConsulta int primary key not null,
    idPaciente int,
    idReceta int,
    fecha varchar(15),
    diagnostico varchar(250),
    peso varchar(10),
    edad int
);

create table Recetas
(
	idReceta int primary key not null,
    descripcion varchar(250)
);

#Prueba de insercion de datos
insert into pacientes 
values(1, "juan", "Jos", "J12", "J", "J");

insert into consultas
values (1, 1, 1, "01/11/2011", "lorem ipsum", 100, 20);

insert into recetas
values(1, "Lorem Ipsum sdadasdasdasdasd");

#Prueba de muestreo de multiples tablas
select * from pacientes as P, consultas as C, recetas as R where P.idPaciente = C.idPaciente and R.idReceta = C.idReceta;