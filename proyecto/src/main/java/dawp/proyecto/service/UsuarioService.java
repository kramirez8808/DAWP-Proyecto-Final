package dawp.proyecto.service;

// ------ EXTERNAL IMPORTS ------
import java.util.List;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Usuario;

public interface UsuarioService {
    
    //GetUsuarios: Devuelve una lista con todos los usuarios
    public List<Usuario> getUsuarios();

    //GetUsuariosActivos: Devuelve una lista con todos los usuarios activos
    public List<Usuario> getUsuariosActivos(boolean activos);

    //GetUsuario: Devuelve un usuario por su ID
    public Usuario getUsuario(Usuario usuario);

    //SaveUsuario: Guarda o actualiza un usuario existente
    public void saveUsuario(Usuario usuario);

    //DeleteUsuario: Elimina un usuario existente
    public void deleteUsuario(Usuario usuario);
}
