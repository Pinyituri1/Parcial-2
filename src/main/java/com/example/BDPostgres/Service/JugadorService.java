package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.Jugador;
import com.example.BDPostgres.Repository.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    public List<Jugador> findAll() {
        return jugadorRepository.findAll();
    }

    public Optional<Jugador> findById(Integer id) {
        return jugadorRepository.findById(id);
    }

    public Jugador save(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public void deleteById(Integer id) {
        jugadorRepository.deleteById(id);
    }

    public List<Jugador> obtenerJugadoresPorEquipo(Integer equipoId) {
        return jugadorRepository.BuscarEquiposPorId(equipoId);
    }

    public List<Jugador> obtenerJugadoresConMasDeXGoles(Integer goles) {
        return jugadorRepository.BuscarJugadoresConMasXGoles(goles);
    }
}
