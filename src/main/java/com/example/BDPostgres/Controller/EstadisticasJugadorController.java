package com.example.BDPostgres.Controller;

import com.example.BDPostgres.Model.EstadisticasJugador;
import com.example.BDPostgres.Service.EstadisticasJugadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estadisticas-jugadores")
@RequiredArgsConstructor
public class EstadisticasJugadorController {

    private final EstadisticasJugadorService estadisticasJugadorService;

    @GetMapping
    public List<EstadisticasJugador> getAllEstadisticas() {
        return estadisticasJugadorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadisticasJugador> getEstadisticasById(@PathVariable Integer id) {
        Optional<EstadisticasJugador> estadisticas = estadisticasJugadorService.findById(id);
        return estadisticas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EstadisticasJugador> createEstadisticas(@RequestBody EstadisticasJugador estadisticas) {
        EstadisticasJugador savedEstadisticas = estadisticasJugadorService.save(estadisticas);
        return ResponseEntity.status(201).body(savedEstadisticas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadisticasJugador> updateEstadisticas(@PathVariable Integer id, @RequestBody EstadisticasJugador estadisticas) {
        estadisticas.setId_estadistica(id);
        EstadisticasJugador updatedEstadisticas = estadisticasJugadorService.save(estadisticas);
        return ResponseEntity.ok(updatedEstadisticas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstadisticas(@PathVariable Integer id) {
        Optional<EstadisticasJugador> estadisticas = estadisticasJugadorService.findById(id);
        if (estadisticas.isPresent()) {
            estadisticasJugadorService.deleteById(id);
            return ResponseEntity.noContent().build();  // Devuelve 204 No Content
        }
        return ResponseEntity.notFound().build();  // Devuelve 404 Not Found si no existe
    }
}
