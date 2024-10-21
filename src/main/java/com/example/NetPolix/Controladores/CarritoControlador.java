package com.example.NetPolix.Controladores;

import com.example.NetPolix.Modelo.Carrito;
import com.example.NetPolix.Servicio.CarritoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
public class CarritoControlador {

    @Autowired
    private CarritoServicio carritoServicio;

    @GetMapping("/{nombreUsuario}")
    public ResponseEntity<Carrito> obtenerCarrito(@PathVariable String nombreUsuario) {
        Carrito carrito = carritoServicio.obtenerCarritoPorUsuario(nombreUsuario);
        if (carrito != null) {
            return ResponseEntity.ok(carrito);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarContenidoACarrito(@RequestParam String nombreUsuario, @RequestParam Long contenidoId) {
        carritoServicio.agregarContenidoACarrito(nombreUsuario, contenidoId);
        return ResponseEntity.ok("Contenido agregado al carrito");
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarContenidoDeCarrito(@RequestParam String nombreUsuario, @RequestParam Long contenidoId) {
        carritoServicio.eliminarContenidoDeCarrito(nombreUsuario, contenidoId);
        return ResponseEntity.ok("Contenido eliminado del carrito");
    }
}
