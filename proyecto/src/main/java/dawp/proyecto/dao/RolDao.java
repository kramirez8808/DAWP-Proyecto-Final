package dawp.proyecto.dao;

// ------ EXTERNAL IMPORTS ------
import org.springframework.data.jpa.repository.JpaRepository;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Rol;

public interface RolDao extends JpaRepository<Rol, Long> {
    
}
