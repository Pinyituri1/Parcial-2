package com.example.BDPostgres.Repository;

import com.example.BDPostgres.Model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {
}
