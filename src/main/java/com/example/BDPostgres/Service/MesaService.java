package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.Mesa;
import com.example.BDPostgres.Repository.MesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MesaService {
    private final MesaRepository mesaRepository;

    public List<Mesa> findAll() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> findById(Integer id) {
        return mesaRepository.findById(id);
    }

    public Mesa save(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public void deleteById(Integer id) {
        mesaRepository.deleteById(id);
    }

    public Mesa update(Mesa mesa) {
        if (!mesaRepository.existsById(mesa.getIdMesa())) {
            throw new RuntimeException("Cliente not found with id: " + mesa.getIdMesa());
        }
        return mesaRepository.save(mesa);
    }

}