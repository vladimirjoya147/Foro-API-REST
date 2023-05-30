package com.AluraChallenge.Foro.DTO.Topico;

import com.AluraChallenge.Foro.DTO.Curso.CursoDatos;
import com.AluraChallenge.Foro.DTO.Usuarios.UsuarioRespuesta;
import com.AluraChallenge.Foro.Entidades.Curso;
import com.AluraChallenge.Foro.Entidades.Statustopico;
import com.AluraChallenge.Foro.Entidades.Usuario;

public record DatosActualizarTopico(
        String titulo,
        String mensaje


) {
}
