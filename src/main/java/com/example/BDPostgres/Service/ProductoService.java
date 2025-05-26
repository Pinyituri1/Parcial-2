package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.Producto;
import com.example.BDPostgres.Repository.ProductoRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Optional<Producto> findById(Integer id) {
        return productoRepository.findById(id);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteById(Integer id) {
        productoRepository.deleteById(id);
    }

    public Producto update(Producto producto) {
        if (!productoRepository.existsById(producto.getIdProducto())) {
            throw new RuntimeException("Cliente not found with id: " + producto.getIdProducto());
        }
        return productoRepository.save(producto);
    }

}