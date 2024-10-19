package com.example.NetPolix.RepositoriosJPA;

import com.example.NetPolix.Modelo.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContenidoRepositorio extends JpaRepository<Contenido, Long> {


    List<Contenido> findByTituloContainingIgnoreCase(String titulo);
    List<Contenido> findByGeneroContainingIgnoreCase(String genero);
}

