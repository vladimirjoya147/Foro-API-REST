package com.AluraChallenge.Foro.Servicio;

import com.AluraChallenge.Foro.Entidades.Curso;
import com.AluraChallenge.Foro.Repositorio.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoServicioImpl implements CursoServicio{

    @Autowired
    CursoRepositorio cursoRepositorio;

    @Override
    public Curso guardarCurso(Curso curso) {
        return cursoRepositorio.save(curso);
    }

    @Override
    public Curso obtenerCursoPorID(Integer id) {
        return cursoRepositorio.findById(id).get();
    }

    @Override
    public void eliminarCurso(Integer id) {
        cursoRepositorio.deleteById(id);
    }

    @Override
    public Curso actualizarCurso(Curso curso) {
        return cursoRepositorio.save(curso);
    }


}
