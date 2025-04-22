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
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_partido;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(length = 100, nullable = false)
    private String estadio;

    @Column(name = "equipo_local", insertable = false, updatable = false)
    private Integer equipo_local;

    @Column(name = "equipo_visita", insertable = false, updatable = false)
    private Integer equipo_visita;

    @ManyToOne
    @JoinColumn(name = "equipo_local", nullable = false)
    @JsonIgnore
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "equipo_visita", nullable = false)
    @JsonIgnore
    private Equipo equipoVisita;

    @Column(nullable = false)
    private Integer goles_local;

    @Column(nullable = false)
    private Integer goles_visita;

    @OneToMany(mappedBy = "partido")
    @JsonIgnore
    private List<EstadisticasJugador> estadisticas;
}
