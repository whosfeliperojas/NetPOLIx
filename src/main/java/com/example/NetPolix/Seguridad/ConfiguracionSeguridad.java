package com.example.NetPolix.Seguridad;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usar BCrypt para encriptar contraseñas
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Deshabilita CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/usuario/**").permitAll() // Permitir acceso sin autenticación a las rutas de usuario
                        .requestMatchers("/api/contenido/buscar").permitAll()
                        .anyRequest().authenticated() // Requiere autenticación para otras rutas
                )
                .cors(cors -> cors.disable()); // Deshabilitar CORS si es necesario
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .inMemoryAuthentication() // Ejemplo simple de autenticación en memoria
                .withUser("usuario") // Nombre de usuario
                .password(passwordEncoder().encode("contrasena")) // Encriptar la contraseña
                .roles("USER"); // Rol asignado

        return authenticationManagerBuilder.build(); // Exponemos el AuthenticationManager como un bean
    }
}
