CREATE DATABASE IF NOT EXISTS reto2;
USE reto2;
CREATE TABLE autores (
    AutorID VARCHAR(255), 
    Nombre VARCHAR(255),
    Link VARCHAR(255), 
    CitadoPor INT,
    Email VARCHAR(255), 
    Afiliaciones VARCHAR(255)
);