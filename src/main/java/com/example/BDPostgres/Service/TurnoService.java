package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.Turno;
import com.example.BDPostgres.Repository.TurnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TurnoService {
    private final TurnoRepository turnoRepository;

    public List<Turno> findAll() {
        return turnoRepository.findAll();
    }

    public Optional<Turno> findById(Integer id) {
        return turnoRepository.findById(id);
    }

    public Turno save(Turno turno) {
        return turnoRepository.save(turno);
    }

    public void deleteById(Integer id) {
        turnoRepository.deleteById(id);
    }

    public Turno update(Turno turno) {
        return turnoRepository.save(turno);
    }
}