CREATE DATABASE IF NOT EXISTS meetings;
CREATE USER 'meetings_user'@'localhost' IDENTIFIED BY 'meetings_pass';
GRANT ALL ON meetings.* to 'meetings_user'@'localhost';
