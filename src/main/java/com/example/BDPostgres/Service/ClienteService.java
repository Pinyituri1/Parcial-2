package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.Cliente;
import com.example.BDPostgres.Repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteById(Integer id) {
        clienteRepository.deleteById(id);
    }

    public Cliente update(Cliente cliente) {
        if (!clienteRepository.existsById(cliente.getIdCliente())) {
            throw new RuntimeException("Cliente not found with id: " + cliente.getIdCliente());
        }
        return clienteRepository.save(cliente);
    }
}
