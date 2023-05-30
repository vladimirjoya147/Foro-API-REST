package com.AluraChallenge.Foro.Entidades;

import com.AluraChallenge.Foro.DTO.Curso.CursoDatos;
import com.AluraChallenge.Foro.DTO.Curso.CursoDatosactualizar;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id")
    private Integer id;

    private String nombre;
    private String categoria;

    public Curso(Integer id){
        this.id=id;
    }

    public Curso(CursoDatos cursoDatos) {
        this.nombre= cursoDatos.nombre();
        this.categoria=cursoDatos.categoria();
    }

    public void actualizarCurso(CursoDatosactualizar cursoDatosactualizar){
        if (cursoDatosactualizar.nombre() != null) {
            this.nombre = cursoDatosactualizar.nombre();
        }
        if (cursoDatosactualizar.categoria()!=null){
            this.categoria=cursoDatosactualizar.categoria();
        }
    }

}
