CREATE USER IF NOT EXISTS 'programa' IDENTIFIED BY 'programa';
GRANT ALL ON programa_db.* TO 'programa';
GRANT ALL ON liquibase_db.* TO 'programa';