package com.example.BDPostgres.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_equipo;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private LocalDate fundacion;

    @OneToMany(mappedBy = "equipo")
    @JsonIgnore
    private List<Jugador> jugadores;

    @OneToMany(mappedBy = "equipo")
    @JsonIgnore
    private List<Entrenador> entrenadores;

    @OneToMany(mappedBy = "equipoLocal")
    @JsonIgnore
    private List<Partido> partidosLocal;

    @OneToMany(mappedBy = "equipoVisita")
    @JsonIgnore
    private List<Partido> partidosVisita;
}
