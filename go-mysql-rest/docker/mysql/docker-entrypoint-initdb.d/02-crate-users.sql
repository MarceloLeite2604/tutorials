CREATE USER IF NOT EXISTS 'go_mysql_user' IDENTIFIED BY 'thisismypassword';
GRANT ALL ON go_mysql_example_db.* TO 'go_mysql_user';
