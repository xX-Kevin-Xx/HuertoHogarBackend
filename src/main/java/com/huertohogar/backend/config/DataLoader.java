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
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            // 🟢 PRODUCTOS
            crearProducto(repo,
                    "Manzanas Fuji",
                    "Crujientes y dulces, cultivadas en El Manzano Región de O'Higgins.",
                    1200.0, "Frutas Frescas", 80,
                    "https://freshmate.cl/cdn/shop/files/manzanas_fuji_en_bowl_de_madera_21_11zon.webp?v=1724716431&width=713"
            );

            crearProducto(repo,
                    "Naranjas Valencia",
                    "Jugosas y ricas en vitamina C.",
                    1000.0, "Frutas Frescas", 70,
                    "https://lamejornaranjadevalencia.com/UserFiles/images/naranjas-ok.png"
            );

            crearProducto(repo,
                    "Plátanos Cavendish",
                    "Dulces y cremosos, perfectos para deportistas.",
                    1500.0, "Frutas Frescas", 90,
                    "https://cdn-redagricola.s3-accelerate.amazonaws.com/2025/09/Cientificos_alemanes_desarrollan_platano_Cavendish_resistente_a_la_oxidacion_y_con_mayor_vida_util.jpg"
            );

            crearProducto(repo,
                    "Zanahorias Orgánicas",
                    "Cultivadas sin pesticidas, ricas en vitamina A.",
                    900.0, "Verduras Orgánicas", 60,
                    "https://i5.walmartimages.cl/asr/30b2896e-0f63-4055-9350-47818c12b42d.5b324acce8aa649e62b767a6abf8c7d0.jpeg?null"
            );

            crearProducto(repo,
                    "Espinacas Frescas",
                    "Ricas en hierro, perfectas para batidos.",
                    1100.0, "Verduras Orgánicas", 50,
                    "https://www.conasi.eu/blog/wp-content/uploads/2023/07/recetas-con-espinacas-d.jpg"
            );

            crearProducto(repo,
                    "Pimientos Tricolores",
                    "Ideales para salteados y ensaladas.",
                    1800.0, "Verduras Orgánicas", 0,
                    "https://www.huleymantel.com/uploads/s1/44/54/25/alba-67.webp"
            );

            crearProducto(repo,
                    "Miel Orgánica",
                    "Endulzante natural sin procesar.",
                    3500.0, "Productos Orgánicos", 40,
                    "https://pbs.twimg.com/media/Eb5CV_5WkAAq-20.jpg"
            );

            crearProducto(repo,
                    "Quinua Orgánica",
                    "Quinua de alta calidad rica en proteínas.",
                    2800.0, "Productos Orgánicos", 30,
                    "https://pqs.pe/wp-content/uploads/2021/11/quinua.jpg"
            );

            crearProducto(repo,
                    "Leche Entera",
                    "Leche fresca pasteurizada.",
                    1200.0, "Productos Lácteos", 100,
                    "https://glycemic-index.net/images/CcjBBjdE4b.webp"
            );

            crearProducto(repo,
                    "Mantequilla con Sal",
                    "Mantequilla cremosa, ideal para pan y cocina.",
                    2200.0, "Productos Lácteos", 80,
                    "https://cdnx.jumpseller.com/99578480-7/image/37730304/mantequilla.jpg?1689620808"
            );

            System.out.println("✅ Productos precargados en la base de datos");

            // 🟡 USUARIOS
            Usuario admin = new Usuario();
            admin.setNombre("Kevin Catalan");
            admin.setCorreo("kevincatalanpinto@gmail.com");
            admin.setPassword(passwordEncoder.encode("112233"));
            admin.setRol("ADMIN");
            admin.setTelefono("123456789");
            admin.setDireccion("Mi dirección");
            repoUsuario.save(admin);

            Usuario cliente = new Usuario();
            cliente.setNombre("Cliente Ejemplo");
            cliente.setCorreo("cliente@correo.cl");
            cliente.setPassword(passwordEncoder.encode("abcd"));
            cliente.setRol("USER");
            cliente.setTelefono("987654321");
            cliente.setDireccion("Casa cliente");
            repoUsuario.save(cliente);

            System.out.println("✅ Usuarios precargados correctamente");
        };
    }

    private void crearProducto(
            ProductoRepository repo,
            String nombre,
            String descripcion,
            Double precio,
            String categoria,
            Integer stock,
            String imagenUrl
    ) {
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setCategoria(categoria);
        p.setStock(stock);
        p.setImagenUrl(imagenUrl);

        repo.save(p);
    }
}
