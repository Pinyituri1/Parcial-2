package com.example.BDPostgres.Repository;

import com.example.BDPostgres.Model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {

    @Query(value = "SELECT SUM(p.goles_local + p.goles_visita) FROM partido p WHERE p.equipo_local = :equipoId OR p.equipo_visita = :equipoId", nativeQuery = true)
    int getGolesPorEquipo(@Param("equipoId") Integer equipoId);

    @Query(value = "SELECT p.fecha, e1.nombre AS equipo_local, e2.nombre AS equipo_visita, p.goles_local, p.goles_visita " +
            "FROM partido p " +
            "JOIN equipo e1 ON p.equipo_local = e1.id " +
            "JOIN equipo e2 ON p.equipo_visita = e2.id", nativeQuery = true)
            List<Object[]> getResultadosPartidosConEquipos();
}
