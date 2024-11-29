package com.francesco.microservices.app.cursos.models.repository;

import com.francesco.microservices.app.cursos.models.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositorioCurso extends CrudRepository<Curso, Long> {

    List<Curso> findFirst5ByOrderByValoracionDesc();
    List<Curso> findFirst5ByOrderByFechaCreacionDesc();
}
