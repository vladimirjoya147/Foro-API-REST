package com.AluraChallenge.Foro.Repositorio;

import com.AluraChallenge.Foro.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    UserDetails findBynombre(String nombre);
}
