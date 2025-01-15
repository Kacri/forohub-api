package com.karinadev.forohub_api.Datos;

import com.karinadev.forohub_api.Modelos.EstadoTopico;
import com.karinadev.forohub_api.Modelos.Topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(Long id,
                                   String titulo,
                                   String mensaje,
                                   LocalDateTime fecha,
                                   String nombreUsuario,
                                   String nombreCurso,
                                   EstadoTopico estadoTopico
                                   ) {
    public DatosRespuestaTopico (Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getUsuario().getUsername(),
                topico.getCurso().getNombre(),
                topico.getEstadoTopico());
    }

}
