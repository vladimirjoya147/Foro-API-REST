package com.AluraChallenge.Foro.Controlador;

import com.AluraChallenge.Foro.DTO.Respuesta.DatosRegistroRespuesta;
import com.AluraChallenge.Foro.Entidades.Respuesta;
import com.AluraChallenge.Foro.Entidades.Topics;
import com.AluraChallenge.Foro.Entidades.Usuario;
import com.AluraChallenge.Foro.Servicio.RespuestaService;
import com.AluraChallenge.Foro.Servicio.TopicServicio;
import com.AluraChallenge.Foro.Servicio.UsuarioServicio;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name="4. Respuesta")
@RestController
@RequestMapping
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @Autowired
    private TopicServicio topicsService;

    @Autowired
    private UsuarioServicio usuarioService;

    @PostMapping("/respuesta/{topicoId}/{usuarioId}")
    public ResponseEntity<?> crearRespuesta(
            @PathVariable Integer topicoId,
            @PathVariable Integer usuarioId,
            @RequestBody DatosRegistroRespuesta datosRegistroRespuesta) {
        Topics topico = topicsService.obtenerTopicPorId(topicoId);
        Usuario autor = usuarioService.obtenerUsuarioPorId(usuarioId);

        if (topico == null || autor == null) {
            return ResponseEntity.badRequest().body("No se encontró el tópico o el usuario correspondiente.");
        }
        Respuesta nuevaRespuesta = new Respuesta();
        nuevaRespuesta.setTopico(topico);
        nuevaRespuesta.setAutor(autor);
        nuevaRespuesta.setMensaje(datosRegistroRespuesta.mensaje());
        respuestaService.guardarRespuesta(nuevaRespuesta);
        topico.getRespuestas().add(nuevaRespuesta);
        topicsService.guardarTopic(topico);
        return ResponseEntity.ok("Respuesta registrada con exito");
    }

    @DeleteMapping("/respuesta/id")
    public ResponseEntity<String> eliminarRespuesta(@PathVariable Integer id){
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.ok("Respuesta eliminado con exito");
    }




}
