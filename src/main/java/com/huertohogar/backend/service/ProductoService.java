package com.huertohogar.backend.service;

import com.huertohogar.backend.model.Producto;
import com.huertohogar.backend.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> getAll() {
        return repo.findAll();
    }

    public Optional<Producto> getById(Long id) {
        return repo.findById(id);
    }

    public List<Producto> getByCategoria(String categoria) {
        return repo.findByCategoriaIgnoreCase(categoria);
    }

    public Producto save(Producto p) {
        return repo.save(p);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
