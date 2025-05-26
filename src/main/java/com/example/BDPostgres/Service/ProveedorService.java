package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.Proveedor;
import com.example.BDPostgres.Repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProveedorService {
    private final ProveedorRepository proveedorRepository;

    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> findById(Integer id) {
        return proveedorRepository.findById(id);
    }

    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public void deleteById(Integer id) {
        proveedorRepository.deleteById(id);
    }

    public Proveedor update(Proveedor proveedor) {
        if (!proveedorRepository.existsById(proveedor.getIdProveedor())) {
            throw new RuntimeException("Cliente not found with id: " + proveedor.getIdProveedor());
        }
        return proveedorRepository.save(proveedor);
    }

}