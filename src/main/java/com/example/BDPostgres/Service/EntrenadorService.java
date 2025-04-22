package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.Entrenador;
import com.example.BDPostgres.Repository.EntrenadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public List<Entrenador> findAll() {
        return entrenadorRepository.findAll();
    }

    public Optional<Entrenador> findById(Integer id) {
        return entrenadorRepository.findById(id);
    }

    public Entrenador save(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public void deleteById(Integer id) {
        entrenadorRepository.deleteById(id);
    }
}
