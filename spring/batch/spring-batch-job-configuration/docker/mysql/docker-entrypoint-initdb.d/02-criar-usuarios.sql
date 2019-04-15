CREATE USER IF NOT EXISTS 'programa' IDENTIFIED BY 'programa';
GRANT ALL ON programa_db.* TO 'programa';
CREATE USER IF NOT EXISTS 'spring_batch' IDENTIFIED BY 'spring_batch';
GRANT ALL ON spring_batch_db.* TO 'spring_batch';