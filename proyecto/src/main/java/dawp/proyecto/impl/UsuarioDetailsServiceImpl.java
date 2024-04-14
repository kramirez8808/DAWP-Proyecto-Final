package dawp.proyecto.impl;

// ------ EXTERNAL IMPORTS ------
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// ------ INTERNAL IMPORTS ------
import dawp.proyecto.dao.UsuarioDao;
import dawp.proyecto.domain.Rol;
import dawp.proyecto.domain.Usuario;
import dawp.proyecto.service.UsuarioDetailsService;

@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {
    
    // Interface para el acceso a la tabla de usuarios
    @Autowired
    private UsuarioDao usuarioDao;

    // Datos de la sesión
    @Autowired
    private HttpSession session;

    //Método para cargar el usuario por el username
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // Se busca el usuario por su username en la Tabla Usuario
        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        //Se remueve la imagen del usuario de la sesión
        session.removeAttribute("usuarioImagen");
        //Se agrega la imagen del usuario a la sesión
        session.setAttribute("usuarioImagen", usuario.getImagen());
        
        // Si el usuario existe, se extraen los roles
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        
        // Se retorna el usuario con sus roles
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
}
