CREATE TABLE usuario_cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    comentario VARCHAR(255),
    fecha_asignacion DATE NOT NULL,
    completado BOOLEAN NOT NULL DEFAULT FALSE,
    usuario_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,

    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    CONSTRAINT fk_curso FOREIGN KEY (curso_id) REFERENCES cursos(id) ON DELETE CASCADE
);