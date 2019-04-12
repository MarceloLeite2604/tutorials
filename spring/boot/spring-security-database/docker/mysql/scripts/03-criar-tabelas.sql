CREATE TABLE programa_db.usuarios (
	usuario VARCHAR(45) NOT NULL,
	senha VARCHAR(128) NOT NULL,
	habilitado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (usuario)
);

CREATE TABLE programa_db.papeis_usuarios (
	id_papel_usuario int(11) NOT NULL AUTO_INCREMENT,
	usuario varchar(45) NOT NULL,
	papel varchar(45) NOT NULL,
	PRIMARY KEY (id_papel_usuario),
	UNIQUE KEY uni_username_role (papel, usuario),
	KEY fk_username_idx (usuario),
	CONSTRAINT fk_username FOREIGN KEY (usuario) REFERENCES usuarios (usuario)
);

