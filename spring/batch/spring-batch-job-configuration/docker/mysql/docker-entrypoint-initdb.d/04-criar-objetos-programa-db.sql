USE programa_db;

CREATE TABLE USUARIOS
(
	ID VARCHAR(36) NOT NULL PRIMARY KEY,
	NOME VARCHAR(256) NOT NULL,
	SOBRENOME VARCHAR(256) NOT NULL,
	USUARIO VARCHAR(32) NOT NULL
) ENGINE=InnoDB;

DROP TRIGGER IF EXISTS atraso_escrita_usuarios;

DELIMITER $$
CREATE TRIGGER atraso_escrita_usuarios
BEFORE INSERT ON USUARIOS
FOR EACH ROW BEGIN
	DO sleep(0.000016);
END $$