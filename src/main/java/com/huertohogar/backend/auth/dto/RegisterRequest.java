package com.huertohogar.backend.auth.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String nombre;
    private String correo;
    private String password;

    private String telefono;
    private String direccion;
}
