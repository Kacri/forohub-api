package com.karinadev.forohub_api.Modelos;

import com.karinadev.forohub_api.Datos.DatosActualizadoTopico;
import com.karinadev.forohub_api.Datos.DatosRegistroTopico;
import com.karinadev.forohub_api.Exeption.ValidacionException;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String mensaje;
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "curso_id",nullable = false)
    private Curso curso;

    @Enumerated(EnumType.STRING)
    private EstadoTopico estadoTopico;

    public Topico(){

    }

    public Topico( DatosRegistroTopico datosRegistroTopico,Usuario usuario,Curso curso) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();

        if (usuario != null){
            this.usuario = usuario;
        }else{
            throw new ValidacionException("El id usuario no puede ser nullo");
        }
        if (curso != null){
            this.curso = curso;
        }else{
            throw new ValidacionException("El id curso no puede ser nullo");
        }

        this.fecha = datosRegistroTopico.fecha() != null ? datosRegistroTopico.fecha(): LocalDateTime.now();
        this.estadoTopico = EstadoTopico.PENDIENTE;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public EstadoTopico getEstadoTopico() {
        return estadoTopico;
    }

    public void setEstadoTopico(EstadoTopico estadoTopico) {
        this.estadoTopico = estadoTopico;
    }

    public void actualizarTopico(DatosActualizadoTopico datosActualizadoTopico) {
        if (datosActualizadoTopico.titulo() != null){
            this.titulo = datosActualizadoTopico.titulo();
        }
        if (datosActualizadoTopico.mensaje() != null){
            this.mensaje = datosActualizadoTopico.mensaje();
        }
    }

    public void desactivarTopico(){
        this.estadoTopico = EstadoTopico.ELIMINADO;
    }

    public boolean validarEstado( EstadoTopico estadoesperado){
       return this.estadoTopico == estadoesperado;
    }

}
