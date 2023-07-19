package nexus.nexusgestion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class inventarioController {
    @GetMapping({"/","/inventario"})
    public String inventario(Model model) {
        model.addAttribute("titulo", "Inventario");
        
        return "inventario";


    }   

    @GetMapping("/ventas")
    public String ventas(Model model) {
        model.addAttribute("titulo", "Ventas");

        return "ventas";
    }
}

