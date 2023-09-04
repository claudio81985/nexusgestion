package nexus.nexusgestion.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
import nexus.nexusgestion.Model.Entities.Producto;
import nexus.nexusgestion.Model.Entities.Proveedor;
import nexus.nexusgestion.Model.Service.ICategoriaService;
import nexus.nexusgestion.Model.Service.IProductoService;
import nexus.nexusgestion.Model.Service.IProveedorService;
import nexus.nexusgestion.Model.Service.ProveedorSinProductosException;

@Controller
@RequestMapping("/productos")
@SessionAttributes("producto")

@Secured({ "ROLE_ADMINISTRADOR", "ROLE_SUPERUSUARIO" })

public class productoController {

    @Autowired
    IProductoService productoService;

    @Autowired
    ICategoriaService categoriaService;

    @Autowired
    IProveedorService proveedorService;

    @GetMapping("/listado")
    public String listado(Model model) {

        model.addAttribute("titulo", "Listado de Productos");
        model.addAttribute("productos", productoService.buscarTodo());

        return "productos/list";

    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("titulo", "Nuevo Producto");
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.buscarTodo());
        model.addAttribute("proveedores", proveedorService.buscarTodo());

        return "productos/form";
    }

    
    @PostMapping("/guardar")
    public String guardar(@Valid Producto producto, BindingResult result, @RequestParam("cate") Long idCate,
            @RequestParam("provee") Long idProvee,
            Model model, RedirectAttributes msgFlash, SessionStatus status) {

        // Verificar si hay errores
        if (result.hasErrors()) {
            model.addAttribute("danger", "Corrija los Errores...");
            return "productos/form";
        }

        producto.setCategoria(categoriaService.buscarPorId(idCate));
        producto.setProveedor(proveedorService.buscarPorId(idProvee));
        productoService.guardar(producto);

        msgFlash.addFlashAttribute("success", "Producto Guardado Correctamente.");
        status.setComplete();

        return "redirect:/inventario";
    }

    
    @GetMapping("/borrar/{id}")
    public String deshabOrHabProducto(@PathVariable("id") Long id, RedirectAttributes msgFlash) {

        Producto producto = productoService.buscarPorId(id);
        producto.setActivo(!producto.isActivo());
        productoService.guardar(producto);
        msgFlash.addFlashAttribute("warning", producto.isActivo()
                ? "Producto Habilitado"
                : "Se eliminó el producto: " + producto.getCodigoIdentificacion() + " " + producto.getNombreComun());

        return "redirect:/inventario";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {

        Producto producto = productoService.buscarPorId(id);

        model.addAttribute("titulo", "Nuevo Producto");
        model.addAttribute("producto", producto);

        return "productos/form";
    }
   
    

    
    @GetMapping("/moverProducto")
    public String mover(Model model) {

        model.addAttribute("titulo", "Mover Producto");
        model.addAttribute("productos", productoService.buscarTodo());

        return "productos/moverProducto";

    }

    
    @PostMapping("/intercambiar-stock")
    public String intercambiarStock(@RequestParam("codigoIdentificacion") String codigoIdentificacion,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("origen") String origen,
            RedirectAttributes msgFlash) {
        Producto producto = productoService.buscarPorCodigoIdentificacion(codigoIdentificacion);

        if (producto != null) {
            try {
                if ("sucursalUno".equals(origen)) {
                    productoService.intercambiarStockSucursales(producto, cantidad, 0);
                } else if ("sucursalDos".equals(origen)) {
                    productoService.intercambiarStockSucursales(producto, 0, cantidad);
                }

                productoService.guardar(producto);
                msgFlash.addFlashAttribute("success", "Stock de sucursales intercambiado correctamente.");
            } catch (IllegalArgumentException e) {
                msgFlash.addFlashAttribute("warning", e.getMessage());
            } catch (IllegalStateException e) {
                msgFlash.addFlashAttribute("danger", e.getMessage());
            }
        } else {
            msgFlash.addFlashAttribute("danger", "Producto no encontrado.");
        }

        return "redirect:/inventario";
    }

    @ModelAttribute("categorias")
    public List<Categoria> getCategorias() {
        return categoriaService.buscarTodo();
    }

    @ModelAttribute("proveedores")
    public List<Proveedor> getProveedor() {
        return proveedorService.buscarTodo();
    }

}
