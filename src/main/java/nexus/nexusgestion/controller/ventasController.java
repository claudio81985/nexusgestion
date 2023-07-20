package nexus.nexusgestion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/ventas")
public class ventasController {
    @GetMapping("/")
    public String ventas(Model model) {
        model.addAttribute("titulo", "Ventas");
        return "ventas/ventas"; // ventas.html dentro de la carpeta "ventas"
    }
}
