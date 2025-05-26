package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.Venta;
import com.example.BDPostgres.Repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VentaService {
    private final VentaRepository ventaRepository;

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> findById(Integer id) {
        return ventaRepository.findById(id);
    }

    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    public void deleteById(Integer id) {
        ventaRepository.deleteById(id);
    }

    public Venta update(Venta venta) {
        if (!ventaRepository.existsById(venta.getIdVenta())) {
            throw new RuntimeException("Cliente not found with id: " + venta.getIdVenta());
        }
        return ventaRepository.save(venta);
    }

}