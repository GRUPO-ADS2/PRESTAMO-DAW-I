use railway;

CREATE PROCEDURE actualizarEstadoSolicitud(
    IN p_solicitud_id INT,
    IN p_nuevo_estado VARCHAR(15)
)
BEGIN
    UPDATE Solicitud
    SET Estado = p_nuevo_estado
    WHERE IdSolicitud = p_solicitud_id;
END ;
