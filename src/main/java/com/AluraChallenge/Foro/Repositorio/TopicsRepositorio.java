package com.AluraChallenge.Foro.Repositorio;

import com.AluraChallenge.Foro.Entidades.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicsRepositorio extends JpaRepository<Topics, Integer> {
}
