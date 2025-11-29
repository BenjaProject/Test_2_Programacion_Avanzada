drop database prgavz;
create database prgavz;
use prgavz;

create table user(
id int auto_increment primary key,
usuario varchar(50),
pass varchar(50),
rol varchar(50)
);

create table Persona(
rut int primary key,
nombre varchar(50),
apellido_paterno varchar(50),
apellido_materno varchar(50),
direccion varchar(50)
);

create table curso(
id_curso int auto_increment primary key,
nombre_curso varchar(50),
nivel int,
anio_academico int
);

create table Alumno(
id int AUTO_INCREMENT primary key,
rut int,
FOREIGN KEY (rut) REFERENCES Persona(rut), 
id_curso int,
FOREIGN KEY (id_curso) REFERENCES curso(id_curso),
cantidad_atrasos int,
cantidad_inasistencias int
);

CREATE TABLE Atraso (
    id_atraso INT AUTO_INCREMENT PRIMARY KEY,
    id_alumno INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    razon VARCHAR(100),

    FOREIGN KEY (id_alumno)
        REFERENCES Alumno(id)
        ON DELETE CASCADE
);
CREATE TABLE Inasistencia (
    id_inasistencia INT AUTO_INCREMENT PRIMARY KEY,
    id_alumno INT NOT NULL,
    fecha DATE NOT NULL,
    justificada BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (id_alumno)
        REFERENCES Alumno(id)
        ON DELETE CASCADE
);
insert into user (usuario,pass,rol) values ('Leo','1234', 'Profesor');


delimiter //
CREATE TRIGGER aumentaAtrasosAlumno AFTER INSERT ON Atraso
FOR EACH ROW
BEGIN
    UPDATE Alumno SET cantidad_atrasos = cantidad_atrasos + 1
    WHERE Alumno.id = NEW.id_alumno;
END;//
delimiter ;

delimiter //
CREATE TRIGGER aumentaInasistenciasAlumno AFTER INSERT ON Inasistencia
FOR EACH ROW
BEGIN
    UPDATE Alumno SET cantidad_inasistencias = cantidad_inasistencias + 1
    WHERE Alumno.id = NEW.id_alumno;
END;//
delimiter ;

delimiter //
CREATE TRIGGER disminuyeAtrasosAlumno AFTER DELETE ON Atraso
FOR EACH ROW
BEGIN
    UPDATE Alumno SET cantidad_atrasos = cantidad_atrasos - 1
    WHERE Alumno.id = old.id_alumno;
END;//
delimiter ;

delimiter //
CREATE TRIGGER disminuyeInasistenciasAlumno AFTER DELETE ON Inasistencia
FOR EACH ROW
BEGIN
    UPDATE Alumno SET cantidad_inasistencias = cantidad_inasistencias - 1
    WHERE Alumno.id = old.id_alumno;
END;//
delimiter ;

INSERT INTO curso (nombre_curso, nivel, anio_academico) VALUES 
('1° Medio', 1, 2024),
('2° Medio', 2, 2024),
('3° Medio', 3, 2024),
('4° Medio', 4, 2024);


INSERT INTO Persona (rut, nombre, apellido_paterno, apellido_materno, direccion) VALUES
(22000001, 'Juan', 'Pérez', 'González', 'Calle Falsa 123'),
(22000002, 'María', 'López', 'Rodríguez', 'Av. Siempre Viva 742'),
(22000003, 'Carlos', 'Muñoz', 'Sánchez', 'Los Alamos 11'),
(22000004, 'Ana', 'Rojas', 'Díaz', 'Pasaje 4, Casa 2'),
(22000005, 'Pedro', 'Soto', 'Torres', 'El Roble 55'),
(22000006, 'Laura', 'Vargas', 'Ruiz', 'Los Pinos 88'),
(22000007, 'Diego', 'Castro', 'Flores', 'Alameda 99'),
(22000008, 'Sofia', 'Morales', 'Romero', 'Centro 101'),
(22000009, 'Javier', 'Ortiz', 'Herrera', 'Norte 200'),
(22000010, 'Camila', 'Silva', 'Molina', 'Sur 300');


INSERT INTO Alumno (rut, id_curso, cantidad_atrasos, cantidad_inasistencias) VALUES
(22000001, 1, 0, 0), (22000002, 1, 0, 0), (22000003, 1, 0, 0), (22000004, 1, 0, 0),
(22000005, 1, 0, 0), (22000006, 1, 0, 0), (22000007, 1, 0, 0), (22000008, 1, 0, 0),
(22000009, 1, 0, 0), (22000010, 1, 0, 0);


