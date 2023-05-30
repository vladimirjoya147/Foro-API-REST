package com.AluraChallenge.Foro.Servicio;

import com.AluraChallenge.Foro.DTO.Curso.CursoDatos;
import com.AluraChallenge.Foro.Entidades.Curso;

public interface CursoServicio {

    public Curso guardarCurso(Curso curso);

    public Curso obtenerCursoPorID(Integer id);

    public void eliminarCurso(Integer id);

    public Curso actualizarCurso(Curso curso);

}
