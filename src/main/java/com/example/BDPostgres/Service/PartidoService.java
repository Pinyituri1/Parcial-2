package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.Partido;
import com.example.BDPostgres.Repository.PartidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartidoService {

    private final PartidoRepository partidoRepository;

    public List<Partido> findAll() {
        return partidoRepository.findAll();
    }

    public Optional<Partido> findById(Integer id) {
        return partidoRepository.findById(id);
    }

    public Partido save(Partido partido) {
        return partidoRepository.save(partido);
    }

    public void deleteById(Integer id) {
        partidoRepository.deleteById(id);
    }

    public int obtenerGolesPorEquipo(Integer equipoId) {
        return partidoRepository.getGolesPorEquipo(equipoId);
    }

    public List<Object[]> obtenerResultadosPartidosConEquipos() {
        return partidoRepository.getResultadosPartidosConEquipos();
    }
}
