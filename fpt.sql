CREATE OR REPLACE FUNCTION contar_votos_candidatos()
RETURNS TABLE (
    candidato_id BIGINT,  -- Cambié de INT a BIGINT
    candidato_nombre VARCHAR(100),
    total_votos BIGINT
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        c.id_candidato,
        d.nombre AS candidato_nombre,  -- Usamos el nombre del docente (candidato)
        COUNT(v.id_voto) AS total_votos
    FROM 
        candidatos c
    LEFT JOIN 
        votos v ON c.id_candidato = v.id_candidato
    LEFT JOIN 
        docentes d ON c.id_docente = d.id_docente  -- JOIN con la tabla `docentes`
    GROUP BY 
        c.id_candidato, d.nombre  -- Agrupamos por id_candidato y el nombre del docente
    ORDER BY 
        total_votos DESC;
END;
$$ LANGUAGE plpgsql;



SELECT * FROM contar_votos_candidatos();





CREATE OR REPLACE FUNCTION contar_votos_por_partido_y_candidato()
RETURNS TABLE (
    nombre_partido VARCHAR(100),
    nombre_candidato VARCHAR(100),
    total_votos BIGINT
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        p.nombre_partido,
        d.nombre AS nombre_candidato,
        COUNT(v.id_voto) AS total_votos
    FROM 
        votos v
    JOIN 
        candidatos c ON v.id_candidato = c.id_candidato
    JOIN 
        partidos p ON c.id_partido = p.id_partido
    JOIN 
        docentes d ON c.id_docente = d.id_docente
    GROUP BY 
        p.nombre_partido, d.nombre
    ORDER BY 
        total_votos DESC;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM contar_votos_por_partido_y_candidato();



CREATE OR REPLACE FUNCTION registrar_auditoria_estudiante()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO auditoria_estudiante(
            accion, id_estudiante,
            nombre_anterior, apellido_pat_anterior, apellido_mat_anterior, carrera_anterior, correo_anterior,
            nombre_nuevo, apellido_pat_nuevo, apellido_mat_nuevo, carrera_nueva, correo_nuevo,
            fecha
        ) VALUES (
            TG_OP,
            NEW.id_estudiante,
            NULL, NULL, NULL, NULL, NULL,
            NEW.nombre, NEW.apellido_pat, NEW.apellido_mat, NEW.carrera, NEW.correo_institucional,
            NOW()
        );
        RETURN NEW;
    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO auditoria_estudiante(
            accion, id_estudiante,
            nombre_anterior, apellido_pat_anterior, apellido_mat_anterior, carrera_anterior, correo_anterior,
            nombre_nuevo, apellido_pat_nuevo, apellido_mat_nuevo, carrera_nueva, correo_nuevo,
            fecha
        ) VALUES (
            TG_OP,
            OLD.id_estudiante,
            OLD.nombre, OLD.apellido_pat, OLD.apellido_mat, OLD.carrera, OLD.correo_institucional,
            NEW.nombre, NEW.apellido_pat, NEW.apellido_mat, NEW.carrera, NEW.correo_institucional,
            NOW()
        );
        RETURN NEW;
    ELSE -- DELETE
        INSERT INTO auditoria_estudiante(
            accion, id_estudiante,
            nombre_anterior, apellido_pat_anterior, apellido_mat_anterior, carrera_anterior, correo_anterior,
            nombre_nuevo, apellido_pat_nuevo, apellido_mat_nuevo, carrera_nueva, correo_nuevo,
            fecha
        ) VALUES (
            TG_OP,
            OLD.id_estudiante,
            OLD.nombre, OLD.apellido_pat, OLD.apellido_mat, OLD.carrera, OLD.correo_institucional,
            NULL, NULL, NULL, NULL, NULL,
            NOW()
        );
        RETURN OLD;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_auditoria_estudiante
AFTER INSERT OR UPDATE OR DELETE ON estudiantes
FOR EACH ROW
EXECUTE FUNCTION registrar_auditoria_estudiante();

SELECT * FROM auditoria_estudiante;