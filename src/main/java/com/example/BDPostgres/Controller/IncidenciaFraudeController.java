package com.example.BDPostgres.Controller;

import com.example.BDPostgres.Model.IncidenciaFraude;
import com.example.BDPostgres.Model.Venta;
import com.example.BDPostgres.Repository.IncidenciaFraudeRepository;
import com.example.BDPostgres.Repository.VentaRepository;
import com.example.BDPostgres.Service.IncidenciaFraudeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/incidencias")
@RequiredArgsConstructor
public class IncidenciaFraudeController {

    private final IncidenciaFraudeService incidenciaFraudeService;
    private final VentaRepository ventaRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<IncidenciaFraude> lista = incidenciaFraudeService.findAll();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener incidencias de fraude: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Optional<IncidenciaFraude> incidenciaFraudeOpt = incidenciaFraudeService.findById(id);
            if (incidenciaFraudeOpt.isPresent()) {
                return ResponseEntity.ok(incidenciaFraudeOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Incidencia de fraude no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar incidencia de fraude: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody IncidenciaFraude incidenciaFraude) {
        try {
            // Validar si la venta existe
            Optional<Venta> ventaOpt = ventaRepository.findById(incidenciaFraude.getIdVenta());
            if (ventaOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Venta no encontrada con ID: " + incidenciaFraude.getIdVenta());
            }
            incidenciaFraude.setVenta(ventaOpt.get()); // Asociamos la venta a IncidenciaFraude

            IncidenciaFraude savedIncidenciaFraude = incidenciaFraudeService.save(incidenciaFraude);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedIncidenciaFraude);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la incidencia de fraude: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody IncidenciaFraude incidenciaFraude) {
        try {
            // Verificar si la incidencia de fraude existe
            Optional<IncidenciaFraude> incidenciaFraudeOpt = incidenciaFraudeService.findById(id);
            if (incidenciaFraudeOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incidencia de fraude no encontrada con ID: " + id);
            }
            incidenciaFraude.setIdIncidencia(id);

            // Verificar si la venta existe
            Optional<Venta> ventaOpt = ventaRepository.findById(incidenciaFraude.getIdVenta());
            if (ventaOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Venta no encontrada con ID: " + incidenciaFraude.getIdVenta());
            }
            incidenciaFraude.setVenta(ventaOpt.get());

            IncidenciaFraude updatedIncidenciaFraude = incidenciaFraudeService.save(incidenciaFraude);
            return ResponseEntity.ok(updatedIncidenciaFraude);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la incidencia de fraude: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            incidenciaFraudeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar incidencia de fraude: " + e.getMessage());
        }
    }
}
