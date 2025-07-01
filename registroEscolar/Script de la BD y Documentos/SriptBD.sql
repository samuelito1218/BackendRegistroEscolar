CREATE SCHEMA pruebatecnicabd;
USE pruebatecnicabd;

CREATE TABLE persona (
	id_persona INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR (100) NOT NULL,
    apellido VARCHAR (100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    email VARCHAR (100) NOT NULL UNIQUE,
    telefono VARCHAR (50) NOT NULL,
    PRIMARY KEY (id_persona)
);

-- Crear tabla Estudiante
CREATE TABLE estudiante (
	id_persona INT NOT NULL,
    numero_matricula INT NOT NULL UNIQUE,
    grado INT NOT NULL,
    PRIMARY KEY (id_persona),
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Tabla profesor
CREATE TABLE profesor (
	id_persona INT NOT NULL,
    especialidad VARCHAR(100) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    PRIMARY KEY (id_persona),
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Tabla Administrativo
CREATE TABLE administrativo (
	id_persona INT NOT NULL,
    cargo VARCHAR (100) NOT NULL,
    departamento VARCHAR (100) NOT NULL,
    PRIMARY KEY (id_persona),
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Tabla Curso
CREATE TABLE curso (
	id_curso INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR (100) NOT NULL,
    descripcion VARCHAR (200) NOT NULL,
    creditos INT NOT NULL,
    id_profesor INT NOT NULL,
    PRIMARY KEY (id_curso),
    FOREIGN KEY (id_profesor) REFERENCES profesor(id_persona)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Tabla Inscripción
CREATE TABLE inscripcion (
	id_inscripcion INT NOT NULL AUTO_INCREMENT,
    id_estudiante INT NOT NULL,
    id_curso INT NOT NULL,
    fecha_inscripcion DATE NOT NULL,
    PRIMARY KEY (id_inscripcion),
    FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_persona)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (id_curso) REFERENCES curso(id_curso)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Consultas iniciales
SELECT * FROM persona;
SELECT * FROM estudiante;
SELECT * FROM profesor;
SELECT * FROM administrativo;
SELECT * FROM curso;
SELECT * FROM inscripcion;

-- Registros básicos
INSERT INTO persona (nombre, apellido, fecha_nacimiento, email, telefono) VALUES
('Carlos', 'Ramírez', '2005-03-10', 'carlos.ramirez@mail.com', '3114567890'),
('María', 'López', '2004-08-22', 'maria.lopez@mail.com', '3101234567'),
('Julián', 'Martínez', '1980-05-15', 'julian.martinez@mail.com', '3127896541'),
('Lucía', 'Gómez', '1990-12-01', 'lucia.gomez@mail.com', '3134561237'),
('Sofía', 'Torres', '1995-07-19', 'sofia.torres@mail.com', '3148765432'),
('Andrés', 'Fernández', '1985-10-25', 'andres.fernandez@mail.com', '3159876543');

-- Registros para estudiantes (jóvenes)
INSERT INTO persona (nombre, apellido, fecha_nacimiento, email, telefono) VALUES
('Laura', 'Muñoz', '2002-04-12', 'laura.munoz@mail.com', '3109876543'),
('Mateo', 'Castro', '2003-09-30', 'mateo.castro@mail.com', '3112345678'),
('Valentina', 'Rojas', '2001-11-20', 'valentina.rojas@mail.com', '3123456789'),
('Sebastián', 'Vargas', '2004-06-08', 'sebastian.vargas@mail.com', '3134567890'),
('Daniela', 'Pérez', '2005-02-14', 'daniela.perez@mail.com', '3145678901'),
('Juan', 'Ortiz', '2000-07-22', 'juan.ortiz@mail.com', '3156789012'),
('Isabela', 'Morales', '2006-10-03', 'isabela.morales@mail.com', '3167890123'),
('Tomás', 'Navarro', '2003-03-17', 'tomas.navarro@mail.com', '3178901234'),
('Camila', 'Cárdenas', '2002-08-25', 'camila.cardenas@mail.com', '3189012345'),
('Samuel', 'Reyes', '2001-05-06', 'samuel.reyes@mail.com', '3190123456');

-- Registros para profesores/administrativos (adultos)
INSERT INTO persona (nombre, apellido, fecha_nacimiento, email, telefono) VALUES
('Carlos', 'Mendoza', '1980-02-15', 'carlos.mendoza@mail.com', '3101112233'),
('Diana', 'Salazar', '1985-06-30', 'diana.salazar@mail.com', '3112223344'),
('Fernando', 'García', '1978-11-12', 'fernando.garcia@mail.com', '3123334455'),
('Patricia', 'Acosta', '1982-09-08', 'patricia.acosta@mail.com', '3134445566'),
('Luis', 'Córdoba', '1975-04-21', 'luis.cordoba@mail.com', '3145556677'),
('Martha', 'Ramírez', '1968-12-05', 'martha.ramirez@mail.com', '3156667788'),
('Héctor', 'Velásquez', '1988-07-10', 'hector.velasquez@mail.com', '3167778899'),
('Gloria', 'Jiménez', '1979-10-19', 'gloria.jimenez@mail.com', '3178889900'),
('Ricardo', 'Santos', '1983-01-01', 'ricardo.santos@mail.com', '3189990011'),
('Ángela', 'Castillo', '1970-05-28', 'angela.castillo@mail.com', '3190001122');
