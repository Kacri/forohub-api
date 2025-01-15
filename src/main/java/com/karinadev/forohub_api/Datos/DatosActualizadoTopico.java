package com.karinadev.forohub_api.Datos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizadoTopico(@NotNull Long id,String titulo,String mensaje) {

}
