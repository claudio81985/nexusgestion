package nexus.nexusgestion.controller;

import java.security.Principal;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nexus.nexusgestion.Model.Entities.CambiarClave;
import nexus.nexusgestion.Model.Entities.Usuario;
import nexus.nexusgestion.Model.Service.IUsuarioService;

import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping("/usuarios")
public class CambiarContrasenaController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/nuevaClave")
    public String mostrarFormularioCambioClave(Model model) {
        model.addAttribute("titulo", "Cambiar Contraseña");
        model.addAttribute("cambiarClave", new CambiarClave()); // Agregar el objeto cambiarClave al modelo
        return "usuarios/cambioClave";
    }

    @Transactional
    @PostMapping("/cambiarContrasena")
    public String cambiarContrasena(@ModelAttribute("cambiarClave") @Valid CambiarClave cambiarClave,
            BindingResult result,
            Principal principal,
            RedirectAttributes redirectAttributes, RedirectAttributes msgFlash, SessionStatus status) {

        if (result.hasErrors()) {
            return "usuarios/cambioClave";
        }

        String nombre = principal.getName();
        System.out.println("Nombre de usuario obtenido: " + nombre);

        Usuario usuario = usuarioService.buscarPorNombre(nombre);

        if (usuario == null) {
            System.out.println("No se encontró ningún usuario con el nombre: " + nombre);
            // Manejar el caso de que no se encuentre el usuario
            return "redirect:/error"; // Por ejemplo, redirigir a una página de error
        }

        System.out.println("Usuario encontrado: " + usuario.toString());

        if (!passwordEncoder.matches(cambiarClave.getClaveActual(), usuario.getClave())) {
            result.rejectValue("claveActual", "error.claveActual", "La contraseña actual no es correcta.");
            cambiarClave.setClaveActual("");
            return "usuarios/cambioClave";
        }

        // Verificación de nulidad de la nueva contraseña
        if (cambiarClave.getNuevaclave() == null) {
            result.rejectValue("confirmarclave", "error.confirmarclave", "La nueva contraseña no puede estar vacía.");
            return "usuarios/cambioClave";
        }

        // Verificar que las contraseñas nuevas coincidan
        if (!cambiarClave.getNuevaclave().equals(cambiarClave.getConfirmarclave())) {
            result.rejectValue("confirmarclave", "error.confirmarclave", "Las contraseñas nuevas no coinciden.");
            return "usuarios/cambioClave";
        }

        String nuevaClaveEncriptada = passwordEncoder.encode(cambiarClave.getNuevaclave());
        usuario.setClave(nuevaClaveEncriptada);
        System.out.println("nueva clave: " + nuevaClaveEncriptada);
        usuarioService.guardar(usuario);

        msgFlash.addFlashAttribute("success", "Contraseña cambiada correctamente.");
        status.setComplete();

        return "redirect:/inventario";
    }

}
