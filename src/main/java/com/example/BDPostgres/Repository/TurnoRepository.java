package com.example.BDPostgres.Repository;

import com.example.BDPostgres.Model.CierreCaja;
import com.example.BDPostgres.Model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {
}
