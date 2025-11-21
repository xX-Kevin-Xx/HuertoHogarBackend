package com.huertohogar.backend.service;

import com.huertohogar.backend.model.Usuario;
import com.huertohogar.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Optional<Usuario> findByCorreo(String correo) {
        return repo.findByCorreo(correo);
    }

    public Usuario save(Usuario usuario) {
        return repo.save(usuario);
    }

    public List<Usuario> findAll() {
        return repo.findAll();
    }

}
