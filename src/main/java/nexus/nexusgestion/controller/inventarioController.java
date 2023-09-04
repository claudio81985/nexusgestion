package nexus.nexusgestion.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nexus.nexusgestion.Model.Service.ICategoriaService;
import nexus.nexusgestion.Model.Service.IProductoService;
import nexus.nexusgestion.Model.Service.IProveedorService;
import nexus.nexusgestion.Model.Service.ProveedorSinProductosException;

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
        model.addAttribute("proveedores", proveedorService.buscarTodo());

        return "inventario";
    }

    @PostMapping("/aumentar-precios")
    public String aumentarPrecios(@RequestParam("proveedorId") Long proveedorId,
        @RequestParam("aumentoPorcentaje") BigDecimal aumentoPorcentaje,
        RedirectAttributes msgFlash) {
        
        System.out.println("ProveedorID = " + proveedorId);
        System.out.println("Porcentaje para aumentar = " + aumentoPorcentaje);
        
        try {
            productoService.aumentarPreciosPorProveedor(proveedorId, aumentoPorcentaje);
            System.out.println("##################################################");
            System.out.println("SE ACTUALIZARON LOS PRECIOS CORRECTAMENTE");
            System.out.println("##################################################");
            msgFlash.addFlashAttribute("success", "Precios aumentados correctamente.");
        } catch (ProveedorSinProductosException e) {
            msgFlash.addFlashAttribute("warning", e.getMessage());
        } catch (Exception e) {
            msgFlash.addFlashAttribute("danger", "Error al aumentar precios.");
        }

        return "redirect:/inventario";
    }
    

    // @GetMapping("/aumentar")
    // public String auemetar(Model model) {

    //     model.addAttribute("titulo", "Incrementar Precio");
    //     model.addAttribute("productos", productoService.buscarTodo());
    //     model.addAttribute("proveedores", proveedorService.buscarTodo());

    //     return "inventario/aumentar";

    // }
   
}
