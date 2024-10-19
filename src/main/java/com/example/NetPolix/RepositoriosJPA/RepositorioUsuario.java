package com.example.NetPolix.RepositoriosJPA;

import com.example.NetPolix.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
    boolean existsByNombreUsuario(String nombreUsuario);  // Verifica si el nombre de usuario ya existe
    Usuario findByNombreUsuario(String nombreUsuario); // Encuentra usuario por nombre
}

