package com.example.BDPostgres.Controller;

import com.example.BDPostgres.Model.Proveedor;
import com.example.BDPostgres.Model.Venta;
import com.example.BDPostgres.Service.ProveedorService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/proveedor")
@RequiredArgsConstructor
public class ProveedorController {
    private final ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> findAll() {
        return ResponseEntity.ok(proveedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> findById(@PathVariable Integer id) {
        return proveedorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Proveedor> save(@RequestBody Proveedor proveedor) {
        return ResponseEntity.ok(proveedorService.save(proveedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> update(@PathVariable Integer id, @RequestBody Proveedor proveedor) {
        proveedor.setIdProveedor(id);  // Asegura que el ID sea correcto
        Proveedor updated = proveedorService.update(proveedor);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        proveedorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
