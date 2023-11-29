CREATE DATABASE IF NOT EXISTS VENTAS;

-- drop user 'ventas'@'localhost';

CREATE USER 'ventas'@'localhost' IDENTIFIED BY 'ventas';

GRANT ALL PRIVILEGES ON VENTAS.* TO 'ventas'@'localhost';

