package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.Lugar;
import com.example.demo.Service.LugarService;

@Controller
public class RutaController {

    @Autowired
    private LugarService lugarService;

    // Página de bienvenida
    @GetMapping("/")
    public String inicio() {
        return "Bienvenida";
    }

    // Login
    @GetMapping("/auth/login")
    public String mostrarLogin() {
        return "auth/login";
    }

    // Registro (solo muestra el formulario)
    @GetMapping("/auth/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new com.example.demo.Model.Usuario());
        return "auth/registro";
    }

    // Panel de administración
    @GetMapping("/admin/dashbord") // lo dejo con tu nombre actual
    public String mostrarDashboard(Model model) {
        model.addAttribute("lugares", lugarService.listar());
        return "admin-dashbord";
    }

    // Formulario para registrar nuevo lugar
    @GetMapping("/admin/lugares/nuevo")
    public String nuevoLugar(Model model) {
        model.addAttribute("lugar", new Lugar());
        return "lugar-form";
    }

    // Página principal con lugares
    @GetMapping("/lugares")
    public String mostrarLugares(Model model) {
        model.addAttribute("lugares", lugarService.listar());
        return "lugares";
    }

    // Detalle de lugar
    @GetMapping("/lugares/{id}")
    public String detalleLugar(@PathVariable String id, Model model) {
        lugarService.buscarPorId(id).ifPresent(lugar -> model.addAttribute("lugar", lugar));
        return "lugar_detalle";
    }
}