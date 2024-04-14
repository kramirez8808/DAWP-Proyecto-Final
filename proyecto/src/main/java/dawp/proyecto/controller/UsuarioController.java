package dawp.proyecto.controller;



// ------ EXTERNAL IMPORTS ------
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Usuario;
import dawp.proyecto.impl.FirebaseStorageServiceImpl;
import dawp.proyecto.service.UsuarioService;


@Controller
@RequestMapping("/usuarios/")
public class UsuarioController {

    //Objeto UsuarioService: Servicio para gestionar los usuarios
    @Autowired
    UsuarioService usuarioService;
    
    //Listado: Devuelve una vista que muestra una lista con todos los usuarios
    @GetMapping("/")
    public String listado(Model model) {
        List<Usuario> lista = usuarioService.getUsuarios();
        model.addAttribute("usuarios", lista);
        model.addAttribute("totalUsuarios", lista.size());

        return "/usuario/listado";
    }

    //Objeto FirebaseStorageService: Servicio para gestionar el almacenamiento de imagenes
    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @PostMapping("/guardar")
    public String usuarioGuardar(Usuario usuario,
            @RequestParam("imagenFile") MultipartFile imagenFile,
            @RequestParam("estado") String estado) {

		usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        boolean nuevo = true;
        
        // Validar si es una creación o modificación (Si trae ID)
        if (usuario.getIdUsuario() != null) {
            nuevo = false;
            Usuario actual = usuarioService.getUsuario(usuario);
            usuario.setPassword(actual.getPassword());
            usuario.setUsername(actual.getUsername());
            usuario.setRoles(actual.getRoles());
            usuario.setActivo(actual.isActivo());
            if (imagenFile.isEmpty()){
                usuario.setImagen(actual.getImagen());
            }
        } 
        else {
            usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
            
            if (estado.equals("1")) {
                usuario.setActivo(false);
            } else {
                usuario.setActivo(true);
            }

        }
        
        if (!imagenFile.isEmpty()) {
            usuarioService.save(usuario,false);
            usuario.setImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "usuario",
                            usuario.getIdUsuario()));
        }
        usuarioService.save(usuario,nuevo);
        return "redirect:/usuarios/";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String usuarioEliminar(Usuario usuario) {
        usuarioService.delete(usuario);
        return "redirect:/usuarios/";
    }

    @GetMapping("/modificar/{idUsuario}")
    public String usuarioModificar(Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);

        List<Usuario> lista = usuarioService.getUsuarios();
        model.addAttribute("totalUsuarios", lista.size());

        return "/usuario/modifica";
    }

}
