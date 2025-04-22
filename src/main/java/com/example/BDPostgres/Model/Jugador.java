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
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_jugador;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String posicion;

    @Column(nullable = false)
    private Integer dorsal;

    @Column(name = "fecha_nac", nullable = false)
    private LocalDate fecha_nac;

    @Column(length = 100, nullable = false)
    private String nacionalidad;

    @Column(name = "id_equipo", insertable = false, updatable = false)
    private Integer id_equipo;

    @ManyToOne
    @JoinColumn(name = "id_equipo", nullable = false)
    @JsonIgnore
    private Equipo equipo;

    @OneToMany(mappedBy = "jugador")
    @JsonIgnore
    private List<EstadisticasJugador> estadisticas;
}
