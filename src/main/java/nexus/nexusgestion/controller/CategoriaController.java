package nexus.nexusgestion.controller;




import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nexus.nexusgestion.Model.Entities.Categoria;
import nexus.nexusgestion.Model.Service.ICategoriaService;
import nexus.nexusgestion.Model.Service.IProductoService;

@Controller
@RequestMapping("/categorias")
@SessionAttributes("categoria")
@Secured({"ROLE_SUPERUSUARIO",  "ROLE_ADMINISTRADOR"})

public class CategoriaController {

    @Autowired
    IProductoService productoService;

    @Autowired
    ICategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {

        model.addAttribute("titulo", "Listado de Productos");
        model.addAttribute("categorias", categoriaService.buscarTodo());

        return "categorias/list";

    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("titulo", "Nueva Categoria");
        model.addAttribute("categoria", new Categoria());

        return "categorias/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Categoria categoria, BindingResult result,
            Model model, RedirectAttributes msgFlash, SessionStatus status) {

        // Verificar si hay errores
        if (result.hasErrors()) {
            model.addAttribute("danger", "Corrija los Errores...");
            return "categorias/form";
        }

        categoriaService.guardar(categoria);

        msgFlash.addFlashAttribute("success", "Categoria Guardada Correctamente.");
        status.setComplete();

        return "redirect:/compras/nuevo";
    }

    @GetMapping("/borrar/{id}")
    public String deshabOrHabCategoria(@PathVariable("id") Long id, RedirectAttributes msgFlash) {

        Categoria categoria = categoriaService.buscarPorId(id);
        categoria.setActivo(!categoria.isActivo());
        categoriaService.guardar(categoria);
        msgFlash.addFlashAttribute("warning",
                categoria.isActivo() ? "Categoria Habilitado" : "Categoria Deshabilitado");

        return "redirect:/categorias/listado";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {

        Categoria categoria = categoriaService.buscarPorId(id);

        model.addAttribute("titulo", "Nueva Categoria");
        model.addAttribute("categoria", categoria);

        return "categorias/form";
    }

}
