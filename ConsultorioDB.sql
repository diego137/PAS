create database consultorio;
use consultorio;

CREATE TABLE `pacientes` (
  `idPaciente` int(11) NOT NULL auto_increment,
  `nombrePaciente` varchar(40) DEFAULT NULL,
  `apellidosPaciente` varchar(40) DEFAULT NULL,
  `curpPaciente` varchar(30) DEFAULT NULL,
  `usuarioPaciente` varchar(15) DEFAULT NULL,
  `contrasenaPaciente` varchar(15) DEFAULT NULL,
  `FirstTime` bit(1) DEFAULT b'1',
  PRIMARY KEY (`idPaciente`)
);

create table Consultas
(
	idConsulta int primary key not null AUTO_INCREMENT,
    idPaciente int,
    idReceta int,
    fecha varchar(15),
    diagnostico varchar(250),
    peso varchar(10),
    edad int
);

create table Recetas
(
	idReceta int primary key not null AUTO_INCREMENT,
    descripcion varchar(250)
);

CREATE TABLE `empleados` (
  `idEmpleados` int(11) NOT NULL auto_increment,
  `Nombre` varchar(40) DEFAULT NULL,
  `Apellidos` varchar(40) DEFAULT NULL,
  `Curp` varchar(30) DEFAULT NULL,
  `usuarioLog` varchar(15) DEFAULT NULL,
  `Contrasena` varchar(15) DEFAULT NULL,
  `Rol` varchar (40) DEFAULT NULL,
  `FirstTime` bit(1) DEFAULT b'1',
  PRIMARY KEY (`idEmpleados`)
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