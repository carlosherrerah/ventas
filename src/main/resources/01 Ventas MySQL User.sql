CREATE DATABASE IF NOT EXISTS ventas;

-- drop user 'ventas'@'localhost';     sensible a Mayusculas

CREATE USER 'ventas'@'%' IDENTIFIED BY 'ventas';

GRANT ALL PRIVILEGES ON ventas.* TO 'ventas'@'%';

