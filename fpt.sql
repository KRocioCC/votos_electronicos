CREATE OR REPLACE FUNCTION registrar_auditoria_estudiante()
RETURNS TRIGGER AS $$
DECLARE
    datos_votante votantes%ROWTYPE;
BEGIN
    IF TG_OP = 'INSERT' THEN
        SELECT * INTO datos_votante FROM votantes WHERE id_votante = NEW.id_votante;

        INSERT INTO auditoria_estudiante(
            accion, id_estudiante,
            nombre_anterior, apellido_pat_anterior, apellido_mat_anterior, carrera_anterior, correo_anterior,
            nombre_nuevo, apellido_pat_nuevo, apellido_mat_nuevo, carrera_nueva, correo_nuevo,
            fecha
        )
        VALUES (
            TG_OP,
            NEW.id_votante,
            NULL, NULL, NULL, NULL, NULL,
            datos_votante.nombre, datos_votante.apellido_pat, datos_votante.apellido_mat,
            datos_votante.carrera, datos_votante.correo_institucional,
            NOW()
        );
        RETURN NEW;

    ELSIF TG_OP = 'DELETE' THEN
        SELECT * INTO datos_votante FROM votantes WHERE id_votante = OLD.id_votante;

        INSERT INTO auditoria_estudiante(
            accion, id_estudiante,
            nombre_anterior, apellido_pat_anterior, apellido_mat_anterior, carrera_anterior, correo_anterior,
            nombre_nuevo, apellido_pat_nuevo, apellido_mat_nuevo, carrera_nueva, correo_nuevo,
            fecha
        )
        VALUES (
            TG_OP,
            OLD.id_votante,
            datos_votante.nombre, datos_votante.apellido_pat, datos_votante.apellido_mat,
            datos_votante.carrera, datos_votante.correo_institucional,
            NULL, NULL, NULL, NULL, NULL,
            NOW()
        );
        RETURN OLD;
    ELSE
        -- UPDATE: usar ambos
        SELECT * INTO datos_votante FROM votantes WHERE id_votante = NEW.id_votante;

        INSERT INTO auditoria_estudiante(
            accion, id_estudiante,
            nombre_anterior, apellido_pat_anterior, apellido_mat_anterior, carrera_anterior, correo_anterior,
            nombre_nuevo, apellido_pat_nuevo, apellido_mat_nuevo, carrera_nueva, correo_nuevo,
            fecha
        )
        VALUES (
            TG_OP,
            NEW.id_votante,
            datos_votante.nombre, datos_votante.apellido_pat, datos_votante.apellido_mat,
            datos_votante.carrera, datos_votante.correo_institucional,
            datos_votante.nombre, datos_votante.apellido_pat, datos_votante.apellido_mat,
            datos_votante.carrera, datos_votante.correo_institucional,
            NOW()
        );
        RETURN NEW;
    END IF;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER trigger_auditoria_estudiante
AFTER INSERT OR UPDATE OR DELETE ON estudiantes
FOR EACH ROW
EXECUTE FUNCTION registrar_auditoria_estudiante();




SELECT * FROM auditoria_estudiante ORDER BY fecha DESC;





CREATE OR REPLACE FUNCTION contar_votos_por_partido()
RETURNS TABLE (
    nombre_partido VARCHAR(100),
    total_votos BIGINT
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        p.nombre_partido,
        COUNT(v.id_voto) AS total_votos
    FROM 
        votos v
    JOIN 
        partidos p ON v.id_partido = p.id_partido
    GROUP BY 
        p.nombre_partido
    ORDER BY 
        total_votos DESC;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM contar_votos_por_partido();



CREATE OR REPLACE FUNCTION top_carreras_con_mas_votos()
RETURNS TABLE (
  carrera VARCHAR(100),
  total_votos BIGINT
) AS $$
BEGIN
  RETURN QUERY
  SELECT 
    vtn.carrera,
    COUNT(v.id_voto) AS total_votos
  FROM votos v
  JOIN votantes vtn ON v.id_votante = vtn.id_votante
  GROUP BY vtn.carrera
  ORDER BY total_votos DESC
  LIMIT 3;
END;
$$ LANGUAGE plpgsql;
DROP FUNCTION IF EXISTS top_carreras_con_mas_votos();


SELECT * FROM top_carreras_con_mas_votos();
