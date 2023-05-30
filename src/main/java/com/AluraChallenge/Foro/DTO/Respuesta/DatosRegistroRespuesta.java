package com.AluraChallenge.Foro.DTO.Respuesta;

import com.AluraChallenge.Foro.Entidades.Topics;
import com.AluraChallenge.Foro.Entidades.Usuario;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(@NotNull
                                      String mensaje/*,
                                     @NotNull
                                     Topics topico,
                                     @NotNull
                                     Usuario autor*/
) {
}
