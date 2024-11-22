package com.francesco.microservices.app.cursos.models.repository;

import com.francesco.microservices.app.cursos.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioCurso extends CrudRepository<Curso, Long> {

}
