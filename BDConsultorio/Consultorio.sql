create database Consultorio;
use Consultorio;

create table Empleados 
(
	idEmpleados int primary key not null,
    Nombre varchar(30),
    Apellidos varchar(30),
    Curp varchar(30),
    usuarioLog varchar(30),
    Contrasena varchar(30),
    Rol varchar(30),
    FirsTime Bit (1)
);


create table Paciente
(
	idPaciente int primary key not null,
    nombrePaciente varchar(30),
    apellidosPaciente varchar(30),
    CurpPaciente varchar(30),
    usuarioLog varchar(30),
    Contrasena varchar(30),
    FirsTime Bit (1),
    Diagnostico varchar(250),
    Receta varchar(250),
    Observacion varchar(250)
);
