package com.example.BDPostgres.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadisticasJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_estadistica;

    @Column(name = "id_jugador", insertable = false, updatable = false)
    private Integer id_jugador;

    @Column(name = "id_partido", insertable = false, updatable = false)
    private Integer id_partido;

    @ManyToOne
    @JoinColumn(name = "id_jugador", nullable = false)
    @JsonIgnore
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "id_partido", nullable = false)
    @JsonIgnore
    private Partido partido;

    @Column(nullable = false)
    private Integer minutos_jugados;

    @Column(nullable = false)
    private Integer goles;

    @Column(nullable = false)
    private Integer asistencias;

    @Column(nullable = false)
    private Integer tarjetas_amarillas;

    @Column(nullable = false)
    private Integer tarjetas_rojas;
}
