package com.example.BDPostgres.Repository;

import com.example.BDPostgres.Model.CierreCaja;
import com.example.BDPostgres.Model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
}
