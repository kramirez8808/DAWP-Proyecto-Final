package dawp.proyecto.dao;

// ------ EXTERNAL IMPORTS ------
import org.springframework.data.jpa.repository.JpaRepository;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    
    //Método para buscar un usuario por su username
    Usuario findByUsername(String username);

    //Método para buscar un usuario por su correo
    Usuario findByUsernameAndPassword(String username, String Password);

    //Método para buscar un usuario por su username o correo
    Usuario findByUsernameOrEmail(String username, String email);

    //Método para verificar si un usuario existe por su username o correo
    boolean existsByUsernameOrEmail(String username, String email);
}
