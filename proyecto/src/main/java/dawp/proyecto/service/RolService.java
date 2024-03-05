package dawp.proyecto.service;

// ------ EXTERNAL IMPORTS ------
import java.util.List;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Rol;

public interface RolService {
    
    //GetRoles: Devuelve una lista con todos los roles
    public List<Rol> getRoles();

    //GetRol: Devuelve un rol por su ID
    public Rol getRol(Rol rol);

    //SaveRol: Guarda o actualiza un rol existente
    public void saveRol(Rol rol);

    //DeleteRol: Elimina un rol existente
    public void deleteRol(Rol rol);
}
