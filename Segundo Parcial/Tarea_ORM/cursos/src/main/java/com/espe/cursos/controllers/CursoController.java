package com.espe.cursos.controllers;

import com.espe.cursos.models.entities.Curso;
import com.espe.cursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    // Crear un nuevo curso
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(curso));
    }

    // Obtener todos los cursos
    @GetMapping
    public ResponseEntity<List<Curso>> obtenerTodos() {
        List<Curso> cursos = service.findAll();
        return ResponseEntity.ok(cursos);
    }

    // Obtener un curso por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerPorId(@PathVariable Long id) {
        Optional<Curso> curso = service.findById(id);
        return curso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    // Eliminar un curso por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Curso> curso = service.findById(id);
        if (curso.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado");
        }
    }
}
