package com.karinadev.forohub_api.Controller;

import com.karinadev.forohub_api.Datos.DatosActualizadoTopico;
import com.karinadev.forohub_api.Datos.DatosListadoTopico;
import com.karinadev.forohub_api.Datos.DatosRegistroTopico;
import com.karinadev.forohub_api.Datos.DatosRespuestaTopico;
import com.karinadev.forohub_api.Exeption.ValidacionException;
import com.karinadev.forohub_api.Modelos.EstadoTopico;
import com.karinadev.forohub_api.Modelos.Topico;
import com.karinadev.forohub_api.Modelos.ValidResponse;
import com.karinadev.forohub_api.Repository.CursoRepository;
import com.karinadev.forohub_api.Repository.TopicoRepository;
import com.karinadev.forohub_api.Repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
//@SecurityRequirement(name = "bearer-key")

public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;




    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder){

        if (!cursoRepository.existsById(datosRegistroTopico.idCurso())){
            throw new ValidacionException("No existe curso con el id informado");
        }
        if (!usuarioRepository.existsById(datosRegistroTopico.idUsuario())){
            throw  new ValidacionException("No existe usuario con el id infomado");
        }

        var usuarios = usuarioRepository.findById(datosRegistroTopico.idUsuario()).get();
        var cursos = cursoRepository.findById(datosRegistroTopico.idCurso()).get();

        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico,usuarios,cursos));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(),topico.getTitulo(),
                topico.getMensaje(),topico.getFecha(),topico.getUsuario().getUsername(),topico.getCurso().getNombre(),
                topico.getEstadoTopico()
                );
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }



    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopico(@RequestParam(required = false)
                                                                  @PageableDefault(size = 10 )Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.listarFechaAsc(paginacion).map(DatosListadoTopico::new));
    }

//    @GetMapping("/curso/{nombrecurso}")
//    public List<Topico> obtenerPorNombreCurso(@PathVariable String nombrecurso){
//        return topicoRepository.obtenerPorNombreCurso(nombrecurso);
//    }



    @GetMapping("/curso/{nombrecurso}")
    public ResponseEntity<Page<DatosListadoTopico>> obtenerPorNombreCurso(@RequestParam(required = false)
                                                                              @PageableDefault(size = 10 )Pageable paginacion,
                                                                              @PathVariable String nombrecurso) {
        if (nombrecurso == null || nombrecurso.trim().isEmpty()) {
            return ResponseEntity.badRequest().build(); // Maneja el caso de entrada vacía.
        }
        Page<Topico> topicos = topicoRepository.obtenerPorNombreCurso(nombrecurso, paginacion);
        Page<DatosListadoTopico> datosListadoTopico = topicos.map(DatosListadoTopico::new);
        return ResponseEntity.ok(datosListadoTopico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerDatosTopico(@PathVariable Long id) {
    Topico topico = topicoRepository.getReferenceById(id);
    var datosTopico = new DatosRespuestaTopico(topico.getId(),topico.getTitulo(),
            topico.getMensaje(),topico.getFecha(),topico.getUsuario().getUsername(),topico.getCurso().getNombre(),
            topico.getEstadoTopico());
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping()
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizadoTopico datosActualizadoTopico){
        Optional <Topico> topicoBuscado = topicoRepository.findById(datosActualizadoTopico.id());
        if (topicoBuscado.isPresent()){
            Topico topico = topicoBuscado.get();
            topico.actualizarTopico(datosActualizadoTopico);
            return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(),
                    topico.getTitulo(),topico.getMensaje(),topico.getFecha(),topico.getUsuario().getUsername(),
                    topico.getCurso().getNombre(),topico.getEstadoTopico()));
        }else {
            throw  new ValidacionException("El ID topico no existe");
        }
    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Optional <Topico> topicoBuscado = topicoRepository.findById(id);
        //Optional <Topico> topicoBuscado = topicoRepository.findById( datosActualizadoTopico.id());
       if (topicoBuscado.isPresent()){
            Topico topico = topicoBuscado.get();
            if (topico.validarEstado(EstadoTopico.ELIMINADO)){
                throw new ValidacionException("El Topico esta eliminado");
            }else{
                topico.desactivarTopico();
                return ResponseEntity.ok(new ValidResponse("Se elimino correctamente.."));
            }
        }else {
            throw  new ValidacionException("El ID topico no existe");
        }
    }



}
