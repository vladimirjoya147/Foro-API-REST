package com.AluraChallenge.Foro.Controlador;

import com.AluraChallenge.Foro.DTO.Curso.CursoDatos;
import com.AluraChallenge.Foro.DTO.Curso.CursoDatosactualizar;
import com.AluraChallenge.Foro.DTO.Curso.Cursoobtenerdatos;
import com.AluraChallenge.Foro.DTO.Usuarios.DatosUsuarioActualizar;
import com.AluraChallenge.Foro.Entidades.Curso;
import com.AluraChallenge.Foro.Repositorio.CursoRepositorio;
import com.AluraChallenge.Foro.Servicio.CursoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name="2. Cursos")
@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    CursoServicio cursoServicio;
    @Autowired
    CursoRepositorio cursoRepositorio;

    @Operation(
            summary = "Registrar un curso",
            description = "debes estar logueado para registrar un curso")
    @PostMapping
    public ResponseEntity<CursoDatos> registrarCurso(@RequestBody CursoDatos cursoDatos){
        Curso cursodate = cursoServicio.guardarCurso(new Curso(cursoDatos));
        return ResponseEntity.ok(cursoDatos);
    }
    @Operation(
            summary = "Obtener curso por ID",
            description = "debes estar logueado para buscar un curso")
    @GetMapping("/{id}")
    public ResponseEntity<Cursoobtenerdatos> obtenerCursosPorId(@PathVariable Integer id){
        Curso curso = cursoServicio.obtenerCursoPorID(id);
        Cursoobtenerdatos obtenerdatos = new Cursoobtenerdatos(curso.getId(),curso.getNombre(), curso.getCategoria());
        return ResponseEntity.ok(obtenerdatos);
    }

    @Operation(
            summary = "Eliminar un curso",
            description = "Debes que estar logueado para eliminar un curso",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Eliminar Curso",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(implementation = String.class)
                            )
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> elliminarCurso (@PathVariable Integer id){
        cursoServicio.eliminarCurso(id);
        String mensaje = "Usuario eliminado con éxito";
        return ResponseEntity.ok(mensaje);
    }

    @Operation(
            summary = "Actualizar datos de un curso",
            description = "debes estar logueado para modificar datos de un curso")
    @PatchMapping("/{cursoId}")
    @Transactional
    public ResponseEntity<CursoDatosactualizar> actualizarCurso(@PathVariable Integer cursoId,
                                                  @RequestBody CursoDatos curso){
        Curso cursoexistente = cursoServicio.obtenerCursoPorID(cursoId);
        cursoexistente.setId(cursoId);
        cursoexistente.setNombre(curso.nombre());
        cursoexistente.setCategoria(curso.categoria());
        cursoServicio.actualizarCurso(cursoexistente);
        CursoDatosactualizar datosactualizar = new CursoDatosactualizar(cursoexistente.getId(),
                cursoexistente.getNombre(),cursoexistente.getCategoria());
        return ResponseEntity.ok(datosactualizar);
    }
}
