create database friends; -- Create the new database
create user 'springuser'@'localhost' identified by 'kawapasu';
grant all on friends.* to 'springuser'@'localhost';
