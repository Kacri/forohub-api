package com.karinadev.forohub_api.Datos;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso (@NotBlank String nombre,
                                 @NotBlank String descripcion){
}
