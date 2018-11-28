/*create database Hospital;*/
use Hospital;

/*Empleados*/
create table Doctores
(
	idDoctor int primary key not null,
    nombreDoctor varchar(30),
    apellidoDoctor varchar(30),
    idUsuario int,
	cedula decimal
);

create table Enfermera
(
	idEnfermera int primary key not null,
    idUsuario int,
    nombreEnfermera varchar(30),
    apellidoEnfermera varchar(30)
);

create table Paciente
(
	idPaciente int primary key not null,
    idUsuario int,
    nombrePaciente varchar(30),
    apellidoPaciente varchar(30)
);

create table Usuario
(
	idUsuario int primary key not null,
    nombreUsuario varchar(30),
    contrasena int,
    rol varchar(30)
);

#Creamos la llave foranea y se la asignamos a idEmpleado
alter table Usuario
add foreign key (idUsuario)
references Doctores(idUsuario);

/*--Otras-*/
create table Farmaceutico
(
	idMedicamento int primary key not null,
    medicamento varchar(30),
    medida varchar(30),
    existencia int
);

create table Consulta
(
	idConsulta int primary key not null,
    fecha datetime,
    asistencia boolean,
    idRecetaMedica int
);

create table RecetaMedica
(
	idRecetaMedica int primary key not null,
    edad int,
    peso int,
    medicmento varchar(30),
    sexo varchar(10),
    diagnostico varchar(250)
    
);