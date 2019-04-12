INSERT INTO programa_db.usuarios
(
	usuario,
	senha,
	habilitado
)
VALUES 
(
	'marcelo',
	'{bcrypt}$2a$10$gbYTQMs3gQ/oIyv4JS127.IqHofg1QPsz.lamKN60NYJiLFpJca.a', 
	true
);

INSERT INTO programa_db.usuarios
(
	usuario,
	senha,
	habilitado
)
VALUES
(
	'alice',
	'{bcrypt}$2a$10$gbYTQMs3gQ/oIyv4JS127.IqHofg1QPsz.lamKN60NYJiLFpJca.a',
	true
);

INSERT INTO programa_db.papeis_usuarios
(
	usuario,
	papel
)
VALUES
(
	'marcelo',
	'ROLE_USER'
);

INSERT INTO programa_db.papeis_usuarios
(
	usuario,
	papel
)
VALUES
(
	'marcelo',
	'ROLE_ADMIN'
);

INSERT INTO programa_db.papeis_usuarios
(
	usuario,
	papel
)
VALUES
(
	'alice',
	'ROLE_USER'
);
