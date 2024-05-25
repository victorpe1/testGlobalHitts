CREATE TABLE productos (
    id_producto NUMBER PRIMARY KEY,
    nombre VARCHAR2(255),
    fec_registro DATE
);


CREATE OR REPLACE PROCEDURE sp_insertAndListProducts (
    idProducto IN NUMBER,
    nombre IN VARCHAR2,
    fec_registro IN DATE,
    cursor OUT SYS_REFCURSOR,
    codigoRespuesta OUT NUMBER,
    mensajeRespuesta OUT VARCHAR2
) AS
BEGIN
    BEGIN
        INSERT INTO productos (id_producto, nombre, fec_registro)
        VALUES (idProducto, nombre, fec_registro);

        OPEN cursor FOR
            SELECT id_producto, nombre, fec_registro
            FROM productos
            WHERE TO_CHAR(fec_registro, 'YYYYMMDD') = TO_CHAR(SYSDATE, 'YYYYMMDD');

        codigoRespuesta := 0;
        mensajeRespuesta := 'Ejecución con éxito';
    EXCEPTION
        WHEN OTHERS THEN
            codigoRespuesta := 1;
            mensajeRespuesta := SQLERRM;
    END;
END sp_insertAndListProducts;