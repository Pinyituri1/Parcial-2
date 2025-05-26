package com.example.BDPostgres.Controller;

import com.example.BDPostgres.Model.Mesa;
import com.example.BDPostgres.Service.MesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mesa")
@RequiredArgsConstructor
public class MesaController {
    private final MesaService mesaService;

    @GetMapping
    public ResponseEntity<List<Mesa>> findAll() {
        return ResponseEntity.ok(mesaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> findById(@PathVariable Integer id) {
        return mesaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Mesa> save(@RequestBody Mesa mesa) {
        return ResponseEntity.ok(mesaService.save(mesa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> update(@PathVariable Integer id, @RequestBody Mesa mesa) {
        mesa.setIdMesa(id);  // Asegura que el ID sea correcto
        Mesa updated = mesaService.update(mesa);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        mesaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
