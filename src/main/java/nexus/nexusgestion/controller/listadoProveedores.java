package nexus.nexusgestion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proveedores")
public class listadoProveedores {
    @GetMapping("/listado")
    public String proveedores(Model model) {
        model.addAttribute("titulo", "Proveedores");
        return "proveedores/listadoProveedores";
    }
}
