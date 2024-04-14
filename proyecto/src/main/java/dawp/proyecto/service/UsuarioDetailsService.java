package dawp.proyecto.service;

// ------ EXTERNAL IMPORTS ------
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// ------ INTERNAL IMPORTS ------

public interface UsuarioDetailsService {
    
    //Método para cargar un usuario por su username
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
