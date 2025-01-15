package com.karinadev.forohub_api.Repository;

import com.karinadev.forohub_api.Modelos.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    Page<Topico> findAll(Pageable paginacion);

    @Query("""
            SELECT t FROM Topico t ORDER BY t.fecha ASC
            """)
    Page<Topico> listarFechaAsc(Pageable paginacion);

    @Query("""
            SELECT t
             FROM Topico t
             JOIN t.curso e
             WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombrecurso, '%'))
            """)
//    List<Topico> obtenerPorNombreCurso(String nombrecurso);
//    //List<Topico> obtenerPorNombreCurso (String nombreCurso);

    Page<Topico> obtenerPorNombreCurso(@Param("nombrecurso")String nombrecurso,Pageable paginacion);

}
