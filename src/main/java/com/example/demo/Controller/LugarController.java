package com.example.demo.Controller;

import com.example.demo.Model.Lugar;
import com.example.demo.Model.Usuario;
import com.example.demo.Service.LugarService;
import com.example.demo.Repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/lugares")
public class LugarController {

    @Autowired
    private LugarService lugarService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Guardar nuevo lugar
    @PostMapping("/guardar")
    public String guardarLugar(@ModelAttribute Lugar lugar) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findByCorreo(auth.getName()).orElse(null);

        if (usuario != null) {
            // Asociar el usuario creador si tu entidad Lugar lo admite
            lugar.setUsuario(usuario);
            lugarService.guardar(lugar);
        }

        return "redirect:/admin/dashbord";
    }

    // Actualizar lugar existente
    @PostMapping("/actualizar")
    public String actualizarLugar(@ModelAttribute Lugar lugar) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findByCorreo(auth.getName()).orElse(null);

        if (usuario != null) {
            lugar.setUsuario(usuario);
            lugarService.guardar(lugar);
        }

        return "redirect:/admin/dashbord";
    }

    // Eliminar lugar
    @GetMapping("/eliminar/{id}")
    public String eliminarLugar(@PathVariable String id) {
        lugarService.eliminar(id);
        return "redirect:/admin/dashbord";
    }
}