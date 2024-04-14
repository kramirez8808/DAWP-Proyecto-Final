package dawp.proyecto.service;


// ------ EXTERNAL IMPORTS ------
import java.util.List;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Usuario;

public interface UsuarioService {
    
    // GetUsuarios: Se obtienen todos los usuarios
    public List<Usuario> getUsuarios();
    
    // GetUsuario: Se obtiene un usuario a partir de un ID
    public Usuario getUsuario(Usuario usuario);
    
    // GetUsuarioPorUsername: Se obtiene un Usuario, a partir del username
    public Usuario getUsuarioPorUsername(String username);

    // GetUsuarioPorUsernameYPassword: Se obtiene un Usuario, a partir del username y el password
    public Usuario getUsuarioPorUsernameYPassword(String username, String password);
    
    // GetUsuarioPorUsernameOCorreo: Se obtiene un Usuario, a partir del username o el correo
    public Usuario getUsuarioPorUsernameOCorreo(String username, String correo);
    
    // ExisteUsuarioPorUsernameOCorreo: Se comprueba si existe un usuario a partir del username o correo
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo);
    
    // Save: Se guarda un usuario nuevo o se modifica si ya existe el ID
    public void save(Usuario usuario,boolean crearRolUser);
    
    // Delete: Se elimina un usuario a partir de un ID
    public void delete(Usuario usuario);
    
}

