package com.example.BDPostgres.Controller;

import com.example.BDPostgres.Model.Partido;
import com.example.BDPostgres.Service.PartidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/partidos")
@RequiredArgsConstructor
public class PartidoController {

    private final PartidoService partidoService;

    @GetMapping
    public List<Partido> getAllPartidos() {
        return partidoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partido> getPartidoById(@PathVariable Integer id) {
        Optional<Partido> partido = partidoService.findById(id);
        return partido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Partido> createPartido(@RequestBody Partido partido) {
        Partido savedPartido = partidoService.save(partido);
        return ResponseEntity.status(201).body(savedPartido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partido> updatePartido(@PathVariable Integer id, @RequestBody Partido partido) {
        partido.setId_partido(id);
        Partido updatedPartido = partidoService.save(partido);
        return ResponseEntity.ok(updatedPartido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartido(@PathVariable Integer id) {
        Optional<Partido> partido = partidoService.findById(id);
        if (partido.isPresent()) {
            partidoService.deleteById(id);
            return ResponseEntity.noContent().build();  // Devuelve 204 No Content
        }
        return ResponseEntity.notFound().build();  // Devuelve 404 Not Found si no existe
    }

    // Consulta 1: Obtener total de goles en los que ha participado un equipo
    @GetMapping("/goles/{equipoId}")
    public int obtenerGolesPorEquipo(@PathVariable Integer equipoId) {
        return partidoService.obtenerGolesPorEquipo(equipoId);
    }

    // Consulta 2: Obtener resultados de los partidos con nombres de los equipos
    @GetMapping("/resultados")
    public List<Object[]> obtenerResultadosPartidosConEquipos() {
        return partidoService.obtenerResultadosPartidosConEquipos();
    }
}
