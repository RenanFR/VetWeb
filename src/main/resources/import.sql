﻿CREATE DATABASE vetweb_database;

INSERT INTO perfil VALUES ('ROLE_usuario');
INSERT INTO perfil VALUES ('ROLE_admin');
INSERT INTO usuario VALUES ('VetWeb', 'VetWeb', '$2a$10$s.RdnBYSfM1X77Yn0kkutuj0hOH3hY7.ocwCv2ZsGOFWV3HegOljm');
INSERT INTO usuario VALUES ('admin', 'admin', '$2a$10$P/QiTaO3tKya8OT0ZXYH.uIVybUZ02cHoQdo7iyK4l0CxlcDhg8Gi');
INSERT INTO usuario_perfil VALUES ('admin', 'ROLE_admin');
INSERT INTO usuario_perfil VALUES ('VetWeb', 'ROLE_usuario');