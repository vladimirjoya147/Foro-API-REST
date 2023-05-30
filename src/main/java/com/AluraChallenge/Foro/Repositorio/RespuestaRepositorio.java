package com.AluraChallenge.Foro.Repositorio;

import com.AluraChallenge.Foro.Entidades.Respuesta;
import org.hibernate.mapping.BasicValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepositorio extends JpaRepository<Respuesta, Integer> {
}
