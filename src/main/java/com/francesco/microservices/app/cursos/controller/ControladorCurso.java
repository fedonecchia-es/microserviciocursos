package com.francesco.microservices.app.cursos.controller;

import com.francesco.microservices.app.cursos.models.entity.Curso;
import com.francesco.microservices.app.cursos.services.ServicioCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ControladorCurso {

    @Autowired //injects an instance of the repository interface
    private ServicioCurso servicioCurso;

    @GetMapping
    public ResponseEntity<?> devolverCursos() {
        //mete los cursos dentro de un body y lo devolve como un JSON
        return ResponseEntity.ok().body(servicioCurso.findAll());
    }

    @GetMapping("/valorados")
    public ResponseEntity<?> devolverCincoCursosMejoresValorados() {
        //mete los cursos dentro de un body y lo devolve como un JSON
        return ResponseEntity.ok().body(servicioCurso.sortByValoracion());
    }

    @GetMapping("/ultimos")
    public ResponseEntity<?> devolverCincoCursosMasRecientes() {
        //mete los cursos dentro de un body y lo devolve como un JSON
        return ResponseEntity.ok().body(servicioCurso.sortByFecha());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> devolverCurso(@PathVariable Long id) {
        Optional<Curso> cu = servicioCurso.findById(id);

        if (cu.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(cu.get());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarCurso(@PathVariable Long id) {
        servicioCurso.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> addCurso(@RequestBody Curso curso) {
        Curso cu = servicioCurso.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCurso(@RequestBody Curso curso, @PathVariable Long id) {
        Optional<Curso> cu = servicioCurso.findById(id);

        if (cu.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Curso ocurso = cu.get();
            ocurso.setTitulo(curso.getTitulo());
            ocurso.setDescripcion(curso.getDescripcion());
            ocurso.setUrl(curso.getUrl());
            ocurso.setThumbnail(curso.getThumbnail());
            ocurso.setValoracion(curso.getValoracion());
            Curso cursoCreado = servicioCurso.save(ocurso);
            return ResponseEntity.status(HttpStatus.OK).body(cursoCreado);
        }
    }

    @PutMapping("/{id}/valora/{voto}")// /valora
    public ResponseEntity<?> addVoto(@PathVariable Integer voto, @PathVariable Long id) {
        Optional<Curso> cu = servicioCurso.findById(id);
        if (cu.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Curso ocurso = cu.get();
            if (voto > 5) {
                return ResponseEntity.badRequest().build();
            } else {
                if (ocurso.getValoracion() == null) {
                    ocurso.setValoracion(voto.floatValue());
                } else {
                    ocurso.addVoto(voto);
                }
                Curso cursoCreado = servicioCurso.save(ocurso);
                return ResponseEntity.status(HttpStatus.OK).body(cursoCreado);
            }

        }
    }
}
