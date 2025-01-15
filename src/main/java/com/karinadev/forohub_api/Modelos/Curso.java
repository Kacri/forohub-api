package com.karinadev.forohub_api.Modelos;

import com.karinadev.forohub_api.Datos.DatosRegistroCurso;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "cursos")
@Entity(name = "Curso")


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean activo;

    public Curso() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<Usuario_Curso> getUsuarioCursoList() {
        return usuarioCursoList;
    }

    public void setUsuarioCursoList(List<Usuario_Curso> usuarioCursoList) {
        this.usuarioCursoList = usuarioCursoList;
    }

    @OneToMany(mappedBy =  "curso",cascade = CascadeType.PERSIST,orphanRemoval = false)
    private List<Usuario_Curso> usuarioCursoList;

    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombre = datosRegistroCurso.nombre();
        this.descripcion = datosRegistroCurso.descripcion();
        this.activo = true;
    }
}
