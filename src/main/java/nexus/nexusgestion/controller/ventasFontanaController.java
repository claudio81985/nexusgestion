package nexus.nexusgestion.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nexus.nexusgestion.Model.Entities.LineaVenta;
import nexus.nexusgestion.Model.Entities.Producto;
import nexus.nexusgestion.Model.Entities.Venta;
import nexus.nexusgestion.Model.Service.IProductoService;
import nexus.nexusgestion.Model.Service.IVentaService;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/ventas")
@SessionAttributes("venta")
public class ventasFontanaController {

    @Autowired
    IProductoService productoService;

    @Autowired
    IVentaService ventaService;

    @GetMapping("/listado")
    public String listado(Model model) {

        model.addAttribute("titulo", "Listado de Ventas");
        model.addAttribute("ventas", ventaService.buscarTodo());

        return "ventas/list";
    }

    @GetMapping("/nuevo")
    public String nuevaVenta(Model model) {

        model.addAttribute("titulo", "Nueva Venta");
        model.addAttribute("venta", new Venta());

        return "ventas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Venta venta, BindingResult result,
            @RequestParam(name = "item_id[]") List<String> itemIds,
            @RequestParam(name = "cantidad[]") List<String> cantidades,
            Model model, RedirectAttributes flash, SessionStatus status) {

        // Verificar los errores:
        if (result.hasErrors()) {
            model.addAttribute("danger", "Corrija los errores...");
            return "ventas/form";
        }

        // Verificar si hay productos en la venta...
        if (itemIds == null || itemIds.size() == 1) {
            model.addAttribute("titulo", "Nueva Venta");
            model.addAttribute("warning", "Añadir productos a la venta...");
            return "ventas/form";
        }

        // Si no hay errores...
        LineaVenta linea;
        Producto producto;

        // Cargar las lineas en la venta...
        for (int i = 0; i < itemIds.size() - 1; i++) {

            linea = new LineaVenta();
            Long id = Long.parseLong(itemIds.get(i));
            int cant = Integer.parseInt(cantidades.get(i));

            producto = productoService.buscarPorId(id);

            linea.setProducto(producto);
            linea.setPrecioActual(producto.getPrecio());
            linea.setCantidad(cant);

            // No tendría que llegar acá...
            if (cant > producto.getStockSucursalUno() || cant > producto.getStockSucursalDos()) {
                model.addAttribute("titulo", "Nueva Venta");
                model.addAttribute("danger", "No hay stock suficiente...");
                return "ventas/form";
            }

            // Actualizar stock de la sucursal y stock general:
            producto.setStockSucursalUno(producto.getStockSucursalUno() - cant);
            producto.setStockSucursalDos(producto.getStockSucursalDos() - cant);

            venta.addLinea(linea);

        }

        // Guardar la venta...
        ventaService.guardar(venta);
        status.setComplete();
        flash.addFlashAttribute("success", "Registrado con exito...");

        return "redirect:/ventas/listado";
    }

    @GetMapping(value = "/buscar-productos/{criterio}", produces = { "application/json" })
    public @ResponseBody List<Producto> buscarProductos(@PathVariable("criterio") String texto) {
        return productoService.buscarPor(texto);
    }

    @GetMapping("/borrar/{id}")
    public String deshabOrHabVenta(@PathVariable("id") Long id, RedirectAttributes msgFlash) {

        Venta venta = ventaService.buscarPorId(id);
        venta.setActivo(!venta.isActivo());
        ventaService.guardar(venta);
        msgFlash.addFlashAttribute("warning", venta.isActivo() ? "Venta Habilitada" : "Venta Deshabilitada");

        return "redirect:/ventas/listado";
    }

}
