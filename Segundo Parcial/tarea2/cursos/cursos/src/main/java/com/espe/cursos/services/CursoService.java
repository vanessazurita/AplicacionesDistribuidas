package com.espe.cursos.services;

import com.espe.cursos.models.Curso;
import com.espe.cursos.models.CursoUsuario;
import com.espe.cursos.models.UsuarioDTO;
import com.espe.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void deleteById(Long id) {
        cursoRepository.deleteById(id);
    }

    public Optional<CursoUsuario> asignarUsuario(Long cursoId, Long usuarioId) {
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();

            // Validar si el usuario ya está matriculado
            boolean usuarioYaMatriculado = curso.getCursoUsuarios().stream()
                    .anyMatch(cursoUsuario -> cursoUsuario.getUsuarioId().equals(usuarioId));
            if (usuarioYaMatriculado) {
                throw new IllegalArgumentException("Este estudiante ya está matriculado");
            }

            try {
                // Llamada al microservicio de usuarios
                UsuarioDTO usuario = restTemplate.getForObject("http://localhost:8001/api/usuarios/" + usuarioId, UsuarioDTO.class);

                if (usuario == null) {
                    throw new IllegalArgumentException("No se encuentra el usuario solicitado o no existe");
                }

                CursoUsuario cursoUsuario = new CursoUsuario();
                cursoUsuario.setUsuarioId(usuario.getId());
                cursoUsuario.setUsuarioNombre(usuario.getNombre());
                cursoUsuario.setUsuarioApellido(usuario.getApellido());

                curso.getCursoUsuarios().add(cursoUsuario);
                cursoRepository.save(curso);

                return Optional.of(cursoUsuario);
            } catch (Exception e) {
                if (e.getMessage().contains("Connection refused")) {
                    throw new RuntimeException("Error en la base de datos");
                }
                throw new RuntimeException(e.getMessage());
            }
        }
        return Optional.empty();
    }

    public void eliminarUsuarioDeCurso(Long cursoId, Long usuarioId) {
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            curso.getCursoUsuarios().removeIf(cursoUsuario -> cursoUsuario.getUsuarioId().equals(usuarioId));
            cursoRepository.save(curso);
        }
    }
}
