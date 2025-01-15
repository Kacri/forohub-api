package com.karinadev.forohub_api.Datos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRespuestaCurso(Long id,
                                  String nombre,
                                  String descripcion) {

}
