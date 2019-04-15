DROP TRIGGER atraso_escrita_usuarios;
CREATE TRIGGER atraso_escrita_usuarios
BEFORE INSERT ON usuarios
BEGIN
    dbms_lock.sleep(0.01);
END;
/