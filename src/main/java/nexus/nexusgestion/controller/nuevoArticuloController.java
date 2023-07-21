package nexus.nexusgestion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class nuevoArticuloController {
    @GetMapping("/nuevo-articulo")
    public String nuevoArticulo(Model model) {
        model.addAttribute("titulo", "Nuevo artículo");
        
        return "nuevo-articulo";
    }  
}
