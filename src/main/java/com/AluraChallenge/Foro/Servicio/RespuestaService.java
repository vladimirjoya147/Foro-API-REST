package com.AluraChallenge.Foro.Servicio;

import com.AluraChallenge.Foro.Entidades.Respuesta;

public interface RespuestaService {
    public Respuesta guardarRespuesta(Respuesta respuesta);
    public void eliminarRespuesta(Integer id);
    public Respuesta actualizarRespuesta(Respuesta respuesta);
}
