package com.example.BDPostgres.Controller;

import com.example.BDPostgres.Model.Cliente;
import com.example.BDPostgres.Model.Mesa;
import com.example.BDPostgres.Model.Reservacion;
import com.example.BDPostgres.Repository.ClienteRepository;
import com.example.BDPostgres.Repository.MesaRepository;
import com.example.BDPostgres.Service.ReservacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/reservacion")
@RequiredArgsConstructor
public class ReservacionController {

    private final ReservacionService reservacionService;
    private final ClienteRepository clienteRepository;
    private final MesaRepository mesaRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<Reservacion> lista = reservacionService.findAll();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener reservaciones: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Optional<Reservacion> reservacionOpt = reservacionService.findById(id);
            if (reservacionOpt.isPresent()) {
                return ResponseEntity.ok(reservacionOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Reservación no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar reservación: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Reservacion reservacion) {
        try {
            // Buscar el cliente y la mesa por sus respectivos IDs
            Optional<Cliente> clienteOpt = clienteRepository.findById(reservacion.getIdCliente());
            Optional<Mesa> mesaOpt = mesaRepository.findById(reservacion.getIdMesa());

            // Verificar si el cliente y la mesa existen en la base de datos
            if (clienteOpt.isEmpty() || mesaOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Cliente o Mesa no encontrados.");
            }

            // Asignar los objetos Cliente y Mesa a la reservación
            Reservacion nuevaReservacion = new Reservacion();
            nuevaReservacion.setIdCliente(reservacion.getIdCliente());
            nuevaReservacion.setIdMesa(reservacion.getIdMesa());
            nuevaReservacion.setFecha(reservacion.getFecha());
            nuevaReservacion.setHora(reservacion.getHora());
            nuevaReservacion.setEstado(reservacion.getEstado());
            nuevaReservacion.setCliente(clienteOpt.get());
            nuevaReservacion.setMesa(mesaOpt.get());

            // Guardar la reservación
            Reservacion saved = reservacionService.save(nuevaReservacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la reservación: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Reservacion reservacion) {
        try {
            reservacion.setIdReservacion(id);

            Optional<Cliente> clienteOpt = clienteRepository.findById(reservacion.getIdCliente());
            Optional<Mesa> mesaOpt = mesaRepository.findById(reservacion.getIdMesa());

            if (clienteOpt.isEmpty() || mesaOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Cliente o Mesa no encontrados.");
            }

            reservacion.setCliente(clienteOpt.get());
            reservacion.setMesa(mesaOpt.get());

            Reservacion updated = reservacionService.update(reservacion);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la reservación: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            reservacionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar reservación: " + e.getMessage());
        }
    }
}
