package com.karinadev.forohub_api.Repository;

import com.karinadev.forohub_api.Modelos.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso,Long> {
    Page<Curso> findByActivoTrue(Pageable paginacion);
}
