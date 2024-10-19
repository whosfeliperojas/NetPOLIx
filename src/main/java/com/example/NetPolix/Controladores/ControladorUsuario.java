package com.example.NetPolix.Controladores;

import com.example.NetPolix.Servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class ControladorUsuario {

    @Autowired
    private ServicioUsuario servicioUsuario;

    // Registro de un nuevWebSecurityConfigurerAdaptero usuario
    // Registro de un nuevo usuario
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody SolicitudRegistro solicitudRegistro) {
        if (servicioUsuario.existePorNombreUsuario(solicitudRegistro.getNombreUsuario())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El nombre de usuario ya existe");
        }
        servicioUsuario.crearUsuario(solicitudRegistro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
    }


    // Inicio de sesión de un usuario
    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody SolicitudLogin solicitudLogin) {
        boolean autenticado = servicioUsuario.autenticarUsuario(solicitudLogin);
        if (autenticado) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    // Edición de preferencias del perfil
    @PutMapping("/preferencias")
    public ResponseEntity<?> editarPreferencias(@RequestBody PreferenciasUsuario preferenciasUsuario) {
        servicioUsuario.actualizarPreferencias(preferenciasUsuario);
        return ResponseEntity.ok("Preferencias actualizadas");
    }

    // Obtener wishlist
    @GetMapping("/wishlist/{nombreUsuario}")
    public ResponseEntity<List<String>> obtenerWishlist(@PathVariable String nombreUsuario) {
        List<String> wishlist = servicioUsuario.obtenerWishlist(nombreUsuario);
        return ResponseEntity.ok(wishlist);
    }

    // Agregar a wishlist
    @PostMapping("/wishlist/agregar")
    public ResponseEntity<?> agregarAWishlist(@RequestBody SolicitudWishlist solicitudWishlist) {
        servicioUsuario.agregarAWishlist(solicitudWishlist);
        return ResponseEntity.ok("Contenido agregado a wishlist");
    }

    // Barra de búsqueda
    @GetMapping("/buscar")
    public ResponseEntity<List<String>> buscarContenido(@RequestParam String consulta) {
        List<String> resultados = servicioUsuario.buscarContenido(consulta);
        return ResponseEntity.ok(resultados);
    }
}

