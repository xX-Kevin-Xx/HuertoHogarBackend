package com.huertohogar.backend.repository;

import com.huertohogar.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoriaIgnoreCase(String categoria);
}
