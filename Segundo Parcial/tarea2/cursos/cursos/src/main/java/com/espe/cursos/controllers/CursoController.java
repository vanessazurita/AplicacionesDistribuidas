package com.espe.cursos.controllers;

import com.espe.cursos.models.Curso;
import com.espe.cursos.models.CursoUsuario;
import com.espe.cursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> listar() {
        return cursoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtener(@PathVariable Long id) {
        return cursoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(validarErrores(result));
        }
        return ResponseEntity.ok(cursoService.save(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCurso(@PathVariable Long id, @Valid @RequestBody Curso cursoActualizado, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(validarErrores(result));
        }
        Optional<Curso> cursoOptional = cursoService.findById(id);
        if (cursoOptional.isPresent()) {
            Curso cursoExistente = cursoOptional.get();
            cursoExistente.setNombre(cursoActualizado.getNombre());
            cursoExistente.setDescripcion(cursoActualizado.getDescripcion());
            cursoExistente.setCreditos(cursoActualizado.getCreditos());
            Curso cursoGuardado = cursoService.save(cursoExistente);
            return ResponseEntity.ok(cursoGuardado);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/asignar-usuario/{cursoId}")
    public ResponseEntity<?> asignarUsuario(@PathVariable Long cursoId, @RequestBody Long usuarioId) {
        Optional<CursoUsuario> cursoUsuarioOptional = cursoService.asignarUsuario(cursoId, usuarioId);
        if (cursoUsuarioOptional.isPresent()) {
            return ResponseEntity.ok(cursoUsuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-usuario/{cursoId}/usuario/{usuarioId}")
    public ResponseEntity<Void> eliminarUsuarioDeCurso(@PathVariable Long cursoId, @PathVariable Long usuarioId) {
        cursoService.eliminarUsuarioDeCurso(cursoId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cursoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Método para validar errores
    private Map<String, String> validarErrores(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage()));
        return errores;
    }
}
