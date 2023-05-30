package com.AluraChallenge.Foro.DTO.Topico;

import com.AluraChallenge.Foro.DTO.Curso.CursoDatos;
import com.AluraChallenge.Foro.DTO.Usuarios.UsuarioRespuesta;
import com.AluraChallenge.Foro.Entidades.Curso;
import com.AluraChallenge.Foro.Entidades.Statustopico;
import com.AluraChallenge.Foro.Entidades.Usuario;

import java.time.LocalDateTime;

public record DatosRegistrarTopico(
        String titulo,
        String mensaje,
        Statustopico estatus,
        UsuarioRespuesta autor,
        CursoDatos curso
) {

}