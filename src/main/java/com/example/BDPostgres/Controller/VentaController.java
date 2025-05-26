package com.example.BDPostgres.Controller;

import com.example.BDPostgres.Model.Cliente;
import com.example.BDPostgres.Model.Venta;
import com.example.BDPostgres.Model.DetalleVenta;
import com.example.BDPostgres.Repository.ClienteRepository;
import com.example.BDPostgres.Repository.VentaRepository;
import com.example.BDPostgres.Repository.DetalleVentaRepository;
import com.example.BDPostgres.Service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/venta")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;
    private final ClienteRepository clienteRepository;
    private final DetalleVentaRepository detalleVentaRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<Venta> lista = ventaService.findAll();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener ventas: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Optional<Venta> ventaOpt = ventaService.findById(id);
            if (ventaOpt.isPresent()) {
                return ResponseEntity.ok(ventaOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Venta no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar venta: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Venta venta) {
        try {
            Optional<Cliente> clienteOpt = clienteRepository.findById(venta.getIdCliente());

            if (clienteOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Cliente no encontrado.");
            }

            Venta nuevaVenta = new Venta();
            nuevaVenta.setIdCliente(venta.getIdCliente());
            nuevaVenta.setCliente(clienteOpt.get());
            nuevaVenta.setFecha(venta.getFecha());
            nuevaVenta.setTotal(venta.getTotal());

            Venta saved = ventaService.save(nuevaVenta);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la venta: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Venta venta) {
        try {
            venta.setIdVenta(id);

            Optional<Cliente> clienteOpt = clienteRepository.findById(venta.getIdCliente());

            if (clienteOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Cliente no encontrado.");
            }

            venta.setCliente(clienteOpt.get());

            Venta updated = ventaService.update(venta);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la venta: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            ventaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar venta: " + e.getMessage());
        }
    }
}
