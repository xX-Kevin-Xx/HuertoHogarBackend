package com.huertohogar.backend.controller;

import com.huertohogar.backend.auth.dto.RegisterRequest;
import com.huertohogar.backend.model.Usuario;
import com.huertohogar.backend.repository.UsuarioRepository;
import com.huertohogar.backend.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {

        String correo = body.get("correo");
        String password = body.get("password");

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(correo, password)
            );
        } catch (Exception e) {
            return ResponseEntity.status(401).body(
                    Map.of("error", "Correo o contraseña incorrectos")
            );
        }

        Usuario user = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtService.generateToken(user.getCorreo(), user.getRol());

        return ResponseEntity.ok(
                Map.of(
                        "message", "Login exitoso",
                        "token", token,
                        "nombre", user.getNombre(),
                        "correo", user.getCorreo(),
                        "rol", user.getRol(),
                        "telefono", user.getTelefono(),
                        "direccion", user.getDireccion()
                )
        );
    }


    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody RegisterRequest request) {

        if (usuarioRepository.findByCorreo(request.getCorreo()).isPresent()) {
            return Map.of("error", "El correo ya está registrado");
        }

        Usuario usuario = new Usuario();

        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setTelefono(request.getTelefono());
        usuario.setDireccion(request.getDireccion());

        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.getCorreo().equalsIgnoreCase("kevincatalanpinto@gmail.com")) {
            usuario.setRol("ADMIN");
        } else {
            usuario.setRol("USER");
        }

        usuarioRepository.save(usuario);

        return Map.of("message", "Usuario registrado con éxito");
    }
}
