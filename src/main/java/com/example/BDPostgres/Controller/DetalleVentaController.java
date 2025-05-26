package com.example.BDPostgres.Controller;

import com.example.BDPostgres.Model.DetalleVenta;
import com.example.BDPostgres.Model.Producto;
import com.example.BDPostgres.Model.Venta;
import com.example.BDPostgres.Repository.ProductoRepository;
import com.example.BDPostgres.Repository.VentaRepository;
import com.example.BDPostgres.Repository.DetalleVentaRepository;
import com.example.BDPostgres.Service.DetalleVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/detalleVenta")
@RequiredArgsConstructor
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;
    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<DetalleVenta> lista = detalleVentaService.findAll();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener detalles de venta: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Optional<DetalleVenta> detalleVentaOpt = detalleVentaService.findById(id);
            if (detalleVentaOpt.isPresent()) {
                return ResponseEntity.ok(detalleVentaOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Detalle de venta no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar detalle de venta: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody DetalleVenta detalleVenta) {
        try {
            // Validar si la venta existe
            Optional<Venta> ventaOpt = ventaRepository.findById(detalleVenta.getIdVenta());
            if (ventaOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Venta no encontrada con ID: " + detalleVenta.getIdVenta());
            }
            detalleVenta.setVenta(ventaOpt.get()); // Asociamos la venta a DetalleVenta

            // Validar si el producto existe
            Optional<Producto> productoOpt = productoRepository.findById(detalleVenta.getIdProducto());
            if (productoOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Producto no encontrado con ID: " + detalleVenta.getIdProducto());
            }
            detalleVenta.setProducto(productoOpt.get()); // Asociamos el producto a DetalleVenta

            // Calcular subtotal (si lo necesitas hacer manualmente)
            if (detalleVenta.getCantidad() != null && detalleVenta.getPrecioUnitario() != null) {
                detalleVenta.setSubtotal(detalleVenta.getCantidad() * detalleVenta.getPrecioUnitario());
            }

            DetalleVenta savedDetalleVenta = detalleVentaService.save(detalleVenta);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDetalleVenta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el detalle de venta: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody DetalleVenta detalleVenta) {
        try {
            // Verificar si el detalle de venta existe
            Optional<DetalleVenta> detalleVentaOpt = detalleVentaService.findById(id);
            if (detalleVentaOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle de venta no encontrado con ID: " + id);
            }
            detalleVenta.setIdDetalle(id);

            // Verificar si la venta existe
            Optional<Venta> ventaOpt = ventaRepository.findById(detalleVenta.getIdVenta());
            if (ventaOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Venta no encontrada con ID: " + detalleVenta.getIdVenta());
            }
            detalleVenta.setVenta(ventaOpt.get());

            // Verificar si el producto existe
            Optional<Producto> productoOpt = productoRepository.findById(detalleVenta.getIdProducto());
            if (productoOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Producto no encontrado con ID: " + detalleVenta.getIdProducto());
            }
            detalleVenta.setProducto(productoOpt.get());

            // Calcular subtotal
            if (detalleVenta.getCantidad() != null && detalleVenta.getPrecioUnitario() != null) {
                detalleVenta.setSubtotal(detalleVenta.getCantidad() * detalleVenta.getPrecioUnitario());
            }

            DetalleVenta updatedDetalleVenta = detalleVentaService.save(detalleVenta);
            return ResponseEntity.ok(updatedDetalleVenta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el detalle de venta: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            detalleVentaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar detalle de venta: " + e.getMessage());
        }
    }
}
