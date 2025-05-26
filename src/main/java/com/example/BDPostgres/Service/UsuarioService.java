package com.example.BDPostgres.Service;

import com.example.BDPostgres.Model.Usuario;
import com.example.BDPostgres.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario update(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getIdUsuario())) {
            throw new RuntimeException("Cliente not found with id: " + usuario.getIdUsuario());
        }
        return usuarioRepository.save(usuario);
    }

}
