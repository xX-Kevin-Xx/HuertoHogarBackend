package com.huertohogar.backend.controller;

import com.huertohogar.backend.model.Usuario;
import com.huertohogar.backend.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsuarios() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody Usuario updated) {

        return service.findById(id).map(existing -> {

            if ("ADMIN".equals(existing.getRol())) {
                return ResponseEntity.badRequest().body("No se puede modificar al administrador principal.");
            }

            existing.setRol(updated.getRol());
            existing.setTelefono(updated.getTelefono());
            existing.setDireccion(updated.getDireccion());

            return ResponseEntity.ok(service.save(existing));

        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {

        return service.findById(id).map(existing -> {

            if ("ADMIN".equals(existing.getRol())) {
                return ResponseEntity.badRequest().body("No se puede eliminar al administrador principal.");
            }

            service.delete(id);
            return ResponseEntity.noContent().build();

        }).orElse(ResponseEntity.notFound().build());
    }


}


