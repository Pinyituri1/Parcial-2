package com.example.BDPostgres.Controller;

import com.example.BDPostgres.Model.Jugador;
import com.example.BDPostgres.Service.JugadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jugadores")
@RequiredArgsConstructor
public class JugadorController {

    private final JugadorService jugadorService;

    @GetMapping
    public List<Jugador> getAllJugadores() {
        return jugadorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> getJugadorById(@PathVariable Integer id) {
        Optional<Jugador> jugador = jugadorService.findById(id);
        return jugador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Jugador> createJugador(@RequestBody Jugador jugador) {
        Jugador savedJugador = jugadorService.save(jugador);
        return ResponseEntity.status(201).body(savedJugador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jugador> updateJugador(@PathVariable Integer id, @RequestBody Jugador jugador) {
        jugador.setId_jugador(id);
        Jugador updatedJugador = jugadorService.save(jugador);
        return ResponseEntity.ok(updatedJugador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJugador(@PathVariable Integer id) {
        Optional<Jugador> jugador = jugadorService.findById(id);
        if (jugador.isPresent()) {
            jugadorService.deleteById(id);
            return ResponseEntity.noContent().build();  // Devuelve 204 No Content
        }
        return ResponseEntity.notFound().build();  // Devuelve 404 Not Found si no existe
    }

    // Consulta 3: Obtener jugadores de un equipo específico
    @GetMapping("/equipo/{equipoId}")
    public List<Jugador> obtenerJugadoresPorEquipo(@PathVariable Integer equipoId) {
        return jugadorService.obtenerJugadoresPorEquipo(equipoId);
    }

    // Consulta 4: Obtener jugadores con más de X goles
    @GetMapping("/goles/{goles}")
    public List<Jugador> obtenerJugadoresConMasDeXGoles(@PathVariable Integer goles) {
        return jugadorService.obtenerJugadoresConMasDeXGoles(goles);
    }
}
