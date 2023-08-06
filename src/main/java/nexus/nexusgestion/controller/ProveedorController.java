package nexus.nexusgestion.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nexus.nexusgestion.Model.Entities.Categoria;
import nexus.nexusgestion.Model.Entities.Proveedor;
import nexus.nexusgestion.Model.Entities.Provincias;
import nexus.nexusgestion.Model.Service.IProveedorService;
import nexus.nexusgestion.Model.Service.IProvinciasService;

@Controller
@RequestMapping("/proveedores")
@SessionAttributes("proveedor")
// @Secured({"ROLE_ADMIN", "ROLE_USER"})
public class ProveedorController {

    @Autowired
    IProveedorService proveedorService;

    @Autowired
    IProvinciasService provinciasService;

    @GetMapping("/listado")
    public String listado(Model model) {

        model.addAttribute("titulo", "Listado de Proveedores");
        model.addAttribute("proveedores", proveedorService.buscarTodo());

        return "proveedores/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("titulo", "Nuevo Proveedor");
        model.addAttribute("proveedor", new Proveedor());

        return "proveedores/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Proveedor proveedor, BindingResult result, @RequestParam("pro") Long idPro,
            Model model, RedirectAttributes msgFlash, SessionStatus status) {

        // Verificar si hay errores
        if (result.hasErrors()) {
            model.addAttribute("danger", "Corrija los Errores...");
            return "proveedores/form";
        }

        proveedor.setProvincias(provinciasService.buscarPorId(idPro));
        proveedorService.guardar(proveedor);

        msgFlash.addFlashAttribute("success", "Proveedor Guardado Correctamente.");
        status.setComplete();

        return "redirect:/proveedores/listado";
    }

    @GetMapping("/borrar/{id}")
    public String deshabOrHabProducto(@PathVariable("id") Long id, RedirectAttributes msgFlash) {

        Proveedor proveedor = proveedorService.buscarPorId(id);
        proveedor.setActivo(!proveedor.isActivo());
        proveedorService.guardar(proveedor);
        
        msgFlash.addFlashAttribute("warning", proveedor.isActivo()
            ? "Proveedor Habilitado"
            : "Se eliminó el proveedor: " + proveedor.getRazon_soc()
        );

        return "redirect:/proveedores/listado";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {

        Proveedor proveedor = proveedorService.buscarPorId(id);

        model.addAttribute("titulo", "Nuevo Proveedor");
        model.addAttribute("proveedor", proveedor);

        return "proveedores/form";

    }

   @ModelAttribute("provincias")
    public List<Provincias> getProvincias() {
        return provinciasService.buscarTodo();
    }
}
