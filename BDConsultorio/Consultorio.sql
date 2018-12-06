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

insert into Empleados (idEmpleados,Nombre,Apellidos,Curp,usuarioLog,Contrasena,Rol)
values 
(0001,'Lalo','Tampoco Se','LOLI93217XHCR','Lalona','Ceti','enfermera'),
(0002,'Filanito','Detal','DETI93217XHCR','Solovino','Porsucuenta','farmaceutico'),
(0003,'Rafa','Nise','RFG93217XHCR','Rafis','dormido','Paciente'),
(0004,'Elprofe','Elmachio','MACHI93217XHCR','Leon','CuandoHasvisto','enfermera');

drop table empleados;
select * from Empleados;
delete from Empleados;

delete from Empleados where idEmpleados=0;

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
