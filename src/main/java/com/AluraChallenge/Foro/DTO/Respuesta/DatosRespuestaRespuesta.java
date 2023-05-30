package com.AluraChallenge.Foro.DTO.Respuesta;

import com.AluraChallenge.Foro.Entidades.Topics;
import com.AluraChallenge.Foro.Entidades.Usuario;

import java.time.LocalDateTime;

public record DatosRespuestaRespuesta(
        Integer id,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean solucion,
        Topics topico,
        Usuario autor
) {
}
