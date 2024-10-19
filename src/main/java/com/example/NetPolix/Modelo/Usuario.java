package com.example.NetPolix.Modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreUsuario;
    private String contrasena;
    private String correoElectronico;
    private String preferencias;

    @ElementCollection
    private List<String> wishlist = new ArrayList<>(); // Wishlist del usuario

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }

    public List<String> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<String> wishlist) {
        this.wishlist = wishlist;
    }
}

