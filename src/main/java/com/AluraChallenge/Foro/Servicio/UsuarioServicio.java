package com.AluraChallenge.Foro.Servicio;

import com.AluraChallenge.Foro.Entidades.Usuario;

public interface UsuarioServicio {

    public Usuario guardarUsuario(Usuario usuario);

    public Usuario obtenerUsuarioPorId( Integer id);

    public Usuario actualizarUsuario(Usuario usuario);


}
