-- Insertar 10 Estudiantes en la tabla estudiantes
INSERT INTO estudiantes (nombre, apellido_pat, apellido_mat, carrera, correo_institucional, voto) 
VALUES 
('Juan', 'Pérez', 'González', 'Ingeniería Informática', 'juan.perez@umsa.bo', false),
('Maria', 'López', 'Martínez', 'Medicina', 'maria.lopez@umsa.bo', true),
('Carlos', 'Hernández', 'Sánchez', 'Derecho', 'carlos.hernandez@umsa.bo', false),
('Laura', 'Díaz', 'Ramírez', 'Arquitectura', 'laura.diaz@umsa.bo', true),
('Pedro', 'García', 'Fernández', 'Biología', 'pedro.garcia@umsa.bo', true),
('Ana', 'Martínez', 'Torres', 'Psicología', 'ana.martinez@umsa.bo', false),
('Luis', 'Gómez', 'Rojas', 'Ingeniería de Sistemas', 'luis.gomez@umsa.bo', true),
('Elena', 'Jiménez', 'Vázquez', 'Ciencias Políticas', 'elena.jimenez@umsa.bo', true),
('Javier', 'Morales', 'Cruz', 'Economía', 'javier.morales@umsa.bo', false),
('Sofía', 'Ramírez', 'Méndez', 'Educación', 'sofia.ramirez@umsa.bo', true);

-- Insertar 10 Docentes en la tabla docentes
INSERT INTO docentes (nombre, apellido_pat, apellido_mat, carrera, correo_institucional, voto) 
VALUES 
('Carlos', 'Hernández', 'Sánchez', 'Ingeniería de Sistemas', 'carlos.hernandez@umsa.bo', false),
('María', 'Gómez', 'Torres', 'Medicina', 'maria.gomez@umsa.bo', true),
('José', 'Martínez', 'López', 'Derecho', 'jose.martinez@umsa.bo', false),
('Elena', 'Pérez', 'Fernández', 'Ciencias Sociales', 'elena.perez@umsa.bo', true),
('Ricardo', 'Ramírez', 'García', 'Contabilidad', 'ricardo.ramirez@umsa.bo', true),
('Laura', 'Díaz', 'Méndez', 'Arquitectura', 'laura.diaz@umsa.bo', false),
('Javier', 'García', 'Cruz', 'Psicología', 'javier.garcia@umsa.bo', true),
('Ana', 'Jiménez', 'Vázquez', 'Ingeniería Informática', 'ana.jimenez@umsa.bo', true),
('Luis', 'Morales', 'Castillo', 'Biología', 'luis.morales@umsa.bo', false),
('Sofía', 'Ramírez', 'Mendoza', 'Educación', 'sofia.ramirez@umsa.bo', true);

-- Insertar 10 partidos en la tabla partidos
INSERT INTO partidos (nombre_partido, sigla, lema) 
VALUES 
('Partido Nacional', 'PN', 'Unidos por el cambio'),
('Partido Verde', 'PV', 'Por un futuro sostenible'),
('Partido del Pueblo', 'PP', 'La fuerza de todos'),
('Partido Liberal', 'PL', 'Libertad y progreso'),


INSERT INTO candidatos (id_candidato, cargo, id_docente, id_partido)
VALUES
    (1, 'presidente', 1, 1),
    (2, 'presidente', 2, 2),
    (3, 'presidente', 3, 3),
    (4, 'presidente', 4, 4);


-- Ejemplo: Insertar votos de estudiantes
INSERT INTO votos (id_estudiante, id_partido, id_candidato)
VALUES
  (1, 1, 1),
  (2, 2, 2),
  (3, 1, 3),
  (4, 2, 4),
  (5, 1, 2),
  (6, 2, 2),
  (7, 1, 2),
  (8, 2, 1);

-- Ejemplo: Insertar votos de docentes
INSERT INTO votos (id_docente, id_partido, id_candidato)
VALUES
  (1, 1, 1),
  (2, 2, 2),
  (3, 1, 3),
  (4, 2, 4),
  (5, 1, 2),
  (6, 2, 3),
  (7, 1, 4),
  (8, 2, 1),
  (9, 1, 2),
  (10, 2, 1);