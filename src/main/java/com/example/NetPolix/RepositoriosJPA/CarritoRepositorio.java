package com.example.NetPolix.RepositoriosJPA;

import com.example.NetPolix.Modelo.Carrito;
import com.example.NetPolix.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepositorio extends JpaRepository<Carrito, Long> {
    Carrito findByUsuario(Usuario usuario);
}
