CREATE TABLE cursos(
id bigint NOT NULL auto_increment PRIMARY KEY,
nombre varchar(255) NOT NULL,
descripcion varchar(255) NOT NULL UNIQUE,
activo TINYINT NOT NULL

)