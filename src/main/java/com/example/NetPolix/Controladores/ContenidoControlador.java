package com.example.NetPolix.Controladores;

import com.example.NetPolix.Modelo.Contenido;
import com.example.NetPolix.Servicio.ContenidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contenido")
public class ContenidoControlador {

    @Autowired
    private ContenidoServicio contenidoServicio;

    //Busca contenido por titulo o genero
    @GetMapping("/buscar")
    public List<Contenido> buscarContenido(@RequestParam(required = false) String titulo,
                                           @RequestParam(required = false) String genero) {
        return contenidoServicio.buscarContenido(titulo, genero);
    }
}
