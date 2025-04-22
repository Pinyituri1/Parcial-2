package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.EstadisticasJugador;
import com.example.BDPostgres.Repository.EstadisticasJugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadisticasJugadorService {

    private final EstadisticasJugadorRepository estadisticaJugadorRepository;

    public List<EstadisticasJugador> findAll() {
        return estadisticaJugadorRepository.findAll();
    }

    public Optional<EstadisticasJugador> findById(Integer id) {
        return estadisticaJugadorRepository.findById(id);
    }

    public EstadisticasJugador save(EstadisticasJugador estadistica) {
        return estadisticaJugadorRepository.save(estadistica);
    }

    public void deleteById(Integer id) {
        estadisticaJugadorRepository.deleteById(id);
    }
}
