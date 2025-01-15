package com.karinadev.forohub_api.Datos;

import com.karinadev.forohub_api.Modelos.Curso;
import com.karinadev.forohub_api.Modelos.EstadoTopico;
import com.karinadev.forohub_api.Modelos.Topico;
import com.karinadev.forohub_api.Modelos.Usuario;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Long idUsuario,
        String nombreUsuario,
        Long idCurso,
        String nombreCurso,
        EstadoTopico estadoTopico
        ) {
        public DatosListadoTopico(Topico topico){
                this(topico.getId(), topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFecha(),
                        topico.getUsuario().getId(),
                        topico.getUsuario().getUsername(),
                        topico.getCurso().getId(),
                        topico.getCurso().getNombre(),
                        topico.getEstadoTopico());
        }


}
