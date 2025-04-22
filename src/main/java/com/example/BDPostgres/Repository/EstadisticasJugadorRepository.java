package com.example.BDPostgres.Repository;

import com.example.BDPostgres.Model.EstadisticasJugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadisticasJugadorRepository extends JpaRepository<EstadisticasJugador, Integer> {
}
