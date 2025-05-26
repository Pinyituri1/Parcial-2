package com.example.BDPostgres.Controller;

import com.example.BDPostgres.Model.CierreCaja;
import com.example.BDPostgres.Model.Usuario;
import com.example.BDPostgres.Repository.UsuarioRepository;
import com.example.BDPostgres.Service.CierreCajaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cierresCaja")
@RequiredArgsConstructor
public class CierreCajaController {

    private final CierreCajaService cierreCajaService;
    private final UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<CierreCaja> lista = cierreCajaService.findAll();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener cierres de caja: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Optional<CierreCaja> cierreCajaOpt = cierreCajaService.findById(id);
            if (cierreCajaOpt.isPresent()) {
                return ResponseEntity.ok(cierreCajaOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Cierre de caja no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar cierre de caja: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CierreCaja cierreCaja) {
        try {
            if (cierreCaja.getUsuario() == null || cierreCaja.getUsuario().getIdUsuario() == null) {
                return ResponseEntity.badRequest().body("Debe proporcionar el ID del usuario.");
            }

            Optional<Usuario> usuarioOpt = usuarioRepository.findById(cierreCaja.getUsuario().getIdUsuario());
            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Usuario no encontrado con ID: " + cierreCaja.getUsuario().getIdUsuario());
            }

            cierreCaja.setUsuario(usuarioOpt.get());
            CierreCaja saved = cierreCajaService.save(cierreCaja);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el cierre de caja: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CierreCaja cierreCaja) {
        try {
            Optional<CierreCaja> existente = cierreCajaService.findById(id);
            if (existente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cierre de caja no encontrado con ID: " + id);
            }

            if (cierreCaja.getUsuario() == null || cierreCaja.getUsuario().getIdUsuario() == null) {
                return ResponseEntity.badRequest().body("Debe proporcionar el ID del usuario.");
            }

            Optional<Usuario> usuarioOpt = usuarioRepository.findById(cierreCaja.getUsuario().getIdUsuario());
            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Usuario no encontrado con ID: " + cierreCaja.getUsuario().getIdUsuario());
            }

            cierreCaja.setIdCierre(id);
            cierreCaja.setUsuario(usuarioOpt.get());

            CierreCaja actualizado = cierreCajaService.save(cierreCaja);
            return ResponseEntity.ok(actualizado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el cierre de caja: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            cierreCajaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el cierre de caja: " + e.getMessage());
        }
    }
}
