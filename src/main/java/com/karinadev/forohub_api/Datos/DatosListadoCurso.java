package com.karinadev.forohub_api.Datos;

import com.karinadev.forohub_api.Modelos.Curso;

public record DatosListadoCurso(Long id, String nombre, String descripcion, Boolean activo) {

    public DatosListadoCurso(Curso curso){
        this(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getActivo());
    }
}
