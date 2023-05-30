package com.AluraChallenge.Foro.Entidades;


import com.AluraChallenge.Foro.DTO.Topico.DatosRegistrarTopico;
import com.AluraChallenge.Foro.DTO.Topico.DatosTopicoRespuesta;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topico")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="topicos")
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="topics_id")
    private Integer id;

    @NotBlank
    @Column(name = "titulo")
    private String titulo;

    @NotBlank
    private String mensaje;

    @NotNull
    private LocalDateTime fechCreacion = LocalDateTime.now();

    @Enumerated
    @Column(name="estado")
    private Statustopico status = Statustopico.NO_RESPONDIDO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="curso_id")
    private Curso curso;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topics(String titulo, String mensaje, LocalDateTime fechCreacion, Statustopico status, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechCreacion = fechCreacion;
        this.status = status;
        this.autor = autor;
        this.curso = curso;

    }
}
