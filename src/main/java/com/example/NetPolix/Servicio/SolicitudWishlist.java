package com.example.NetPolix.Servicio;

public class SolicitudWishlist {
    private String nombreUsuario;
    private String contenido;  // Contenido a agregar a la wishlist

    // Getters y setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
