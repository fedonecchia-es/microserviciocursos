package com.francesco.microservices.app.cursos.services;

import com.francesco.microservices.app.cursos.models.entity.Curso;
import com.francesco.microservices.app.cursos.models.repository.RepositorioCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ServicioImplCurso implements ServicioCurso {

    @Autowired
    private RepositorioCurso repositorioCurso;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Curso> findAll() {
        return repositorioCurso.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Curso> sortByValoracion() {
        return repositorioCurso.findFirst5ByOrderByValoracionDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Curso> sortByFecha() {
        return repositorioCurso.findFirst5ByOrderByFechaCreacionDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return repositorioCurso.findById(id);
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        return repositorioCurso.save(curso);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repositorioCurso.deleteById(id);
    }
}
