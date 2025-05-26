package com.example.BDPostgres.Controller;

import com.example.BDPostgres.Model.Turno;
import com.example.BDPostgres.Service.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/turno")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    @PostMapping
    public ResponseEntity<Turno> save(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.save(turno));
    }

    @GetMapping
    public ResponseEntity<List<Turno>> findall() {
        return ResponseEntity.ok(turnoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> findById(@PathVariable Integer id) {
        return turnoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Turno> update(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.update(turno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        turnoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
