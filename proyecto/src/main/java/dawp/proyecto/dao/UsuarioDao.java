package dawp.proyecto.dao;

// ------ EXTERNAL IMPORTS ------
import org.springframework.data.jpa.repository.JpaRepository;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    
    //Método para buscar un usuario por su username
    Usuario findByUsername(String username);

}
