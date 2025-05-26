package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.Reservacion;
import com.example.BDPostgres.Repository.ReservacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservacionService {
    private final ReservacionRepository reservacionRepository;

    public List<Reservacion> findAll() {
        return reservacionRepository.findAll();
    }

    public Optional<Reservacion> findById(Integer id) {
        return reservacionRepository.findById(id);
    }

    public Reservacion save(Reservacion reservacion) {
        return reservacionRepository.save(reservacion);
    }

    public void deleteById(Integer id) {
        reservacionRepository.deleteById(id);
    }

    public Reservacion update(Reservacion reservacion) {
        if (!reservacionRepository.existsById(reservacion.getIdReservacion())) {
            throw new RuntimeException("Cliente not found with id: " + reservacion.getIdReservacion());
        }
        return reservacionRepository.save(reservacion);
    }

}
