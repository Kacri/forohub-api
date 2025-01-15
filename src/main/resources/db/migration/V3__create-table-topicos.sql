CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,              
    titulo VARCHAR(255) NOT NULL,                     
    mensaje TEXT NOT NULL,                             
    fecha DATETIME NOT NULL,                         
    usuario_id BIGINT NOT NULL,                       
    curso_id BIGINT NOT NULL,                          
    estado_topico VARCHAR(255) NOT NULL,               

    CONSTRAINT fk_topicos_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id) REFERENCES cursos(id) ON DELETE CASCADE
);