package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.DetalleVenta;
import com.example.BDPostgres.Repository.DetalleVentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetalleVentaService {
    private final DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> findAll() {
        return detalleVentaRepository.findAll();
    }

    public Optional<DetalleVenta> findById(Integer id) {
        return detalleVentaRepository.findById(id);
    }

    public DetalleVenta save(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    public void deleteById(Integer id) {
        detalleVentaRepository.deleteById(id);
    }

    public DetalleVenta update(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }
}