INSERT INTO Persona (rut, nombre, apellido_paterno, apellido_materno, direccion) VALUES
(22000011, 'Felipe', 'Navarro', 'Castillo', 'Calle A 1'),
(22000012, 'Valentina', 'Reyes', 'Gutiérrez', 'Calle B 2'),
(22000013, 'Matias', 'Pardo', 'Salazar', 'Calle C 3'),
(22000014, 'Isidora', 'Bravo', 'Méndez', 'Calle D 4'),
(22000015, 'Tomas', 'Cortes', 'Vega', 'Calle E 5'),
(22000016, 'Fernanda', 'Carrasco', 'Fuentes', 'Calle F 6'),
(22000017, 'Nicolas', 'Sandoval', 'Espinoza', 'Calle G 7'),
(22000018, 'Catalina', 'Vergara', 'Lagos', 'Calle H 8'),
(22000019, 'Benjamin', 'Araya', 'Palma', 'Calle I 9'),
(22000020, 'Antonia', 'Sepúlveda', 'Riquelme', 'Calle J 10');

INSERT INTO Alumno (rut, id_curso, cantidad_atrasos, cantidad_inasistencias) VALUES
(22000011, 2, 0, 0), (22000012, 2, 0, 0), (22000013, 2, 0, 0), (22000014, 2, 0, 0),
(22000015, 2, 0, 0), (22000016, 2, 0, 0), (22000017, 2, 0, 0), (22000018, 2, 0, 0),
(22000019, 2, 0, 0), (22000020, 2, 0, 0);

INSERT INTO Persona (rut, nombre, apellido_paterno, apellido_materno, direccion) VALUES
(22000021, 'Joaquin', 'Fuenzalida', 'Guzmán', 'Lomas 20'),
(22000022, 'Martina', 'Peña', 'Vidal', 'Lomas 21'),
(22000023, 'Vicente', 'Salinas', 'Cárdenas', 'Lomas 22'),
(22000024, 'Florencia', 'Zúñiga', 'Maldonado', 'Lomas 23'),
(22000025, 'Cristobal', 'Aguilera', 'Bustos', 'Lomas 24'),
(22000026, 'Francisca', 'Orellana', 'Pizarro', 'Lomas 25'),
(22000027, 'Martin', 'Jara', 'Saavedra', 'Lomas 26'),
(22000028, 'Emilia', 'Henríquez', 'Toledo', 'Lomas 27'),
(22000029, 'Sebastian', 'Alvarado', 'Guerra', 'Lomas 28'),
(22000030, 'Constanza', 'Donoso', 'Poblete', 'Lomas 29');

INSERT INTO Alumno (rut, id_curso, cantidad_atrasos, cantidad_inasistencias) VALUES
(22000021, 3, 0, 0), (22000022, 3, 0, 0), (22000023, 3, 0, 0), (22000024, 3, 0, 0),
(22000025, 3, 0, 0), (22000026, 3, 0, 0), (22000027, 3, 0, 0), (22000028, 3, 0, 0),
(22000029, 3, 0, 0), (22000030, 3, 0, 0);


INSERT INTO Persona (rut, nombre, apellido_paterno, apellido_materno, direccion) VALUES
(22000031, 'Gabriel', 'Baeza', 'Moya', 'Rio 1'),
(22000032, 'Daniela', 'Villegas', 'Godoy', 'Rio 2'),
(22000033, 'Ignacio', 'Parra', 'Medina', 'Rio 3'),
(22000034, 'Paulina', 'Carrillo', 'Pino', 'Rio 4'),
(22000035, 'Francisco', 'Gálvez', 'Rivera', 'Rio 5'),
(22000036, 'Macarena', 'Lara', 'Serrano', 'Rio 6'),
(22000037, 'Agustin', 'Miranda', 'Correa', 'Rio 7'),
(22000038, 'Pia', 'Nuñez', 'Barrios', 'Rio 8'),
(22000039, 'Maximo', 'Valenzuela', 'Urra', 'Rio 9'),
(22000040, 'Rocio', 'Tapia', 'Gallardo', 'Rio 10');

INSERT INTO Alumno (rut, id_curso, cantidad_atrasos, cantidad_inasistencias) VALUES
(22000031, 4, 0, 0), (22000032, 4, 0, 0), (22000033, 4, 0, 0), (22000034, 4, 0, 0),
(22000035, 4, 0, 0), (22000036, 4, 0, 0), (22000037, 4, 0, 0), (22000038, 4, 0, 0),
(22000039, 4, 0, 0), (22000040, 4, 0, 0);


