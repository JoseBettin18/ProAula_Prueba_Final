package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Model.Usuario;
import com.example.demo.Repository.UsuarioRepository;

@Controller
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {

        // Validar si el correo ya existe
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            return "redirect:/auth/registro?error=correo";
        }

        // Encriptar contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Normalizar el rol
        String rol = (usuario.getRol() == null || usuario.getRol().isBlank())
                ? "USER"
                : usuario.getRol().trim().toUpperCase();

        if (!rol.equals("ADMIN") && !rol.equals("USER")) {
            rol = "USER"; // Seguridad adicional
        }

        usuario.setRol(rol);

        // Guardar usuario en Mongo
        usuarioRepository.save(usuario);

        // Redirigir al login con mensaje de éxito
        return "redirect:/auth/login?success=true";
    }
}