package com.example.BDPostgres.Controller;

import com.example.BDPostgres.Model.Entrenador;
import com.example.BDPostgres.Service.EntrenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entrenadores")
@RequiredArgsConstructor
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    @GetMapping
    public List<Entrenador> getAllEntrenadores() {
        return entrenadorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> getEntrenadorById(@PathVariable Integer id) {
        Optional<Entrenador> entrenador = entrenadorService.findById(id);
        return entrenador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Entrenador> createEntrenador(@RequestBody Entrenador entrenador) {
        Entrenador savedEntrenador = entrenadorService.save(entrenador);
        return ResponseEntity.status(201).body(savedEntrenador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> updateEntrenador(@PathVariable Integer id, @RequestBody Entrenador entrenador) {
        entrenador.setId_entrenador(id);
        Entrenador updatedEntrenador = entrenadorService.save(entrenador);
        return ResponseEntity.ok(updatedEntrenador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrenador(@PathVariable Integer id) {
        Optional<Entrenador> entrenador = entrenadorService.findById(id);
        if (entrenador.isPresent()) {
            entrenadorService.deleteById(id);
            return ResponseEntity.noContent().build();  // Devuelve 204 No Content
        }
        return ResponseEntity.notFound().build();  // Devuelve 404 Not Found si no existe
    }
}
