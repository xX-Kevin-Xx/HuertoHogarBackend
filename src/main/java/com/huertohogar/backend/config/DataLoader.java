package com.huertohogar.backend.config;

import com.huertohogar.backend.model.Producto;
import com.huertohogar.backend.model.Usuario;
import com.huertohogar.backend.repository.ProductoRepository;
import com.huertohogar.backend.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(
            ProductoRepository repo,
            UsuarioRepository repoUsuario,
            PasswordEncoder passwordEncoder  // <-- INYECTADO AQUÍ
    ) {
        return args -> {

            // --- PRODUCTOS (SIN CAMBIOS) ---
            repo.save(new Producto(null,
                    "Manzanas Fuji",
                    "Crujientes y dulces, cultivadas en El Manzano Región de O'Higgins. Perfectas para niños y adultos.",
                    1200.0,
                    "Frutas Frescas",
                    80));

            repo.save(new Producto(null,
                    "Naranjas Valencia",
                    "Jugosas y ricas en vitamina C, ideales para hacer jugo o para consumo inmediato.",
                    1000.0,
                    "Frutas Frescas",
                    70));

            repo.save(new Producto(null,
                    "Plátanos Cavendish",
                    "Dulces y cremosos, perfectos para postres y batidos. Ideal para deportistas.",
                    1500.0,
                    "Frutas Frescas",
                    90));

            repo.save(new Producto(null,
                    "Zanahorias Orgánicas",
                    "Cultivadas sin pesticidas en Melipilla. Excelente fuente de vitamina A y fibra.",
                    900.0,
                    "Verduras Orgánicas",
                    60));

            repo.save(new Producto(null,
                    "Espinacas Frescas",
                    "Espinacas frescas ricas en hierro y vitaminas. Perfectas para ensaladas y batidos verdes.",
                    1100.0,
                    "Verduras Orgánicas",
                    50));

            repo.save(new Producto(null,
                    "Pimientos Tricolores",
                    "Mezcla de pimientos rojos, amarillos y verdes. Ideales para salteados y ensaladas.",
                    1800.0,
                    "Verduras Orgánicas",
                    0));

            repo.save(new Producto(null,
                    "Miel Orgánica",
                    "Miel pura de abejas criadas en entorno natural. Endulzante natural sin procesar.",
                    3500.0,
                    "Productos Orgánicos",
                    40));

            repo.save(new Producto(null,
                    "Quinua Orgánica",
                    "Quinua orgánica de alta calidad. Rica en proteínas y perfecta para dietas saludables.",
                    2800.0,
                    "Productos Orgánicos",
                    30));

            repo.save(new Producto(null,
                    "Leche Entera",
                    "Leche entera fresca de vacas criadas en praderas naturales. Pasteurizada y nutritiva.",
                    1200.0,
                    "Productos Lácteos",
                    100));

            repo.save(new Producto(null,
                    "Mantequilla con Sal",
                    "Mantequilla cremosa con sal, perfecta para pan tostado y cocina.",
                    2200.0,
                    "Productos Lácteos",
                    80));

            System.out.println("✅ Productos precargados en la base de datos de Huerto Hogar");

            // ---- USUARIOS ----
            Usuario admin = new Usuario(
                    null,
                    "Kevin Catalan",
                    "kevincatalanpinto@gmail.com",
                    passwordEncoder.encode("112233"),
                    "ADMIN",
                    "123456789",
                    "Mi dirección"
            );
            repoUsuario.save(admin);

            Usuario cliente = new Usuario(
                    null,
                    "Cliente Ejemplo",
                    "cliente@correo.cl",
                    passwordEncoder.encode("abcd"),
                    "USER",
                    "987654321",
                    "Casa cliente"
            );
            repoUsuario.save(cliente);

            System.out.println("✅ Usuarios precargados correctamente");
        };
    }
}
