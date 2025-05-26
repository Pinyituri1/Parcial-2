package com.example.BDPostgres.Repository;

import com.example.BDPostgres.Model.CierreCaja;
import com.example.BDPostgres.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
