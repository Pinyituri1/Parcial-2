package com.example.BDPostgres.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_entrenador;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String especialidad;

    @Column(name = "id_equipo", insertable = false, updatable = false)
    private Integer id_equipo;

    @ManyToOne
    @JoinColumn(name = "id_equipo", nullable = false)
    @JsonIgnore
    private Equipo equipo;
}

