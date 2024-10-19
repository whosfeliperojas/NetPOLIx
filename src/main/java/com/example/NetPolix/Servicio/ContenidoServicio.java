package com.example.NetPolix.Servicio;



import com.example.NetPolix.Modelo.Contenido;
import com.example.NetPolix.RepositoriosJPA.ContenidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenidoServicio {

    @Autowired
    private ContenidoRepositorio contenidoRepositorio;

    // Método para buscar contenido
    public List<Contenido> buscarContenido(String titulo, String genero) {
        if (titulo != null && !titulo.isEmpty()) {
            return contenidoRepositorio.findByTituloContainingIgnoreCase(titulo);
        } else if (genero != null && !genero.isEmpty()) {
            return contenidoRepositorio.findByGeneroContainingIgnoreCase(genero);
        }
        return contenidoRepositorio.findAll(); // Devuelve todo si no hay criterios de búsqueda
    }
}
