package nexus.nexusgestion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nexus.nexusgestion.Model.Service.ICategoriaService;
import nexus.nexusgestion.Model.Service.IProductoService;
import nexus.nexusgestion.Model.Service.IProveedorService;

@Controller
public class inventarioController {

    @Autowired
    IProductoService productoService;

    @Autowired
    IProveedorService proveedorService;

    @Autowired
    ICategoriaService categoriaService;

    @GetMapping({ "/", "/inventario" })
    public String inventario(Model model) {
        model.addAttribute("titulo", "Inventario");
        model.addAttribute("productos", productoService.buscarTodo());
        model.addAttribute("categorias", categoriaService.buscarTodo());

        return "inventario";
    }

   
   
}
