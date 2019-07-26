CREATE USER IF NOT EXISTS 'modelmapper'@'%' IDENTIFIED BY 'modelmapper';
GRANT ALL ON modelmapper_db.* TO 'modelmapper'@'%';
GRANT ALL ON liquibase_db.* TO 'modelmapper'@'%';
