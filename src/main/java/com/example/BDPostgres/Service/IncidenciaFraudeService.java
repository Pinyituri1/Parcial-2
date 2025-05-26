package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.IncidenciaFraude;
import com.example.BDPostgres.Repository.IncidenciaFraudeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidenciaFraudeService {
    private final IncidenciaFraudeRepository incidenciaFraudeRepository;

    public List<IncidenciaFraude> findAll() {
        return incidenciaFraudeRepository.findAll();
    }

    public Optional<IncidenciaFraude> findById(Integer id) {
        return incidenciaFraudeRepository.findById(id);
    }

    public IncidenciaFraude save(IncidenciaFraude incidenciaFraude) {
        return incidenciaFraudeRepository.save(incidenciaFraude);
    }

    public void deleteById(Integer id) {
        incidenciaFraudeRepository.deleteById(id);
    }

    public IncidenciaFraude update(IncidenciaFraude incidenciaFraude) {
        return incidenciaFraudeRepository.save(incidenciaFraude);
    }
}