package com.example.BDPostgres.Repository;

import com.example.BDPostgres.Model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {

    @Query(value = "SELECT * FROM jugador WHERE equipo_id = :equipoId", nativeQuery = true)
    List<Jugador> BuscarEquiposPorId(@Param("equipoId") Integer equipoId);

    @Query(value = "SELECT * FROM jugador WHERE goles > :goles", nativeQuery = true)
    List<Jugador> BuscarJugadoresConMasXGoles(@Param("goles") Integer goles);
}
