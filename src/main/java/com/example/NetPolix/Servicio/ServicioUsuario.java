package com.example.NetPolix.Servicio;

import com.example.NetPolix.Modelo.Usuario;
import com.example.NetPolix.RepositoriosJPA.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    // Crea un nuevo usuario a partir de la solicitud de registro
    public void crearUsuario(SolicitudRegistro solicitudRegistro) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario(solicitudRegistro.getNombreUsuario());
        nuevoUsuario.setContrasena(solicitudRegistro.getContrasena()); // En un caso real, se debería encriptar la contraseña
        nuevoUsuario.setCorreoElectronico(solicitudRegistro.getCorreoElectronico());
        nuevoUsuario.setPreferencias(solicitudRegistro.getPreferencias());

        repositorioUsuario.save(nuevoUsuario);
    }

    // Verifica si el nombre de usuario ya existe
    public boolean existePorNombreUsuario(String nombreUsuario) {
        return repositorioUsuario.existsByNombreUsuario(nombreUsuario);
    }

    // Autenticación de usuario
    public boolean autenticarUsuario(SolicitudLogin solicitudLogin) {
        Usuario usuario = repositorioUsuario.findByNombreUsuario(solicitudLogin.getNombreUsuario());
        return usuario != null && usuario.getContrasena().equals(solicitudLogin.getContrasena());
    }

    // Actualiza las preferencias del usuario
    public void actualizarPreferencias(PreferenciasUsuario preferenciasUsuario) {
        Usuario usuario = repositorioUsuario.findByNombreUsuario(preferenciasUsuario.getNombreUsuario());
        if (usuario != null) {
            usuario.setPreferencias(preferenciasUsuario.getPreferencias());
            repositorioUsuario.save(usuario);
        }
    }

    // Obtiene la wishlist del usuario
    public List<String> obtenerWishlist(String nombreUsuario) {
        Usuario usuario = repositorioUsuario.findByNombreUsuario(nombreUsuario);
        return usuario != null ? usuario.getWishlist() : new ArrayList<>();
    }

    // Agrega contenido a la wishlist
    public void agregarAWishlist(SolicitudWishlist solicitudWishlist) {
        Usuario usuario = repositorioUsuario.findByNombreUsuario(solicitudWishlist.getNombreUsuario());
        if (usuario != null) {
            usuario.getWishlist().add(solicitudWishlist.getContenido());
            repositorioUsuario.save(usuario);
        }
    }

    // Realiza una búsqueda de contenido (ejemplo)
    public List<String> buscarContenido(String consulta) {
        // Implementar lógica de búsqueda
        return new ArrayList<>(); // Devolver resultados de búsqueda
    }
}

