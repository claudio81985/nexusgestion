package nexus.nexusgestion.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

// import nexus.nexusgestion.Model.Entities.Categoria;
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
    public String guardar(@Valid Proveedor proveedor, BindingResult result,
            @RequestParam(value = "pro", required = false) Long idPro,
            Model model, RedirectAttributes msgFlash, SessionStatus status, HttpServletRequest request) {

        // Verificar si hay errores
        if (result.hasErrors()) {
            model.addAttribute("danger", "Revise los datos ingresados y/o campos incompletos.");
            return "proveedores/form";
        }

        // Verificar si el parámetro 'pro' está presente en la solicitud
        if (idPro == null) {
            // Si no está presente, asignar el valor predeterminado (ID 24)
            idPro = 24L; // El sufijo 'L' indica que es un valor de tipo Long
        }

        proveedor.setProvincias(provinciasService.buscarPorId(idPro));
        proveedorService.guardar(proveedor);

        msgFlash.addFlashAttribute("success", "Se guardaron los datos correctamente.");
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
                : "Se eliminó el proveedor: " + proveedor.getRazon_soc());

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
