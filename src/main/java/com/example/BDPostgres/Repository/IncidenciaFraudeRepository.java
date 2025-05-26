package com.example.BDPostgres.Repository;

import com.example.BDPostgres.Model.CierreCaja;
import com.example.BDPostgres.Model.IncidenciaFraude;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenciaFraudeRepository extends JpaRepository<IncidenciaFraude, Integer> {
}
