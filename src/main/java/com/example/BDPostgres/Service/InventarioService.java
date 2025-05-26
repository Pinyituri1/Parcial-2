package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.Cliente;
import com.example.BDPostgres.Model.Inventario;
import com.example.BDPostgres.Repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InventarioService {
    private final InventarioRepository inventarioRepository;

    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> findById(Integer id) {
        return inventarioRepository.findById(id);
    }

    public Inventario save(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public void deleteById(Integer id) {
        inventarioRepository.deleteById(id);
    }

    public Inventario update(Inventario inventario) {
        if (!inventarioRepository.existsById(inventario.getIdInventario())) {
            throw new RuntimeException("Cliente not found with id: " + inventario.getIdInventario());
        }
        return inventarioRepository.save(inventario);
    }
}