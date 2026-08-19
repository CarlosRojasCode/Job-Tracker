CREATE TABLE candidatura (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    comentarios VARCHAR(255),
    empresa VARCHAR(255),
    enlace VARCHAR(255),
    estado VARCHAR(255),
    fecha_envio DATE,
    puesto VARCHAR(255),
    PRIMARY KEY(id)
);

