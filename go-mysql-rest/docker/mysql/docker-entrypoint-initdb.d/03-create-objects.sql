USE go_mysql_example_db;

CREATE TABLE users
(
	id VARCHAR(36) NOT NULL PRIMARY KEY,
	first_name VARCHAR(256) NOT NULL,
	last_name VARCHAR(256) NOT NULL,
	username VARCHAR(32) NOT NULL
) ENGINE=InnoDB;
