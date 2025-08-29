drop database if exists kinvana_db;
create database kinvana_db;
use kinvana_db;
 
create table Clientes(
	codigo_cliente integer auto_increment,
    nombre varchar (64),
    apellido varchar (64),
    telefono varchar (16),
    correo varchar (128),
    genero enum ('masculino','femenino','no'),
    edad integer,
    constraint pk_cliente primary key (codigo_cliente)
);

INSERT INTO Clientes(nombre, apellido, telefono, correo, genero, edad)
VALUES 
('Carlos', 'Ramírez', '12345678', 'carlos.ramirez@gmail.com', 'masculino', 28),
('María', 'González', '98765432', 'maria.gonzalez@hotmail.com', 'femenino', 34),
('Luis', 'Martínez', '55667788', 'luis.martinez@yahoo.com', 'masculino', 22),
('Ana', 'Pérez', '66778899', 'ana.perez@gmail.com', 'femenino', 29),
('Fernando', 'López', '77889900', 'fernando.lopez@outlook.com', 'masculino', 41),
('Laura', 'Hernández', '88990011', 'laura.hernandez@gmail.com', 'femenino', 25),
('José', 'Castillo', '99001122', 'jose.castillo@gmail.com', 'masculino', 36),
('Sofía', 'Méndez', '11112222', 'sofia.mendez@gmail.com', 'femenino', 31);