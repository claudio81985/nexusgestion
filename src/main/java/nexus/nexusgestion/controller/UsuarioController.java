package nexus.nexusgestion.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.access.annotation.Secured;
// import org.springframework.security.crypto.password.PasswordEncoder;
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

import nexus.nexusgestion.Model.Entities.Permiso;
import nexus.nexusgestion.Model.Entities.Sucursal;
import nexus.nexusgestion.Model.Entities.Usuario;
import nexus.nexusgestion.Model.Service.IPermisoService;
import nexus.nexusgestion.Model.Service.ISucursalService;
import nexus.nexusgestion.Model.Service.IUsuarioService;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuarios")
@SessionAttributes("usuario")
@Secured({ "ROLE_SUPERUSUARIO" })
public class UsuarioController {

    @Autowired
    IUsuarioService usuarioService;

    @Autowired
    IPermisoService permisoService;

    @Autowired
    ISucursalService sucursalService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/listado")
    public String listado(Model model) {

        model.addAttribute("titulo", "Listado de Usuarios");
        model.addAttribute("usuarios", usuarioService.buscarTodos());

        return "usuarios/list";

    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("titulo", "Nuevo Usuario");
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("permisos", permisoService.buscarTodo());
        model.addAttribute("sucursales", sucursalService.buscarTodos());


        return "usuarios/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Usuario usuario, BindingResult result, @RequestParam("permiso") Long idPermiso,
            @RequestParam("sucursalAsignada") Long idSucursal, Model model, RedirectAttributes msgFlash, SessionStatus status) {

        // Verificar si hay errores de validación
        if (result.hasErrors()) {
            model.addAttribute("danger", "Corrija los Errores...");
            return "usuarios/form";
        }

        if (usuario.getId() == null) {
            // Esto es para nuevos usuarios
            usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        } else {
            // Esto es para usuarios existentes
            Usuario usuarioExistente = usuarioService.buscarPorId(usuario.getId());
            if (usuarioExistente != null) {
                usuarioExistente.setNombre(usuario.getNombre());
                usuario = usuarioExistente;
            } else {
                model.addAttribute("danger", "Usuario no encontrado");
                return "usuarios/form";
            }
        }

        // Asignar el permiso al usuario
        Permiso permisoEmpleado = permisoService.buscarPorId(idPermiso);
        if (permisoEmpleado != null) {
            usuario.setPermiso(permisoEmpleado);
        } else {
            model.addAttribute("danger", "Permiso no encontrado");
            return "usuarios/form";
        }

        // Asignar la sucursal al usuario
        Sucursal sucursalUno = sucursalService.buscarPorId(idSucursal);
        if (sucursalUno != null) {
            usuario.setSucursalAsignada(sucursalUno);
        } else {
            model.addAttribute("danger", "Sucursal no encontrada");
            return "usuarios/form";
        }

        usuarioService.guardar(usuario);

        msgFlash.addFlashAttribute("success", "Usuario Registrado Correctamente.");
        status.setComplete();

        return "redirect:/usuarios/listado";
    }

    
    @GetMapping("/borrar/{id}")
    public String deshabOrHabUsuario(@PathVariable("id") Long id, RedirectAttributes msgFlash) {

        Usuario usuario = usuarioService.buscarPorId(id);
        usuario.setActivo(!usuario.isActivo());
        usuarioService.guardar(usuario);
        msgFlash.addFlashAttribute("warning", usuario.isActivo() ? "Usuario Habilitado" : "Usuario Deshabilitado");

        return "redirect:/usuarios/listado";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {

        Usuario usuario = usuarioService.buscarPorId(id);

        model.addAttribute("titulo", "Nuevo Usuario");
        model.addAttribute("usuario", usuario);

        return "usuarios/form";
    }

    @ModelAttribute("permisos")
    public List<Permiso> getPermisos() {
        return permisoService.buscarTodo();
    }

    @ModelAttribute("sucursales")
    public List<Sucursal> getSucursales(){
        return sucursalService.buscarTodos();
    }

}
