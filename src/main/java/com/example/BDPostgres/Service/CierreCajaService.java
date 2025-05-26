package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.CierreCaja;
import com.example.BDPostgres.Repository.CierreCajaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CierreCajaService {
    private final CierreCajaRepository cierreCajaRepository;

    public List<CierreCaja> findAll() {
        return cierreCajaRepository.findAll();
    }

    public Optional<CierreCaja> findById(Integer id) {
        return cierreCajaRepository.findById(id);
    }

    public CierreCaja save(CierreCaja cierreCaja) {
        return cierreCajaRepository.save(cierreCaja);
    }

    public void deleteById(Integer id) {
        cierreCajaRepository.deleteById(id);
    }

    public CierreCaja update(CierreCaja cierreCaja) {
        return cierreCajaRepository.save(cierreCaja);
    }
}