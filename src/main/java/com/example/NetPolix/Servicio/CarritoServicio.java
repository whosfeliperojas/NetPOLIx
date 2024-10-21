package com.example.NetPolix.Servicio;

import com.example.NetPolix.Modelo.Carrito;
import com.example.NetPolix.Modelo.Contenido;
import com.example.NetPolix.Modelo.Usuario;
import com.example.NetPolix.RepositoriosJPA.CarritoRepositorio;
import com.example.NetPolix.RepositoriosJPA.ContenidoRepositorio;
import com.example.NetPolix.RepositoriosJPA.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CarritoServicio {

    @Autowired
    private CarritoRepositorio carritoRepositorio;

    @Autowired
    private ContenidoRepositorio contenidoRepositorio;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public Carrito obtenerCarritoPorUsuario(String nombreUsuario) {
        Usuario usuario = repositorioUsuario.findByNombreUsuario(nombreUsuario);
        return carritoRepositorio.findByUsuario(usuario);
    }

    public void agregarContenidoACarrito(String nombreUsuario, Long contenidoId) {
        Usuario usuario = repositorioUsuario.findByNombreUsuario(nombreUsuario);
        Carrito carrito = carritoRepositorio.findByUsuario(usuario);

        // Si el carrito no existe, se crea uno nuevo
        if (carrito == null) {
            carrito = new Carrito();
            carrito.setUsuario(usuario);
            carrito.setContenidos(new ArrayList<>()); // Inicializa la lista aquí por si acaso
        }

        Optional<Contenido> contenido = contenidoRepositorio.findById(contenidoId);
        if (contenido.isPresent()) {
            carrito.getContenidos().add(contenido.get());
            carritoRepositorio.save(carrito); // Guarda el carrito con el nuevo contenido
        }
    }


    public void eliminarContenidoDeCarrito(String nombreUsuario, Long contenidoId) {
        Usuario usuario = repositorioUsuario.findByNombreUsuario(nombreUsuario);
        Carrito carrito = carritoRepositorio.findByUsuario(usuario);

        if (carrito != null) {
            carrito.getContenidos().removeIf(contenido -> contenido.getId().equals(contenidoId));
            carritoRepositorio.save(carrito);
        }
    }
}
