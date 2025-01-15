package com.karinadev.forohub_api.Datos;

import com.karinadev.forohub_api.Modelos.EstadoTopico;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(@NotNull String titulo,
                                  @NotNull String mensaje,
                                  LocalDateTime fecha,
                                  @NotNull Long idUsuario,
                                  @NotNull Long idCurso,
                                  EstadoTopico estadoTopico
                                  ) {
}
