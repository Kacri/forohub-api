package com.karinadev.forohub_api.Modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "usuario_cursos")
@Entity(name = "Usuario_Curso")
@Data

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario_Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comentario;
    private LocalDate fechaAsignacion;
    private boolean completado;
    @ManyToOne
    @JoinColumn(name="usuario_id",nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "curso_id",nullable = false)
    private Curso curso;


}
