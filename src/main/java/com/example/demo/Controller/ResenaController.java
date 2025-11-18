package com.example.demo.Controller;

import com.example.demo.Model.Resena;
import com.example.demo.Model.Usuario;
import com.example.demo.Model.Lugar;
import com.example.demo.Repository.ResenaRepository;
import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.Service.LugarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/lugares")
public class ResenaController {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private LugarService lugarService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Mostrar reseñas asociadas a un lugar
    @GetMapping("/{id}/resenas")
    public String listarResenas(@PathVariable String id, Model model) {
        Lugar lugar = lugarService.buscarPorId(id).orElse(null);
        if (lugar == null) {
            return "redirect:/lugares";
        }

        List<Resena> resenas = resenaRepository.findByLugarId(id);
        model.addAttribute("lugar", lugar);
        model.addAttribute("resenas", resenas);
        model.addAttribute("resena", new Resena());
        return "lugar-detalle";
    }

    // Guardar nueva reseña
    @PostMapping("/{id}/resenas")
    public String guardarResena(@PathVariable String id, @ModelAttribute Resena resena) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findByCorreo(auth.getName()).orElse(null);

        if (usuario == null) {
            return "redirect:/login";
        }

        resena.setLugarId(id);
        resena.setUsuarioNombre(usuario.getNombre());
        resena.setFecha(LocalDateTime.now());
        resenaRepository.save(resena);

        return "redirect:/lugares/" + id + "/resenas";
    }
}