package com.example.BDPostgres.Controller;

import com.example.BDPostgres.Model.Equipo;
import com.example.BDPostgres.Service.EquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService equipoService;

    @GetMapping
    public List<Equipo> getAllEquipos() {
        return equipoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable Integer id) {
        Optional<Equipo> equipo = equipoService.findById(id);
        return equipo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Equipo> createEquipo(@RequestBody Equipo equipo) {
        Equipo savedEquipo = equipoService.save(equipo);
        return ResponseEntity.status(201).body(savedEquipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable Integer id, @RequestBody Equipo equipo) {
        equipo.setId_equipo(id);
        Equipo updatedEquipo = equipoService.save(equipo);
        return ResponseEntity.ok(updatedEquipo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable Integer id) {
        Optional<Equipo> equipo = equipoService.findById(id);
        if (equipo.isPresent()) {
            equipoService.deleteById(id);
            return ResponseEntity.noContent().build();  // Devuelve 204 No Content
        }
        return ResponseEntity.notFound().build();  // Devuelve 404 Not Found si no existe
    }
}
