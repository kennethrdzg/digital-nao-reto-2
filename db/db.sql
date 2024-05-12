CREATE DATABASE IF NOT EXISTS autores;
USE autores;
CREATE TABLE IF NOT EXISTS autores (
    AutorID VARCHAR(255), 
    Nombre VARCHAR(255),
    Link VARCHAR(255), 
    CitadoPor INT,
    Email VARCHAR(255), 
    Afiliaciones VARCHAR(255)
);
SELECT * FROM autores;