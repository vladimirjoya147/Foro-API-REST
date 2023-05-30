package com.AluraChallenge.Foro.Servicio;

import com.AluraChallenge.Foro.Entidades.Respuesta;
import com.AluraChallenge.Foro.Repositorio.RespuestaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaImpl implements RespuestaService{

    @Autowired
    private RespuestaRepositorio respuestaRepositorio;

    @Override
    public Respuesta guardarRespuesta(Respuesta respuesta) {
        return respuestaRepositorio.save(respuesta);
    }

    @Override
    public void eliminarRespuesta(Integer id) {
        respuestaRepositorio.deleteById(id);
    }

    @Override
    public Respuesta actualizarRespuesta(Respuesta respuesta) {
        return respuestaRepositorio.save(respuesta);
    }
}
