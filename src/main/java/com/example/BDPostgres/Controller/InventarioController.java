package com.example.BDPostgres.Controller;

import com.example.BDPostgres.Model.Cliente;
import com.example.BDPostgres.Model.Inventario;
import com.example.BDPostgres.Service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventario")
@RequiredArgsConstructor
public class InventarioController {
    private final InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> findAll() {
        return ResponseEntity.ok(inventarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> findById(@PathVariable Integer id) {
        return inventarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Inventario> save(@RequestBody Inventario inventario) {
        return ResponseEntity.ok(inventarioService.save(inventario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> update(@PathVariable Integer id, @RequestBody Inventario inventario) {
        inventario.setIdInventario(id);
        return ResponseEntity.ok(inventarioService.update(inventario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        inventarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
