package com.AluraChallenge.Foro.Controlador;

import com.AluraChallenge.Foro.DTO.Curso.CursoDatos;
import com.AluraChallenge.Foro.DTO.Topico.*;
import com.AluraChallenge.Foro.DTO.Usuarios.UsuarioRespuesta;
import com.AluraChallenge.Foro.Entidades.Curso;
import com.AluraChallenge.Foro.Entidades.Respuesta;
import com.AluraChallenge.Foro.Entidades.Topics;
import com.AluraChallenge.Foro.Entidades.Usuario;
import com.AluraChallenge.Foro.Servicio.CursoServicio;
import com.AluraChallenge.Foro.Servicio.RespuestaService;
import com.AluraChallenge.Foro.Servicio.TopicServicio;
import com.AluraChallenge.Foro.Servicio.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Tag(name="3. Topicos")
@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicServicio topicServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private CursoServicio cursoServicio;
    @Autowired
    private RespuestaService respuestaService;

    @Operation(
            summary = "lista de temas ",
            description = "los valores por defecto muestras 6 temas por pagina")
    @GetMapping
    public ResponseEntity<List<ListarTopicos>> listarTopic(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "6") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Topics> topicsPage = topicServicio.listaTopics(pageable);

        List<Topics> topics = topicsPage.getContent();
        List<ListarTopicos> topicoDTO = new ArrayList<>();
        for (Topics topico:topics){
            topicoDTO.add(new ListarTopicos(topico.getId(),topico.getTitulo(),
                    topico.getMensaje(),topico.getFechCreacion(),topico.getStatus(),
                    new UsuarioRespuesta(topico.getAutor().getNombre(),topico.getAutor().getEmail()),
                    new CursoDatos(topico.getCurso().getNombre(), topico.getCurso().getCategoria())));
        }
        return ResponseEntity.ok(topicoDTO);
    }

    @Operation(
            summary = "Obtener temas por ID ",
            description = "obtener tema por ID")
    @GetMapping("/{id}")
    public ResponseEntity<DatosRegistrarTopico> obtenertopic(@PathVariable Integer id){
        Topics topic = topicServicio.obtenerTopicPorId(id);
        DatosRegistrarTopico datosTopico =new DatosRegistrarTopico(topic.getTitulo(), topic.getMensaje(),
                topic.getStatus(),
                new UsuarioRespuesta(topic.getAutor().getNombre(),topic.getAutor().getEmail()),
                new CursoDatos(topic.getCurso().getNombre(),topic.getCurso().getCategoria()));
        return ResponseEntity.ok(datosTopico);
    }

    @Operation(
            summary = "Registrar tema ",
            description = "ingres el ID del curso y el ID del usuario")

    @PostMapping("/usuario/{usuarioId}/curso/{cursoId}")
    public ResponseEntity<DatosTopicoRespuesta> registrotopic(@RequestBody DatosUpdate datosUpdate,
                                                              @PathVariable Integer usuarioId,
                                                              @PathVariable Integer cursoId){
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(usuarioId);
        Curso curso = cursoServicio.obtenerCursoPorID(cursoId);
        Topics topicExistente = new Topics();
        topicExistente.setAutor(usuario);
        topicExistente.setCurso(curso);
        topicExistente.setTitulo(datosUpdate.titulo());
        topicExistente.setMensaje(datosUpdate.mensaje());
        topicServicio.guardarTopic(topicExistente);
        DatosTopicoRespuesta topicoRespuesta =  new DatosTopicoRespuesta(topicExistente.getTitulo(),
                topicExistente.getMensaje(),topicExistente.getFechCreacion(),topicExistente.getStatus(),
                new UsuarioRespuesta(usuario.getNombre(), usuario.getEmail()),
                new CursoDatos(curso.getNombre(), curso.getNombre()));
        return ResponseEntity.ok(topicoRespuesta);
    }

    @Operation(
            summary = "Actualizar tema ",
            description = "ingresar el ID del tema a actualizar")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosTopicoRespuesta> actualizarTopic(@PathVariable Integer id, @RequestBody DatosActualizarTopico datosActualizarTopico) {
        Topics topicExistente = topicServicio.obtenerTopicPorId(id);
        if (topicExistente == null) {
            return ResponseEntity.notFound().build();
        }
        topicExistente.setId(id);
        topicExistente.setTitulo(datosActualizarTopico.titulo());
        topicExistente.setMensaje(datosActualizarTopico.mensaje());
        topicServicio.actualizarTopics(topicExistente);
        DatosTopicoRespuesta topicoRespuesta =  new DatosTopicoRespuesta(topicExistente.getTitulo(),
                topicExistente.getMensaje(), topicExistente.getFechCreacion(),topicExistente.getStatus(),
                new UsuarioRespuesta(topicExistente.getAutor().getNombre(), topicExistente.getAutor().getEmail()),
                new CursoDatos(topicExistente.getCurso().getNombre(),topicExistente.getCurso().getCategoria()));
        return ResponseEntity.ok(topicoRespuesta);
    }

    @Operation(
            summary = "Borrar temas por ID",
            description = "ingresa el ID del tema a eliminar")
    @DeleteMapping("/{id}")
    public ResponseEntity borrarTopic(@PathVariable Integer id){
        topicServicio.eliminarTopic(id);
        return ResponseEntity.ok("Topic Borrado con exito");
    }



}
