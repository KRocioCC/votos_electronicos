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
