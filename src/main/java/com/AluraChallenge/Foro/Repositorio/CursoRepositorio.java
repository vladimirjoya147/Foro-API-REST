package com.AluraChallenge.Foro.Repositorio;

import com.AluraChallenge.Foro.Entidades.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso,Integer> {
}
