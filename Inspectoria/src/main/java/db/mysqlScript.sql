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

create table Funcionario(
id_funcionario int auto_increment primary key,
rut int,
FOREIGN KEY (rut) REFERENCES Persona(rut), 
cursos varchar(50)
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


