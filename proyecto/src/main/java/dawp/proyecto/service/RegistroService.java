package dawp.proyecto.service;

// ------ EXTERNAL IMPORTS ------
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Usuario;

public interface RegistroService {

    //Método Model para activar un usuario
    public Model activar(Model model, String usuario, String clave);

    //Método para crear un usuario
    public Model crearUsuario(Model model, Usuario usuario) throws MessagingException;
    
    //Método para activar un usuario
    public void activar(Usuario usuario, MultipartFile imagenFile);
    
    //Método para recordar un usuario
    public Model recordarUsuario(Model model, Usuario usuario) throws MessagingException;
}
